(ns student-management-system.repository.FacultyRepository
  (:require [schema.core :as schema]
            [student_management_system.model.Faculty :refer [Faculty]]))

(schema/defschema FacultyRepository
  {:name  schema/Str
   :address schema/Str})
