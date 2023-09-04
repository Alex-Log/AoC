(ns main.day3
  (:require [clojure.string :as s]
            [clojure.set :as set]))

(defn read-input-day
  "Read the filename 's'.txt from resources, applying 'parse-line-fn' to all lines, if indicated"
  [s & [parse-line-fn]]
  (let [fname (str "resources/" s ".txt")
        ret   (s/split-lines (slurp fname))
        ret   (if parse-line-fn (map parse-line-fn ret) ret)]
    ret))


(defn parse-split-string [x]
  (s/split x #" "))



(defn char-range [start end]
  (->> (range (int start) (inc (int end)))
       (map char)))

(defn generate-priority [] 
  (concat (char-range \a \z) (char-range \A \Z)))

(defn get-priotiry-val [input]
  (->> input
       (.indexOf (generate-priority))
       inc))

(defn find-union [input]
  (let [x (first input)
        y (last input)]
    (set/intersection x y)))


(->> (read-input-day "day3")
     (map #(->> %
                (partition-all (/ (count %) 2))
                (map set)))
     (map find-union)
     (map seq)
     (map #(->> %
                (map get-priotiry-val)))
     flatten
     (reduce +)
     )

(set/intersection (set "vJrwpWtwJgWr") (set "hcsFMMfFFhFp"))

(get-priotiry-val \a)

(defn triple-intersect [input]
  (let [a (first input)
        b (second input)
        c (last input)]
    (set/intersection a b c)))

(def t-data "vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg")

(->> (read-input-day "day3")
     (partition-all 3)
     (map #(->> %
                (map set)))
     (map triple-intersect)
     (map seq)
     (map #(->> %
                (map get-priotiry-val)))
     flatten
     (reduce +)
     )
