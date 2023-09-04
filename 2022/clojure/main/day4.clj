(ns main.day4
  (:require [clojure.string :as s]
             [main.util :as utl]
            [clojure.set :as set]))



(defn parse-d4 [input]
  (->> (s/split input #"[,-]")
       (map read-string)
       (partition 2)))

(defn fully-contained? [x y]
  (and (>= (first x) (first y))
       (<= (second x) (second y))))

(defn fully-overlap? [[x y]]
  (or (fully-contained? x y)
      (fully-contained? y x)))


(defn d4p1 []
  (->> (utl/read-input-day "day4" parse-d4)
       (filter fully-overlap?)
       count))

(d4p1)

(defn generate-range [[x y]]
  (->> (range x (inc y))
       set))

(defn intersect-range [[x y]]
  (set/intersection
   (generate-range x)
   (generate-range y)))

(defn in-range? [input]
  (< 0 (count (intersect-range input))))

(in-range? '((2 4) (6 8)))

(->> (utl/read-input-day "day4" parse-d4)
     (filter in-range?)
     (count))