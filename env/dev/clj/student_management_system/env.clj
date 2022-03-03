(ns student-management-system.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [student-management-system.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[student-management-system started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[student-management-system has shut down successfully]=-"))
   :middleware wrap-dev})
