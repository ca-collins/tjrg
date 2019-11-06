(ns tjrg.components.header
  (:require
   [reagent.core :as r]
   [tjrg.components.svg :refer [menu-icon]]
   [cljss.core :refer-macros [defstyles]]
   [cljss.core :as css]))

(defstyles navbar []
  {:font-size "18px"
   :background-image "linear-gradient(260deg, #2376ae 0%, #c16ecf 100%)"
   :border "1px solid rgba(0, 0, 0, 0.2)"
   :padding-bottom "10px"
   ::css/media {[:screen :and [:min-width "768px"]]
                {:display "flex"
                 :justify-content "space-between"
                 :padding-bottom "0"
                 :height "70px"
                 :align-items "center"}}})
(defstyles main-nav [state]
  {:list-style-type "none"
   :display state
   ::css/media {[:screen :and [:min-width "768px"]]
                {:display "flex"
                 :margin-right "30px"
                 :flex-direction "row"
                 :justify-content "flex-end"}}})
(defstyles nav-items []
  {:text-align "center"
   :margin "15px auto"
   ::css/media {[:screen :and [:min-width "768px"]]
                {:margin "0"}}})
(defstyles links []
  {:text-decoration "none"
   :color "rgba(255, 255, 255, 0.7)"
   :&:hover {:color "rgba(255, 255, 255, 1)"}})
(defstyles nav-links []
  {::css/media {[:screen :and [:min-width "768px"]]
                {:margin-left "40px"}}})
(defstyles logo []
  {:display "inline-block"
   :font-size "22px"
   :margin-top "10px"
   :margin-left "20px"
   ::css/media {[:screen :and [:min-width "768px"]]
                {:margin-top "0"}}})
(defstyles navbar-toggle []
  {:position "absolute"
   :top "10px"
   :right "20px"
   :cursor "pointer"
   :color "rgba(255, 255, 255, 0.8)"
   :font-size "24px"
   ::css/media {[:screen :and [:min-width "768px"]]
                {:display "none"}}})
(defstyles active []
  {:display "block"})




(defn header []
 (let [open (r/atom false)]
   (fn []
    [:nav {:class (navbar)}
     [:span {:class (navbar-toggle)
             :on-click #(reset! open (not @open))}
      [menu-icon]]
     [:a {:href "#", :class [(logo) (links)]} "logo"]
     [:ul {:class (if @open (main-nav "block") (main-nav "none")), :id "js-menu"}
      [:li {:class (nav-items)}
       [:a {:href "#", :class [(nav-links) (links)]} "Home"]]
      [:li {:class (nav-items)}
       [:a {:href "#", :class [(nav-links) (links)]} "About"]]
      [:li {:class (nav-items)}
       [:a {:href "#", :class [(nav-links) (links)]} "Contribute"]]
      [:li {:class (nav-items)}
       [:a {:href "#", :class [(nav-links) (links)]} "Advertise"]]
      [:li {:class (nav-items)}
       [:a {:href "#", :class [(nav-links) (links)]} "Donate"]]]])))
