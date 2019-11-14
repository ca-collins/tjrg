(ns tjrg.home.core
  (:require
   [reagent.core :as r]
   [goog.string :as g]
   [tjrg.home.hero :refer [hero]]
   [tjrg.home.contribute :refer [contribute]]
   [tjrg.home.advertise1 :refer [advertise]]
   [tjrg.home.donate :refer [donate]]
   [cljss.core :refer-macros [defstyles]]))

(defstyles section []
  {:padding "0 20px 50px 20px"
   :box-box-shadow "1px solid rgba(0, 0, 0, 0.2)"})
(defstyles dark-section []
  {:background-color "#CBB18E"})

(defn homepage []
  (fn []
   [:<>
    [:div {:class (section)}
     [hero]]
    [:div {:class [(section) (dark-section)]}
     [contribute]]
    [:div {:class (section)}
     [advertise]]
    [:div {:class [(section) (dark-section)]}
     [donate]]]))
