(ns student-management-system.service.CourseService
  (:require [student_management_system.model.Course :refer [Course]]
            [toucan.db :as db]
            [ring.util.http-response :refer [ok not-found created]]
            [buddy.hashers :as hashers]))

(defn id->created [id]
  (created (str "/courses/" id) {:id id}))

(defn create-course [add-course-req]
  (->> (db/insert! Course add-course-req)
       :id
       id->created))

(defn get-courses []
  (->> (db/select Course)
       ok))

(defn course->response [course]
  (if course
    (ok course)
    (not-found)))

(defn get-course [course-id]
  (-> (Course course-id)
      course->response))

(defn update-course [id updated-course]
  (db/update! Course id updated-course)
  (ok updated-course))

(defn delete-course [id]
  (db/delete! Course :id id)
  (ok))

