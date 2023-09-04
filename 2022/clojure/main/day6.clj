(ns main.day6
  (:require [clojure.string :as s]
            [main.util :as utl]))


(defn marker? [n sub-line]
  (= (count (set sub-line)) n))

(defn get-first-true [n xs]
  (first (keep-indexed #(if %2 (+ %1 n)) xs)))

(defn d6 [filename n]
  (->> (utl/read-input-day filename)
       first
       (partition n 1)
       (map #(marker? n %))
       (get-first-true n)))

(defn d6p1 [] (d6 "day6" 4))


(defn d6p2 [] (d6 "day6" 14))

(d6p1)

(d6p2)
