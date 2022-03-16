(ns student-management-system.repository.StudentRepository
  (:require [schema.core :as schema]
            [student_management_system.model.Student :refer [Student]]))

(schema/defschema StudentRepository
  {:firstName schema/Str
   :lastName  schema/Str
   :programmeOfStudy schema/Str
   :email     schema/Str
   :contact   schema/Str
   :yearOfAward schema/Str
   :country  schema/Str})
