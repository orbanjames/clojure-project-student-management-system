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
                 [nav-link "#/STUDENTS" "STUDENTS"]
                 [nav-link "#/USER-REGISTRATION" "USER-REGISTRATION" :USER-REGISTRATION]
                 [nav-link "#/FACULTIES" "FACULTIES" :FACULTIES]]]]))

(defn about-page []
  [:section.section>div.container>div.content
   [:h1 "First Hands on Clojure Project!"]
   ;[:img {:src "/img/warning_clojure.png"}]
   ])

(defn USER-REGISTRATION []
  [:section.section>div.container>div.content
   [:div.container
    [:p "Please login here."]
    [:hr]
    [:label {:for "user-name"} [:b "user-name"]]
    [:input#email {:type "text" :placeholder "Enter user-name" :name "name" :required "true"}]
    [:br] [:br]
    [:label {:for "psw"} [:b "Password"]]
    [:input#psw {:type "password" :placeholder "Enter Password" :name "psw" :required "true"}]
    [:br] [:br]
    [:button.check {:type "submit"} "sign-in"]]
   [:div.container.signin
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
                         [:h2 "LIST OF STUDENTS ON SCHOLARSHIP"]
                         [:table
                          [:tr
                           [:th "First Name"]
                           [:th "Last Name"]
                           [:th "Other Names"]
                           [:th "Country"]
                           [:th "Email"]
                           [:th "Contact"]]
                          [:tr
                           [:td "Alfreds Futterkiste"]
                           [:td "Maria Anders"]
                           [:td "Germany"]]
                          [:tr
                           [:td "Centro comercial Moctezuma"]
                           [:td "Francisco Chang"]
                           [:td "Mexico"]]
                          [:tr
                           [:td "Ernst Handel"]
                           [:td "Roland Mendel"]
                           [:td "Austria"]]
                          [:tr
                           [:td "Island Trading"]
                           [:td "Helen Bennett"]
                           [:td "UK"]]
                          [:tr
                           [:td "Laughing Bacchus Winecellars"]
                           [:td "Yoshi Tannamuri"]
                           [:td "Canada"]]
                          [:tr
                           [:td "Magazzini Alimentari Riuniti"]
                           [:td "Giovanni Rovelli"]
                           [:td "Italy"]]]]
    [:button.registration {:type "submit"} "login"]]
   [:div.container.sign-in
    ]])

(defn FACULTIES []
  [:section.section>div.container>div.content
   [:div.container
    [:p "Please login here."]
    [:hr]
    [:label {:for "matric.no"} [:b "matric.no"]]
    [:input#email {:type "text" :placeholder "Enter matric.no" :name "var" :required "true"}]
    [:br] [:br]
    [:label {:for "psw"} [:b "Password"]]
    [:input#psw {:type "password" :placeholder "Enter Password" :name "psw" :required "true"}]
    [:br] [:br]
    [:button.check {:type "submit"} "login"]]
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

     ["/STUDENTS" {:name :STUDENTS
                       :view #'STUDENTS}]
     ["/USER-REGISTRATION" {:name :USER-REGISTRATION
                :view #'USER-REGISTRATION}]
     ["/FACULTIES" {:name :FACULTIES
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
