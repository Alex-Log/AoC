(ns main.day1
  (:require [clojure.string :as s]
            [main.util :as util]))

(defn parse-int-or-zero [x]
  (map read-string (replace `{"" "0"} x)))

(def calories
  (->> (util/read-input-day "day1sample.txt")
       (parse-int-or-zero)
       (partition-by zero?)
       (map #(apply + %))))

(defn d1p1 [calories]
  (apply max calories))

(defn d1p2 [calories]
  (->> calories
       (sort >)
       (take 3)
       (reduce +)))

(defn run [] 
  (println "day1 part1: " (d1p1 calories))
  (println "day1 part2: " (d1p2 calories)))

(run)