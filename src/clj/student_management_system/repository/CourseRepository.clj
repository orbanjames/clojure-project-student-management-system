(ns student-management-system.repository.CourseRepository
  (:require [schema.core :as schema]
            [student_management_system.model.Course :refer [Course]]))

(schema/defschema CourseRepository
  {:title  schema/Str
   :code schema/Str})
