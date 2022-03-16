(ns student-management-system.service.FacultyService
  (:require [student_management_system.model.Faculty :refer [Faculty]]
            [toucan.db :as db]
            [ring.util.http-response :refer [ok not-found created]]
            [buddy.hashers :as hashers]))

(defn id->created [id]
  (created (str "/faculties/" id) {:id id}))

(defn create-faculty [add-faculty-req]
  (->> (db/insert! Faculty add-faculty-req)
       :id
       id->created))

(defn get-faculties []
  (->> (db/select Faculty)
       ok))

(defn faculty->response [faculty]
  (if faculty
    (ok faculty)
    (not-found)))

(defn get-faculty [faculty-id]
  (-> (Faculty faculty-id)
      faculty->response))

(defn update-faculty [id updated-faculty]
  (db/update! Faculty id updated-faculty)
  (ok updated-faculty))

(defn delete-faculty [id]
  (db/delete! Faculty :id id)
  (ok))
