(ns student-management-system.controller.FacultyController
  (:require [student-management-system.repository.FacultyRepository :refer [FacultyRepository]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [student-management-system.service.FacultyService :refer [create-faculty
                                                                   get-faculties
                                                                   get-faculty
                                                                   update-faculty
                                                                   delete-faculty]]
            [schema.core :as schema]))

(def faculty-routes
  [(POST "/faculty" []
     :tags ["faculty"]
     :body [add-faculty-req FacultyRepository]
     :summary "Create new faculty"
     :return [FacultyRepository]
     (create-faculty add-faculty-req))
   (GET "/faculty/all" []
     :tags ["faculty"]
     :summary "Get all faculties"
     (get-faculties))
   (GET "/faculties/:id" []
     :path-params [id :- schema/Int]
     :summary "Get faculty by id"
     :tags ["faculty"]
     (get-faculty id))
   (PUT "/faculties/:id" []
     :tags ["faculty"]
     :summary "Update faculty by id"
     :path-params [id :- schema/Int]
     :body [updated-faculty FacultyRepository]
     (update-faculty id updated-faculty))
   (DELETE "/faculty/:id" []
     :tags ["faculty"]
     :summary "Delete faculty by id"
     :path-params [id :- schema/Int]
     (delete-faculty id))])
