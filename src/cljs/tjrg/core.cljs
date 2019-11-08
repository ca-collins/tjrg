(ns tjrg.core
  (:require
   [cljss.core :as css]
   [reagent.core :as r]
   [reagent.session :as session]
   [reitit.frontend :as reitit]
   [clerk.core :as clerk]
   [accountant.core :as accountant]
   [tjrg.home.core :refer [homepage]]
   [tjrg.about :refer [about-page]]
   [tjrg.contribute :refer [contribute-page]]
   [tjrg.advertise1 :refer [advertise-page]]
   [tjrg.components.header :refer [header]]))
;; -------------------------
;; Routes

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
;; -------------------------
;; Initialize app

(defn mount-root []
  (css/remove-styles!)
  (r/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (clerk/initialize!)
  (accountant/configure-navigation!
   {:nav-handler
    (fn [path]
      (let [match (reitit/match-by-path router path)
            current-page (:name (:data  match))
            route-params (:path-params match)]
        (r/after-render clerk/after-render!)
        (session/put! :route {:current-page (page-for current-page)
                              :route-params route-params})
        (clerk/navigate-page! path)))

    :path-exists?
    (fn [path]
      (boolean (reitit/match-by-path router path)))})
  (accountant/dispatch-current!)
  (mount-root))
