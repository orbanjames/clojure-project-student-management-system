(ns student-management-system.core-test
  (:require [clojure.test :refer :all]
            [student-management-system.core :refer :all]
            [student-management-system.service.StudentService :as StudentService]
            [student-management-system.service.FacultyService :as FacultyService]
            [student-management-system.service.CourseService :as CourseService]
            [student-management-system.service.UniversityService :as UniversityService]
            [student-management-system.service.UserService :as UserService]
            [toucan.db :as db]
            ))

(db/set-default-quoting-style! :postgresql)

(db/set-default-db-connection!
  {
   :classname   "com.postgresql.cj.jdbc.Driver"
   :subprotocol "postgresql"
   :subname     "//localhost:5433/student_management_system"
   :user        "postgres"
   :password    "postgres"
   :useSSL      false
   })

(deftest create-user
  (testing "Test create user"
    (let [user (UserService/create-user {:username  "jamesorban"
                                         :password  "james123"
                                         :firstName "Aondowase"
                                         :lastName  "Orban"
                                         :contact   "08189427322"
                                         :email     "orbanjames5@gmail.com"})
          found-user (UserService/get-user ((user :body) :id))]
      (is (= "jamesorban" ((found-user :body) :username)))
      )
    )
  )

(deftest delete-user
  (testing "Test delete user"
    (let [user (UserService/create-user {:username  "jamesorban"
                                         :password  "james123"
                                         :firstName "Aondowase"
                                         :lastName  "Orban"
                                         :contact   "08189427322"
                                         :email     "orbanjames5@gmail.com"})]
      (UserService/delete-user ((user :body) :id))
      (is (= nil ((UserService/get-user ((user :body) :id)) :body)))
      )))

(deftest update-user
  (testing "Test update user"
    (let [user (UserService/create-user {:username  "jamesorban"
                                         :password  "james123"
                                         :firstName "Aondowase"
                                         :lastName  "Orban"
                                         :contact   "08189427322"
                                         :email     "orbanjames5@gmail.com"})
          update (UserService/update-user ((user :body) :id) {:username  "orban12345"
                                                              :password  "james123"
                                                              :firstName "Aondowase"
                                                              :lastName  "Orban"
                                                              :contact   "08189427322"
                                                              :email     "orbanjames5@gmail.com"})
          found-user (UserService/get-user (update :body))]
      (is (= "orban12345" ((found-user :body) :username)))
      )
    )
  )

(deftest find-all-users
  (testing "Find all users"
    (def usersList (count ((UserService/get-users) :body)))
    (UserService/create-user {:username  "jamesorban"
                              :password  "james123"
                              :firstName "Aondowase"
                              :lastName  "Orban"
                              :contact   "08189427322"
                              :email     "orbanjames5@gmail.com"})
    (is (= (inc usersList) (count ((UserService/get-users) :body))))
    )
  )




(deftest create-student
  (testing "Test create student"
    (let [student (StudentService/create-student {
                                                  :firstName "Aondowase"
                                                  :lastName  "Orban"
                                                  :programmeOfStudy "PhD"
                                                  :contact   "08189427322"
                                                  :email     "aondowase@gmail.com"
                                                  :yearOfAward 2021
                                                  :country   "Nigeria"})
          found-student (StudentService/get-student ((student :body) :id))]
      (is (= "Aondowase" ((found-student :body) :firstName)))
      )
    )
  )

(deftest delete-student
  (testing "Test delete student"
    (let [student (StudentService/create-student {:country  "Nigeria"
                                                  :yearOfAward  2021
                                                  :firstName "Aondowase"
                                                  :lastName  "Orban"
                                                  :programmeOfStudy "PhD"
                                                  :contact   "08189427322"
                                                  :email     "aondowase@gmail.com"})]
      (StudentService/delete-student ((student :body) :id))
      (is (= nil ((StudentService/get-student ((student :body) :id)) :body)))
      )))

(deftest update-student
  (testing "Test update student"
    (let [student (StudentService/create-student {:country  "Nigeria"
                                                  :yearOfAward  2021
                                                  :firstName "Aondowase"
                                                  :lastName  "Orban"
                                                  :programmeOfStudy "PhD"
                                                  :contact   "08189427322"
                                                  :email     "aondowase@gmail.com"})
          update (StudentService/update-student ((student :body) :id) {:country  "Nigeria"
                                                              :yearOfAward  2021
                                                              :firstName "James"
                                                              :lastName  "Orban"
                                                              :programmeOfStudy "PhD"
                                                              :contact   "08189427322"
                                                              :email     "aondowase@gmail.com"})
          found-student (StudentService/get-student (update :body))]
      (is (= "James" ((found-student :body) :firstName)))
      )
    )
  )

(deftest find-all-students
  (testing "Find all students"
    (def studentsList (count ((StudentService/get-students) :body)))
    (StudentService/create-student {:country  "Nigeria"
                                    :yearOfAward  2021
                                    :firstName "Aondowase"
                                    :lastName  "Orban"
                                    :programmeOfStudy "PhD"
                                    :contact   "08189427322"
                                    :email     "aondowase@gmail.com"})
    (is (= (inc studentsList) (count ((StudentService/get-students) :body))))
    )
  )

(deftest create-university
  (testing "Test create university"
    (let [university (UniversityService/create-university {
                                                  :name "University of Belgrade"
                                                  :location  "Belgrade"
                                                  :address   "Belgrade"})
          found-university (UniversityService/get-university ((university :body) :id))]
      (is (= "University of Belgrade" ((found-university :body) :name)))
      )
    )
  )

(deftest delete-university
  (testing "Test delete university"
    (let [university (UniversityService/create-university {:name "University of Belgrade"
                                                           :location  "Belgrade"
                                                           :address   "Belgrade"})]
      (UniversityService/delete-university ((university :body) :id))
      (is (= nil ((UniversityService/get-university ((university :body) :id)) :body)))
      )))

(deftest update-university
  (testing "Test update university"
    (let [university (UniversityService/create-university {:name "University of Belgrade"
                                                           :location  "Belgrade"
                                                           :address   "Belgrade"})
          update (UniversityService/update-university ((university :body) :id) {:name "University of Novi Sad"
                                                                                :location  "Belgrade"
                                                                                :address   "Belgrade"})
          found-university (UniversityService/get-university (update :body))]
      (is (= "University of Novi Sad" ((found-university :body) :name)))
      )
    )
  )

(deftest find-all-universities
  (testing "Find all universities"
    (def universitiesList (count ((UniversityService/get-universities) :body)))
    (UniversityService/create-university {:name "University of Belgrade"
                                    :location  "Belgrade"
                                    :address   "Belgrade"})
    (is (= (inc universitiesList) (count ((UniversityService/get-universities) :body))))
    )
  )



(deftest create-faculty
  (testing "Test create faculty"
    (let [faculty (FacultyService/create-faculty {
                                                  :name "FON"
                                                  :address   "Belgrade"})
          found-faculty (FacultyService/get-faculty ((faculty :body) :id))]
      (is (= "FON" ((found-faculty :body) :name)))
      )
    )
  )

(deftest delete-faculty
  (testing "Test delete faculty"
    (let [faculty (FacultyService/create-faculty {:name "FON"
                                                  :address   "Belgrade"})]
      (FacultyService/delete-faculty ((faculty :body) :id))
      (is (= nil ((FacultyService/get-faculty ((faculty :body) :id)) :body)))
      )))

(deftest update-faculty
  (testing "Test update faculty"
    (let [faculty (FacultyService/create-faculty {:name "FON"
                                                  :address   "Belgrade"})
          update (FacultyService/update-faculty ((faculty :body) :id) {:name "Faculty of Mathematics"
                                                                       :address   "Belgrade"})
          found-faculty (FacultyService/get-faculty (update :body))]
      (is (= "Faculty of Mathematics" ((found-faculty :body) :name)))
      )
    )
  )

(deftest find-all-faculties
  (testing "Find all faculties"
    (def facultiesList (count ((FacultyService/get-faculties) :body)))
    (FacultyService/create-faculty {:name "FON"
                                    :address   "Belgrade"})
    (is (= (inc facultiesList) (count ((FacultyService/get-faculties) :body))))
    )
  )


(deftest create-course
  (testing "Test create course"
    (let [course (CourseService/create-course {
                                                  :title "Tools and Analysis of Software Engineering"
                                                  :code   "M9821"})
          found-course (CourseService/get-course ((course :body) :id))]
      (is (= "Tools and Analysis of Software Engineering" ((found-course :body) :title)))
      )
    )
  )

(deftest delete-course
  (testing "Test delete course"
    (let [course (CourseService/create-course {:title "Tools and Analysis of Software Engineering"
                                               :code   "M9821"})]
      (CourseService/delete-course ((course :body) :id))
      (is (= nil ((CourseService/get-course ((course :body) :id)) :body)))
      )))

(deftest update-course
  (testing "Test update course"
    (let [course (CourseService/create-course {:title "Tools and Analysis of Software Engineering"
                                               :code   "M9821"})
          update (CourseService/update-course ((course :body) :id) {:title "Software Process"
                                                                       :code   "M9825"})
          found-course (CourseService/get-course (update :body))]
      (is (= "Software Process" ((found-course :body) :title)))
      )
    )
  )

(deftest find-all-courses
  (testing "Find all courses"
    (def coursesList (count ((CourseService/get-courses) :body)))
    (CourseService/create-course {:title "Tools and Analysis of Software Engineering"
                                    :code   "M9821"})
    (is (= (inc coursesList) (count ((CourseService/get-courses) :body))))
    )
  )