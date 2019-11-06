(ns tjrg.home.core
  (:require
   [reagent.core :as r]
   [goog.string :as g]
   [tjrg.home.hero :refer [hero]]
   [tjrg.home.contribute :refer [contribute]]
   [tjrg.home.advertise1 :refer [advertise]]
   [tjrg.home.donate :refer [donate]]
   [cljss.core :refer-macros [defstyles]]))

(defstyles app-margin []
  {:margin "0 20px 50px 20px"})

(defn homepage []
  (fn []
    [:div {:class (app-margin)}
     [hero]
     [contribute]
     [advertise]
     [donate]]))
