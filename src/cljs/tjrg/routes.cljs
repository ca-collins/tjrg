(ns tjrg.routes
  (:require
   [reagent.session :as session]
   [reitit.frontend :as reitit]
   [tjrg.home.core :refer [homepage]]
   [tjrg.about :refer [about-page]]
   [tjrg.contribute :refer [contribute-page]]
   [tjrg.advertise1 :refer [advertise-page]]
   [tjrg.components.header :refer [header]]))

(def router
  (reitit/router
   [["/" :index]
    ["/about" :about]
    ["/contribute" :contribute]
    ["/advertise" :advertise]]))


(defn path-for [route & [params]]
  (if params
    (:path (reitit/match-by-name router route params))
    (:path (reitit/match-by-name router route))))

(defn page-for [route]
  (case route
    :index #'homepage
    :about #'about-page
    :contribute #'contribute-page
    :advertise #'advertise-page))

(defn current-page []
  (fn []
    (let [page (:current-page (session/get :route))]
      [:<>
       [header]
       [page]])))
