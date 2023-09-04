(ns main.day1
  (:require [clojure.string :as s]))

;;
;; Utility code
;;

(defn read-input-day
  "Read the text files under resources by passing in name"
  [file-name]
  (let [file-path (str "resources/" file-name ".txt")]
    (s/split-lines (slurp file-path))))

(defn parse-int [s] (Integer/valueOf s))

(defn parse-opt-int [s]
  (if (or (nil? s) (= (s/trim s) ""))
    nil
    (parse-int s)))

(defn parse-int-or-zero [x]
  (map read-string (replace `{"" "0"} x)))

(defn map-apply [f xs]
  (map #(apply f %) xs))

;;Day1: Calorie Counting

(def input-1-day-1 "1000
2000
3000

4000

5000
6000

7000
8000
9000

10000")

input-1-day-1

(read-input-day "day1")



(defn read-data [filename]
  (->> (read-input-day filename)
       (parse-int-or-zero)))

(defn partition-by-zero [input]
  (partition-by zero? input))

(defn calories []
  (->> (read-data "day1")
       partition-by-zero
       (map-apply +)))

(calories)

(defn d1p1 []
 (apply max (calories)))

(d1p1)

(defn d1p2 []
  (->> (calories)
       (sort >)
       (take 3)
       (reduce +)))

(d1p2)


(reduce + `(1 2 3))

(s/split-lines  input-1-day-1)

(map (fn [x] (reduce + x)) (remove (fn [x] (= 0 (first x))))
         (partition-by #(= 0 %) (map read-string
                                     (replace `{"" "0"} (s/split-lines  input-1-day-1)))))


(defn d1-partition-cal [input] (map (fn [x] (reduce + x)) (remove (fn [x] (= 0 (first x)))
                                                                  (partition-by #(= 0 %) (map read-string
                                                                                              (replace `{"" "0"} (s/split-lines  input)))))))
(d1-partition-cal input-1-day-1)

(defn day1 [input] (apply max (d1-partition-cal input)))


(day1 input-1-day-1)


(reduce + (take 3 (sort > (d1-partition-cal input-test-day-1))))


(def test-output (partition-by s/blank? (s/split-lines  input-1-day-1)))

test-output

(remove (fn [x]
          (s/blank? (first x))) test-output)


