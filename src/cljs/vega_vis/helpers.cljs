(ns vega-vis.helpers
  (:require [vega-vis.stocks-index-chart :as stocks]
            ;; [clojure.spec.alpha :as sp]
  ))


(defn random-data
  "Generate `n` kinda random y values `[1, maxval]` "
  [n max-val]
  (let [ys (->> (repeatedly #(rand-int max-val))
                (take n))
        xs (range 1 n)]
    (mapv (fn [[x y]] {:x x
                       :y y})
          (zipmap xs ys))))
        


(defn bar-chart
  "Take a collation of maps with keys of [x y]."
  [data]
  {:width  600
   :height 200
   :padding {:top 10, :left 30, :bottom 30, :right 10}
   :data [{:name "table"
           :values data}]
   :scales [{:name "x"
             :type "ordinal"
             :range "width"
             :domain {:data "table", :field "x"}}
            {:name "y"
             :type "linear"
             :range "height"
             :domain {:data "table", :field "y"}, :nice true}]
   :axes [{:type "x", :scale "x"}
          {:type "y", :scale "y"}]
   :marks [{:type "rect", :from {:data "table"},
            :properties {:enter {:x {:scale "x", :field "x"}
                                 :width {:scale "x", :band true, :offset -1}
                                 :y {:scale "y", :field "y"}
                                 :y2 {:scale "y", :value 0}}
                         :update {:fill {:value "steelblue"}}
                         :hover {:fill {:value "red"}}}}]})

#_ (->> (random-data 100 500)
     bar-chart
     )


(defn stocks-data
  []
  (stocks/stocks-data))


  
