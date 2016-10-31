(ns yatodolist.controllers.todo
  (:require [liberator.core :refer [defresource resource]]
            [compojure.core :refer [defroutes GET]]
            [hiccup.page :as page]
            [monger.conversion :refer [from-db-object]]
            [yatodolist.queries.todo :refer [todo-all]]))

(defn index-render [todos]
  (clojure.string/join "<br>" (map #(str (:_id %) " " (:name %)) todos)))

(defresource index-resource [todos]
  :available-media-types ["text/html"]
  :handle-ok (index-render todos))

(defn index []
  (index-resource (todo-all)))

(defn- show-html [id]
  (page/html5
    [:head
      [:title "Todo item"]]
    [:body
      [:div {:id (format "todo id: %d" id)}]]))

(defn- get-json [id]
  {:todo {:id id}})

(defn- check-content-type [ctx content-types]
  (let [content-type (get-in ctx [:request :headers "content-type"])]
    (if (nil? content-type)
      true
      (or
       (some #{content-type} content-types)
       [false {:message "Unsupported Content-Type"}]))))

(defresource show-resource [id]
  ; :known-content-type? #(check-content-type % ["application/json"])
  :available-media-types ["text/html" "application/json"]
  :handle-ok
  #(let [media-type
         (get-in % [:representation :media-type])]
    (condp = media-type
      "text/html" (show-html id)
      "application/json" (get-json id)
      {:message "You requested a media type"
       :media-type media-type})))

(defroutes routes
  (GET "/todos" [] (index))
  (GET "/todos/:id" [id] (show-resource (Integer/parseInt id))))
