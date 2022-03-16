(ns student-management-system.service.UniversityService
  (:require [student_management_system.model.University :refer [University]]
            [toucan.db :as db]
            [ring.util.http-response :refer [ok not-found created]]
            [buddy.hashers :as hashers]))

(defn id->created [id]
  (created (str "/universities/" id) {:id id}))

(defn create-university [add-university-req]
  (->> (db/insert! University add-university-req)
       :id
       id->created))

(defn get-universities []
  (->> (db/select University)
       ok))

(defn university->response [university]
  (if university
    (ok university)
    (not-found)))

(defn get-university [university-id]
  (-> (University university-id)
      university->response))

(defn update-university [id updated-university]
  (db/update! University id updated-university)
  (ok updated-university))

(defn delete-university [id]
  (db/delete! University :id id)
  (ok))
