(ns yatodolist.core
  (:require [compojure.core :refer [defroutes]]
            [yatodolist.controllers.home :as home]
            [yatodolist.controllers.todo :as todo])
  (:gen-class))

(defroutes routes
  home/routes
  todo/routes)
