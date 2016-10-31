(defproject yatodolist "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [liberator "0.13"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [hiccup "1.0.5"]
                 [midje "1.7.0"]
                 [ring-mock "0.1.5"]
                 [com.stuartsierra/component "0.3.0"]
                 [com.novemberain/monger "3.0.1"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-midje "3.1.3"]]
  :ring {:handler yatodolist.core/routes
         :auto-reload? true}
  :main ^:skip-aot yatodolist.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
