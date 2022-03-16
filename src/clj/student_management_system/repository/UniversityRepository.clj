(ns student-management-system.repository.UniversityRepository
  (:require [schema.core :as schema]
            [student_management_system.model.University :refer [University]]))

(schema/defschema UniversityRepository
  {:name  schema/Str
   :address schema/Str
   :location  schema/Str})
