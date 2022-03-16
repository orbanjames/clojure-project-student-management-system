(ns student-management-system.controller.UniversityController
  (:require [student-management-system.repository.UniversityRepository :refer [UniversityRepository]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [student-management-system.service.UniversityService :refer [create-university
                                                    get-universities
                                                    get-university
                                                    update-university
                                                    delete-university]]
            [schema.core :as schema]))

(def university-routes
  [(POST "/university" []
     :tags ["university"]
     :body [add-university-req UniversityRepository]
     :summary "Create new university"
     :return [UniversityRepository]
     (create-university add-university-req))
   (GET "/university/all" []
     :tags ["university"]
     :summary "Get all universities"
     (get-universities))
   (GET "/university/:id" []
     :path-params [id :- schema/Int]
     :summary "Get university by id"
     :tags ["university"]
     (get-university id))
   (PUT "/university/:id" []
     :tags ["university"]
     :summary "Update university by id"
     :path-params [id :- schema/Int]
     :body [updated-university UniversityRepository]
     (update-university id updated-university))
   (DELETE "/university/:id" []
     :tags ["university"]
     :summary "Delete university by id"
     :path-params [id :- schema/Int]
     (delete-university id))])
