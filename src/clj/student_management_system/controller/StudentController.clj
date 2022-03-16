(ns student-management-system.controller.StudentController
  (:require [student-management-system.repository.StudentRepository :refer [StudentRepository]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [student-management-system.service.StudentService :refer [create-student
                                                                   get-students
                                                                   get-student
                                                                   update-student
                                                                   delete-student]]
            [schema.core :as schema]))

(def student-routes
  [(POST "/student" []
     :tags ["student"]
     :body [add-student-req StudentRepository]
     :summary "Create new student"
     :return [StudentRepository]
     (create-student add-student-req))
   (GET "/student/all" []
     :tags ["student"]
     :summary "Get all students"
     (get-students))
   (GET "/student/:id" []
     :path-params [id :- schema/Int]
     :summary "Get student by id"
     :tags ["student"]
     (get-student id))
   (PUT "/student/:id" []
     :tags ["student"]
     :summary "Update student by id"
     :path-params [id :- schema/Int]
     :body [updated-student StudentRepository]
     (update-student id updated-student))
   (DELETE "/student/:id" []
     :tags ["student"]
     :summary "Delete student by id"
     :path-params [id :- schema/Int]
     (delete-student id))])
