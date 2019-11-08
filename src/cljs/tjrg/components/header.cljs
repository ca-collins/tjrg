(ns tjrg.components.header
  (:require
   [reagent.core :as r]
   [tjrg.components.svg :refer [menu-icon close-icon]]
   [cljss.core :refer-macros [defstyles]]
   [cljss.core :as css]
   [goog.string :as g]))


(defstyles navbar []
  {:font-size "18px"
   :background "linear-gradient(to left, #ebd5b3, #CDC5B4)"
   :border "1px solid rgba(0, 0, 0, 0.2)"
   :-webkit-box-shadow "0px 2px 5px 0px rgba(0,0,0,0.75)"
   :-moz-box-shadow "0px 2px 5px 0px rgba(0,0,0,0.75)"
   :box-shadow "0px 2px 5px 0px rgba(0,0,0,0.75)"
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
   :margin-top "2.5rem"
   ::css/media {[:screen :and [:min-width "768px"]]
                {:display "flex"
                 :margin-right "30px"
                 :margin-top "1rem"
                 :flex-direction "row"
                 :justify-content "flex-end"}}})
(defstyles nav-items []
  {:text-align "center"
   :margin "15px auto"
   ::css/media {[:screen :and [:min-width "768px"]]
                {:margin "0"}}})
(defstyles links [{:keys [is-active]}]
  {:text-decoration "none"
   :border-bottom (if is-active "1px solid black" "none")
   :color "rgba(0, 0, 0, 0.7)"
   :&:hover {:color "#73859d"}})
(defstyles nav-links []
  {::css/media {[:screen :and [:min-width "768px"]]
                {:margin-left "40px"}}})
(defstyles logo []
  {:display "inline-block"
   :font-size "22px"
   :font-family "franklin"
   :margin-top "10px"
   :margin-left "20px"
   ::css/media {[:screen :and [:min-width "768px"]]
                {:margin-top "0"}}})
(defstyles navbar-toggle []
  {:position "absolute"
   :top "10px"
   :right "20px"
   :cursor "pointer"
   :fill "rgba(0, 0, 0, 0.6)"
   :font-size "24px"
   ::css/media {[:screen :and [:min-width "768px"]]
                {:display "none"}}})

(defn header []
 (let [open (r/atom false)
       handle-close #(reset! open false)
       handle-open #(reset! open true)
       active-link (r/atom false)
       ;; TODO: Have active-link update based on route not link target.name
       handle-link (fn [e]
                     (do (reset! active-link (.. e -target -name))
                         (handle-close)))]
   (fn []
    [:nav {:class (navbar)}
     (if @open
       [:span {:class (navbar-toggle)
               :on-click handle-close}
        [close-icon]]
       [:span {:class (navbar-toggle)
               :on-click handle-open}
        [menu-icon]])
     [:a {:href "/"
          :class [(logo) (links false)]
          :name "home"
          :on-click handle-link}
         (str "The James River" (g/unescapeEntities "&nbsp;") "Gazette")]
     [:ul {:class (if @open (main-nav "block") (main-nav "none")), :id "js-menu"}
      [:li {:class (nav-items)}
       [:a {:href "/"
            :name "home"
            :class [(nav-links)
                    (links (if (= @active-link "home") {:is-active true}))]
            :on-click handle-link}
           "Home"]]
      [:li {:class (nav-items)}
       [:a {:href "/about"
            :name "about"
            :class [(nav-links)
                    (links (if (= @active-link "about") {:is-active true}))]
            :on-click handle-link}
           "About"]]
      [:li {:class (nav-items)}
       [:a {:href "/contribute"
            :name "contribute"
            :class [(nav-links)
                    (links (if (= @active-link "contribute") {:is-active true}))]
            :on-click handle-link}
           (str "Get" (g/unescapeEntities "&nbsp;") "Involved")]]
      [:li {:class (nav-items)}
       [:a {:href "/advertise"
            :name "advertise"
            :class [(nav-links)
                    (links (if (= @active-link "advertise") {:is-active true}))]
            :on-click handle-link}
           "Advertise"]]
      [:li {:class (nav-items)}
       [:a {:href "#"
            :class [(nav-links) (links {:is-active false})]
            :on-click handle-close}
           "Donate"]]]])))
