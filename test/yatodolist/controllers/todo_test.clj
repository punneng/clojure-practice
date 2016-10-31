(ns yatodolist.controllers.todo_test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [yatodolist.controllers.todo :refer :all]))

(facts "Todo list"
  (facts "#GET index"
    (facts "should render text"
      (let [response (routes (mock/request :get "/todos"))]
        (:status response) => 200
        (:body response) => (contains "hello world"))))

  (facts "#GET show"
    (facts "text/html"
      (facts "should render id"
        (dotimes [n 10]
          (let [id (+ n 1)
                response (routes (mock/request :get (str "/todos/" id)))]
            (:status response) => 200
            (:body response) => (contains (str "todo id: " id)))))))

    (facts "application/json"
      (facts "should render json with id"
        (dotimes [n 10]
          (let [id (+ n 1)
                response (routes
                          (->
                           (mock/request :get (str "/todos/" id))
                           (mock/header "Accept" "application/json")))]
            (:status response) => 200
            (:body response) => (format "{\"todo\":{\"id\":%d}}" id))))))
