(ns main.day7
  (:require [clojure.string :as s]
            [main.util :as utl]))

(defn process-text-line [line]
  (cond
    (= line "$ ls") nil
    (s/starts-with? line "dir") nil
    (s/starts-with? line "$ cd ..") ")"
    (s/starts-with? line "$ cd ") "("
    :else (let [[size _] (s/split line #" ")] size)))

(defn process-raw-input [input]
  (->> input
       (map process-text-line)
       (filter identity)))

(->> (utl/read-input-day "day7-tst")
     (process-raw-input))

