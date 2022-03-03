(ns student-management-system.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[student-management-system started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[student-management-system has shut down successfully]=-"))
   :middleware identity})
