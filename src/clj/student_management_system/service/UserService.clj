(ns student-management-system.service.UserService
  (:require [student_management_system.model.User :refer [User]]
            [toucan.db :as db]
            [ring.util.http-response :refer [ok not-found created]]
            [buddy.hashers :as hashers]))

(defn crypt-password [user-req]
  (-> (update user-req :password hashers/derive)))

(defn id->created [id]
  (created (str "/users/" id) {:id id}))

(defn create-user [add-user-req]
  (->> (crypt-password add-user-req)
       (db/insert! User)
       :id
       id->created))

(defn get-users []
  (->> (db/select User)
       (map #(dissoc % :password))
       ok))

(defn user->response [user]
  (if user
    (ok user)
    (not-found)))

(defn get-user [user-id]
  (-> (User user-id)
      user->response))

(defn update-user [id updated-user]
  (db/update! User id (crypt-password updated-user))
  (ok (crypt-password updated-user)))

(defn delete-user [id]
  (db/delete! User :id id)
  (ok))
