(ns main.day8
  (:require [clojure.string :as s]
            [main.util :as util]))

(def x (util/read-input-day-og "day8sample"))

(defn tst [x]
  (->> (map #(Integer/parseInt %))))

(map #(tst %) x)

(map #(into [] %) x)

(map #(map println %) x)

(into [] (map vec x))

(Integer/parseInt "1")

(defn string-to-vector-int [x]
  (vec (map #(Integer/parseInt (str %)) (s/split x #""))))

(map Integer/parseInt (s/split "232" #""))

(->> (util/read-input-day-og "day8sample")
     (->> (Integer/parseInt %)))

(->> (util/read-input-day-og "day8sample")
     (map string-to-vector-int)
     (vec))

(def y 
  (->> (util/read-input-day-og "day8sample")
       (map string-to-vector-int)
       (vec)))


y

(defn rotate-90-degrees [x]
  (vec (apply map vector x)))

(rotate-90-degrees (rotate-90-degrees y))


(get-in y [0 1])

(def z `(3 1 3 7 3))

z

(reverse z)

()