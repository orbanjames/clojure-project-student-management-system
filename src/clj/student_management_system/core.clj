(ns student-management-system.core
  (:require
    [student-management-system.handler :as handler]
    [student-management-system.nrepl :as nrepl]
    [luminus.http-server :as http]
    [luminus-migrations.core :as migrations]
    [student-management-system.config :refer [env]]
    [clojure.tools.cli :refer [parse-opts]]
    [clojure.tools.logging :as log]
    [mount.core :as mount]
    [toucan.db :as db]
    [toucan.models :as models]
    [ring.adapter.jetty :refer [run-jetty]]
    [ring.middleware.cors :refer [wrap-cors]]
    [ring.util.http-response :refer :all]
    [compojure.api.sweet :refer [api routes]]
    [student-management-system.controller.UserController :refer [user-routes]]
    [student-management-system.controller.UniversityController :refer [university-routes]]
    [student-management-system.controller.StudentController :refer [student-routes]]
    [student-management-system.controller.FacultyController :refer [faculty-routes]]
    [student-management-system.controller.CourseController :refer [course-routes]])
  (:gen-class))

;; log uncaught exceptions in threads
(Thread/setDefaultUncaughtExceptionHandler
  (reify Thread$UncaughtExceptionHandler
    (uncaughtException [_ thread ex]
      (log/error {:what :uncaught-exception
                  :exception ex
                  :where (str "Uncaught exception on" (.getName thread))}))))

(def cli-options
  [["-p" "--port PORT" "Port number"
    :parse-fn #(Integer/parseInt %)]])

(mount/defstate ^{:on-reload :noop} http-server
  :start
  (http/start
    (-> env
        (update :io-threads #(or % (* 2 (.availableProcessors (Runtime/getRuntime))))) 
        (assoc  :handler (handler/app))
        (update :port #(or (-> env :options :port) %))
        (select-keys [:handler :host :port])))
  :stop
  (http/stop http-server))

(mount/defstate ^{:on-reload :noop} repl-server
  :start
  (when (env :nrepl-port)
    (nrepl/start {:bind (env :nrepl-bind)
                  :port (env :nrepl-port)}))
  :stop
  (when repl-server
    (nrepl/stop repl-server)))


(defn stop-app []
  (doseq [component (:stopped (mount/stop))]
    (log/info component "stopped"))
  (shutdown-agents))

(defn start-app [args]
  (doseq [component (-> args
                        (parse-opts cli-options)
                        mount/start-with-args
                        :started)]
    (log/info component "started"))
  (.addShutdownHook (Runtime/getRuntime) (Thread. stop-app)))


(def database-spec
  {:classname   "com.postgresql.cj.jdbc.Driver"
   :subprotocol "postgresql"
   :subname     "//localhost:5433/student_management_system"
   :user        "postgres"
   :password    "postgres"
   :useSSL      false
   })

(def swagger-config
  {
   :ui      "/swagger"
   :spec    "/swagger.json"
   :options {:ui   {:validatorUrl nil}
             :data {:info {:version "1.0.0", :title "Student Management System"}}}

   })

(def app
  (-> (api {:swagger swagger-config} (apply routes user-routes university-routes student-routes faculty-routes course-routes))
      (wrap-cors :access-control-allow-origin #"http://localhost:4200"
                 :access-control-allow-methods [:get :put :delete :post])))

(defn -main [& args]
  (-> args
                            (parse-opts cli-options)
                            (mount/start-with-args #'student-management-system.config/env))
  (cond
    (nil? (:database-url env))
    (do
      (log/error "Database configuration not found, :database-url environment variable must be set before running")
      (System/exit 1))
    (some #{"init"} args)
    (do
      (migrations/init (select-keys env [:database-url :init-script]))
      (System/exit 0))
    (migrations/migration? args)
    (do
      (migrations/migrate args (select-keys env [:database-url]))
      (System/exit 0))
    :else
    (start-app args))
  (db/set-default-db-connection! database-spec)
  (db/set-default-quoting-style! :postgresql)
  (models/set-root-namespace! `student-management-system.model)
  (run-jetty app {:port 3001})
  (println "student management system server started!"))

  
