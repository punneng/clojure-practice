(ns yatodolist.controllers.home
  (:require [liberator.core :refer [resource]]
            [compojure.core :refer [defroutes GET]]
            [hiccup.page :as page]))

(defn index-html []
  (page/html5
    [:head
      [:title "Hello World"]]
    [:body
      [:div {:id "content"} [:h1 "Hello World!"]]]))

(defn index []
  (resource :available-media-types ["text/html"]
            :handle-ok (index-html)))

(defn test-json []
  {:foo "bar"})

(defn json-test []
  (resource :available-media-types ["application/json"]
            :handle-ok (test-json)))

(defroutes routes
  (GET "/" [] (index))
  (GET "/test.json" [] (json-test)))
