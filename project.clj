(defproject vega-vis "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [reagent "0.7.0"]
                 [hiccup "1.0.5"]
                 [org.clojure/clojurescript "1.9.946"]
                 [metosin/vega-tools "0.2.0"]]

  :source-paths ["src/cljs"]

  :plugins [[lein-cljsbuild "1.1.7"  :exclusions [[org.clojure/clojure]]]
            [lein-figwheel "0.5.13"]]

  :figwheel {:css-dirs ["resources/public/css"]}

  :clean-targets ^{:protect false} ["resources/public/js/compiled" :target-path]

  :cljsbuild
  {:builds
   [{:id "dev"
     :source-paths ["src/cljs"]
     :figwheel {:on-jsload "vega-vis.core/on-js-reload"
                :open-urls ["http://localhost:3449/index.html"]}
     :compiler {:main vega-vis.core
                :asset-path "js/compiled/out"
                :output-to "resources/public/js/compiled/vega_vis.js"
                :output-dir "resources/public/js/compiled/out"
                :source-map-timestamp true
                :preloads [devtools.preload]}}]}

 :profiles {:dev
             {:dependencies [[binaryage/devtools "0.9.9"]
                             [figwheel-sidecar "0.5.14"]
                             [com.cemerick/piggieback "0.2.2"]
                             [org.clojure/tools.nrepl "0.2.13"]]

              :plugins [[cider/cider-nrepl "0.16.0-SNAPSHOT"]]
              :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
