(ns student-management-system.repository.UserRepository
  (:require [schema.core :as schema]
            [student_management_system.model.User :refer [User]]))

(schema/defschema UserRepository
  {:username  schema/Str
   :firstName schema/Str
   :lastName  schema/Str
   :email     schema/Str
   :contact   schema/Str
   :password  schema/Str})
