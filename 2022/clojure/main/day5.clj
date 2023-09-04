(ns main.day5
  (:require [clojure.string :as s]
            [main.util :as utl]
            [clojure.set :as set]))

(->> (s/split "move 1 from 2 to 1" #"\s+")
     (apply list)
     (partition-all 2)
     (reduce #(assoc %1 (first %2) (map read-string (rest %2))) {}))

(defn take-stack-cols [s]
  (->> s
       (map #(re-seq #"\w|    " %))))

(defn transpose [mat]
  (->> mat
       (apply map list)
       (map reverse)
       (map #(remove s/blank? %))))

(defn group-by-stacks [stacks]
  (->> stacks
       (reduce #(assoc %1 (read-string (first %2)) (rest %2)) (sorted-map))))

(defn read-move [line]
  (->> line
       (re-seq #"\d")
       (map #(Integer/parseInt %))))

(defn read-stacks-moves [raw-stacks _ raw-moves]
  [(->> raw-stacks
       take-stack-cols
       transpose
       group-by-stacks)
   (map read-move raw-moves)])


(defn process-move [stacks [n from to]]
  (let [to-data (get stacks to)
        take-from (reverse (take-last n (get stacks from)))
        new-from (drop-last n (get stacks from))
        ]
    (assoc stacks from new-from to (concat to-data take-from))))

(defn process-moves [stacks moves]
  (if (= 0 (count moves))
    stacks
    (recur (process-move stacks (first moves)) (rest moves))))

(defn take-top-stack [stack]
  (->> stack
       (map (fn [[key val]] (last val)))))

(defn d5p1 []
  (let [[stacks moves]
      (->> (utl/read-input-day "day5")
           (partition-by #(= "" %))
           (apply read-stacks-moves))]
  (->> (process-moves stacks moves))))
     
(d5p1)

;; use to break apart (map #(re-seq #"\[\w\]|    |[0-9]" %))
;; answer should be CVCWCRTVQ figure it out later