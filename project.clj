(defproject project-0x-connect-fx "0.0.1-SNAPSHOT"
  :description "re-frame effects library for cljs-0x-connect"
  :url "https://github.com/district0x/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[district0x/cljs-0x-connect "1.0.0"]
                 [mount "0.1.11"]
                 [org.clojure/clojurescript "1.9.946"]
                 [re-frame "0.10.5"]]

  :exclusions [[org.clojure/clojure]
               [org.clojure/clojurescript]]

  :clean-targets ^{:protect false} ["target"]

  :profiles {:dev {:dependencies [[org.clojure/clojure "1.8.0"]
                                  [lein-doo "0.1.8"]]
                   :plugins [[lein-cljsbuild "1.1.7"]
                             [lein-doo "0.1.8"]
                             [lein-npm "0.6.2"]]}}

  :cljsbuild {:builds [{:id "tests"
                        :source-paths ["src" "test"]
                        :compiler {:output-to "tests-output/tests.js"
                                   :output-dir "tests-output"
                                   :main "tests.runner"
                                   :optimizations :none}}]})
