(ns tjrg.home.core
  (:require
   [reagent.core :as r]
   [soda-ash.core :as sa]
   [goog.string :as g]
   [tjrg.core :refer [path-for]]))

(defn homepage []
  (fn []
    [sa/Container
     ;; HERO ==============================
     [sa/Container {:text-align "center" :text true}
      [sa/Header {:size "huge"} "The James River Gazette"]
      [:p (str "A community-driven newspaper dedicated to connecting the people of the "
               "Middle" (g/unescapeEntities "&nbsp;")
               "James"  (g/unescapeEntities "&nbsp;")
               "River"  (g/unescapeEntities "&nbsp;")
               "Basin"  (g/unescapeEntities "&nbsp;")
               "around the common goal of protecting our local waterways.")]
      [sa/Container
       [sa/Button {:type "button"
                   :size "medium"
                   :basic true
                   :color "large"
                   :href (path-for :about)}
                  "Learn more"]
       [sa/Button {:type "button"
                   :size "large"
                   :primary true}
                  "Subscribe"]]]
      ; [:br]
      ; [sa/Button {:href "/issues/0_2020_01_07_James_River_Gazette_EXAMPLE.pdf"
      ;             :download true
      ;             :type "button"
      ;             :secondary true
      ;             :icon true
      ;             :label-position "left"}
      ;            "Download the Latest Issue"
      ;            [sa/Icon {:class "download"}]]]


     ;; GET INVOLVED ==============================
     [sa/Container {:text-align "center" :text true}
      [:br]
      [sa/Header {:size "large"} "Get Involved"]
      [:p "Write an article, submit a joke or comic, give us feedback"]
      [sa/Container
       [sa/Button {:type "button"
                   :size "medium"
                   :basic true
                   :href (path-for :contribute)}
                  "Learn how"]]]


     ;; ADVERTISE ==============================
     [sa/Container {:text-align "center" :text true}
      [:br]
      [sa/Header {:size "large"} "Advertise with Us"]
      [:p "Odio duis pharetra hendrerit ullamcorper. Consequat urna per odio cum etiam luctus nisl etiam? Dapibus fusce tempus eget fusce consectetur rutrum gravida sapien posuere. "]
      [sa/Container
       [sa/Button {:type "button"
                   :size "medium"
                   :basic true
                   :href (path-for :advertise)}
                  "Learn more"]
       [sa/Button {:type "button"
                   :primary true
                   :href (path-for :advertise)}
                  "Contact us"]]]

     ;; Donate ==============================
     [sa/Container {:text-align "center" :text true}
      [:br]
      [sa/Header {:size "large"} "Support the JRG"]
      [:p "Please consider supporting the JRG with a small donation.
           Proceeds help us cover our printing and website costs
           and any surplus is donated to the "
           [:a {:href "#"} "Jame River Foundation."]]
      [sa/Button {:type "button"
                  :primary true
                  :href "https://www.paypal.me/jrgazette"
                  :target "_blank"}
                 "Donate"]]]))
