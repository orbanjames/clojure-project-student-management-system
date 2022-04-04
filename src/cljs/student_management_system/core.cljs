(ns student-management-system.core
  (:require
    [day8.re-frame.http-fx]
    [reagent.dom :as rdom]
    [reagent.core :as r]
    [re-frame.core :as rf]
    [goog.events :as events]
    [goog.history.EventType :as HistoryEventType]
    [markdown.core :refer [md->html]]
    [student-management-system.ajax :as ajax]
    [student-management-system.events]
    [reitit.core :as reitit]
    [reitit.frontend.easy :as rfe]
    [clojure.string :as string])
  (:import goog.History))

(defn nav-link [uri title page]
  [:a.navbar-item
   {:href   uri
    :class (when (= page @(rf/subscribe [:common/page-id])) :is-active)}
   title])

(defn navbar [] 
  (r/with-let [expanded? (r/atom false)]
              [:nav.navbar.is-info>div.container
               [:div.navbar-brand
                [:a.navbar-item {:href "/" :style {:font-weight :bold}} "student-management-system"]
                [:span.navbar-burger.burger
                 {:data-target :nav-menu
                  :on-click #(swap! expanded? not)
                  :class (when @expanded? :is-active)}
                 [:span][:span][:span]]]
               [:div#nav-menu.navbar-menu
                {:class (when @expanded? :is-active)}
                [:div.navbar-start
                 [nav-link "#/" "Home" :Home]
                 [nav-link "#/about" "About" :about]
                 [nav-link "#/students" "STUDENTS"]
                 [nav-link "#/universities" "UNIVERSITIES" :UNIVERSITIES]
                 [nav-link "#/registration" "USER-REGISTRATION" :USER-REGISTRATION]
                 [nav-link "#/faculties" "FACULTIES" :FACULTIES]]]]))

(defn about-page []
  [:section.section>div.container>div.content
   [:h1 "This is a CRUD application designed and implemented using Clojure programming language
         for the fulfilment of the course title Tools and Methods of Software Engineering !"]])

(defn USER-REGISTRATION []
  [:section.section>div.container>div.content
   [:div.container
    [:p "Registration Form"]
    [:hr]
    [:label {:for "user-name"} [:b "user-name"]]
    [:input#email {:type "text" :placeholder "Enter user-name" :name "name" :required "true"}]
    [:br] [:br]
    [:hr]
    [:label {:for "Email"} [:b "Email"]]
    [:input#email {:type "text" :placeholder "Enter Email" :name "name" :required "true"}]
    [:br] [:br]
    [:label {:for "psw"} [:b "Password"]]
    [:input#psw {:type "password" :placeholder "Enter Password" :name "psw" :required "true"}]
    [:br] [:br]
    [:button.check {:type "Register"} "Register User"]]
   [:div.container.register
    ]])


(defn STUDENTS []
  [:section.section>div.container>div.content
   [:div.container
    [:style "table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }"] [:body
                         [:h2 "LIST OF STUDENTS"]
                         [:table
                          [:tr
                           [:th "First Name"]
                           [:th "Last Name"]
                           [:th "Email"]
                           [:th "Country"]
                           [:th "Programme of Study"]
                           [:th "Contact"]
                           [:th "Year of Award"]]
                          [:tr
                           [:td "Aondowase"]
                           [:td "Orwase"]
                           [:td "orwase10@gmail.com"]
                           [:td "Zambia"]
                           [:td "PHD"]
                           [:td "07058673547"]
                           [:td "2010"]]
                          [:tr
                           [:td "Ngukenger"]
                           [:td "Igulen"]
                           [:td "ngukenger@gmail.com"]
                           [:td "Canada"]
                           [:td "Bachelor"]
                           [:td "08189427322"]
                           [:td "2015"]]]]
    [:button.update {:type "update"} "update"]]
   [:div.container.update
    ]])


(defn UNIVERSITIES []
  [:section.section>div.container>div.content
   [:div.container
    [:style "table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }"] [:body
                         [:h2 "LIST OF UNIVERSITIES"]
                         [:table
                          [:tr
                           [:th "Name"]
                           [:th "Address"]
                           [:th "Location"]]
                          [:tr
                           [:td "University of Belgrade"]
                           [:td "Belgrade"]
                           [:td "Belgrade"]]
                          [:tr
                           [:td "University of Novi Sad"]
                           [:td "Novi Sad"]
                           [:td "Novi Sad"]]
                          [:tr
                           [:td "University of Nis"]
                           [:td "Nis"]
                           [:td "Nis"]]]]
    [:button.update {:type "update"} "update"]]
   [:div.container.update
    ]])


(defn FACULTIES []
  [:section.section>div.container>div.content
   [:div.container
    [:style "table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }"] [:body
                         [:h2 "LIST OF FACULTIES"]
                         [:table
                          [:tr
                           [:th "Name Of Faculty"]
                           [:th "Location"]
                           [:th "Address"]]
                          [:tr
                           [:td "Faculty of Organizational Sciences"]
                           [:td "Belgrade"]
                           [:td "No 502 Milan Jovanovic Street, Belgrade"]]]]
    [:button.update {:type "update"} "update"]]
   [:div.container.sign-in
    ]])


(defn home-page []
  )

(defn page []
  (if-let [page @(rf/subscribe [:common/page])]
    [:div
     [navbar]
     [page]]))

(defn navigate! [match _]
  (rf/dispatch [:common/navigate match]))

(def router
  (reitit/router
    [["/" {:name        :home
           :view        #'home-page
           :controllers [{:start (fn [_] (rf/dispatch [:page/init-home]))}]}]
     ["/about" {:name :about
                :view #'about-page}]

     ["/students" {:name :STUDENTS
                       :view #'STUDENTS}]
     ["/universities" {:name :UNIVERSITIES
                   :view #'UNIVERSITIES}]
     ["/registration" {:name :USER-REGISTRATION
                :view #'USER-REGISTRATION}]
     ["/faculties" {:name :FACULTIES
                :view #'FACULTIES}]]))

(defn start-router! []
  (rfe/start!
    router
    navigate!
    {}))

;; -------------------------
;; Initialize app
(defn ^:dev/after-load mount-components []
  (rf/clear-subscription-cache!)
  (rdom/render [#'page] (.getElementById js/document "app")))

(defn init! []
  (start-router!)
  (ajax/load-interceptors!)
  (mount-components))
