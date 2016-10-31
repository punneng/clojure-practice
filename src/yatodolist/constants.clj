(ns yatodolist.constants
  (:require [monger.core :as mg]))

(def conn (mg/connect {:host "127.0.0.1" :port 27017}))
(def db (mg/get-db conn "todo-dev"))
