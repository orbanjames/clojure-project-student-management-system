(ns student-management-system.controller.CourseController
   (:require [student-management-system.repository.CourseRepository :refer [CourseRepository]]
             [compojure.api.sweet :refer [GET POST PUT DELETE]]
             [student-management-system.service.CourseService :refer [create-course
                                                                    get-courses
                                                                    get-course
                                                                    update-course
                                                                    delete-course]]
             [schema.core :as schema]))


(def course-routes
   [(POST "/course" []
       :tags ["course"]
       :body [add-course-req CourseRepository]
       :summary "Create new course"
       :return [CourseRepository]
       (create-course add-course-req))
    (GET "/course/all" []
       :tags ["course"]
       :summary "Get all courses"
       (get-courses))
    (GET "/course/:id" []
       :path-params [id :- schema/Int]
       :summary "Get course by id"
       :tags ["course"]
       (get-course id))
    (PUT "/course/:id" []
       :tags ["course"]
       :summary "Update course by id"
       :path-params [id :- schema/Int]
       :body [updated-course CourseRepository]
       (update-course id updated-course))
    (DELETE "/course/:id" []
       :tags ["course"]
       :summary "Delete course by id"
       :path-params [id :- schema/Int]
       (delete-course id))])
