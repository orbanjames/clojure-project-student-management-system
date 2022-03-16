(ns student-management-system.service.StudentService
  (:require [student_management_system.model.Student :refer [Student]]
            [toucan.db :as db]
            [ring.util.http-response :refer [ok not-found created]]
            [buddy.hashers :as hashers]))

(defn id->created [id]
  (created (str "/students/" id) {:id id}))

(defn create-student [add-student-req]
  (->> (db/insert! Student add-student-req)
       :id
       id->created))

(defn get-students []
  (->> (db/select Student)
       ok))

(defn student->response [student]
  (if student
    (ok student)
    (not-found)))

(defn get-student [student-id]
  (-> (Student student-id)
      student->response))

(defn update-student [id updated-student]
  (db/update! Student id updated-student)
  (ok updated-student))

(defn delete-student [id]
  (db/delete! Student :id id)
  (ok))
