(ns yatodolist.queries.todo
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.conversion :refer [from-db-object]]
            [yatodolist.constants :refer [conn db]]))
(def coll "todos")

(defn todo-all []
  (mc/find-maps db coll))
