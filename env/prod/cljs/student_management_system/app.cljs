(ns student-management-system.app
  (:require [student-management-system.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
