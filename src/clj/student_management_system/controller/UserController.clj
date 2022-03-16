(ns student-management-system.controller.UserController
  (:require [student-management-system.repository.UserRepository :refer [UserRepository]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [student-management-system.service.UserService :refer [create-user
                                                    get-users
                                                    get-user
                                                    update-user
                                                    delete-user]]
            [schema.core :as schema]))

(def user-routes
  [(POST "/user" []
     :tags ["user"]
     :body [add-user-req UserRepository]
     :summary "Create new user"
     :return [UserRepository]
     (create-user add-user-req))
   (GET "/user/all" []
     :tags ["user"]
     :summary "Get all users"
     (get-users))
   (GET "/user/:id" []
     :path-params [id :- schema/Int]
     :summary "Get user by id"
     :tags ["user"]
     (get-user id))
   (PUT "/user/:id" []
     :tags ["user"]
     :summary "Update user by id"
     :path-params [id :- schema/Int]
     :body [updated-user UserRepository]
     (update-user id updated-user))
   (DELETE "/user/:id" []
     :tags ["user"]
     :summary "Delete user by id"
     :path-params [id :- schema/Int]
     (delete-user id))])
