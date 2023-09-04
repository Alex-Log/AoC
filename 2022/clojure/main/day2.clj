(ns main.day2
  (:require [clojure.string :as s]))


;;
;; utility
;;

(defn read-input-day
  "Read the filename 's'.txt from resources, applying 'parse-line-fn' to all lines, if indicated"
  [s & [parse-line-fn]]
  (let [fname (str "resources/" s ".txt")
        ret   (s/split-lines (slurp fname))
        ret   (if parse-line-fn (map parse-line-fn ret) ret)]
    ret))


(defn parse-split-string [x]
  (s/split x #" "))

;;d2p1
;; rock = 1, paper = 2, scissors = 3, win = 6, lost = 0, draw = 3
;; a = rock, b = paper, c = scissors
;; x = rock, y = paper, z = scissors

(def plays ["A" "B" "C" "X" "Y" "Z"])

(defn get-choice [x]
  (-> (.indexOf plays x)
      (mod 3)))

(defn choice-points [x]
  (inc (get-choice x)))

(defn is-win? [x y]
  (= (mod (inc x) 3) y))

(defn is-draw? [x y]
  (= x y))

(defn parse-round-score [x y]
  (cond
    (is-win? x y) 6
    (is-draw? x y) 3
    :else 0))

(parse-round-score 1 0)

(defn d2p1 []
  (->> (read-input-day "day2")
       (map each-round-choice)
       (map get-total-value)
       (reduce +)))

(defn each-round-choice [input]
  (->> input
       parse-split-string
       (map #(->> %
                  str
                  get-choice))))

(get-choice "B")

(defn get-total-value [input]
  (let [x (first input)
        y (last input)]
    (+ 
     (parse-round-score x y)
     (inc y))))


(d2p1)

(map each-round-choice)
(map get-total-value)
(reduce +)

(defn get-play [input]
  (let [x (first input)
        y (last input)]
    (case y
      0 (list x (mod (dec x) 3))
      2 (list x (mod (inc x) 3))
      (list x x))))

(defn d2p2 []
  (->> (read-input-day "day2")
       (map each-round-choice)
       (map get-play)
       (map get-total-value)
       (reduce +)))


(d2p2)

(->> (d2p2)
     (map get-play)
     (map get-total-value)
     (reduce +))

(mod -1 3)



;;read text, take each part and split, get round score + choice score
;; then map-apply


(let [text (slurp "resources/test.txt")]
    (->> text
         (s/split-lines)
         (map #(->> %
                    (clojure.string/split-lines)
                    (map tst)
                    (clojure.string/join " ")))
         (clojure.string/join "\n")))

(defn tst [input]
  (if (re-matches #"Z.*" input) (str input "A") input))

(if (re-matches #"Z.*" %) (str % "A") %)
(re-matches #"Z.*" "Hello")

(->> (range)
     (map #(* % %))
     (filter even?)
     (take 10)
     (reduce +))

(defn process-file [file-path]
  (let [text (slurp file-path)]
    (->> text
         (clojure.string/split % #"\n")
         (map #(->> %
                    (clojure.string/split % #"\s")
                    (map (if (re-matches #"^Z" %) % (str % "A")))
                    (clojure.string/join " ")))
         (clojure.string/join "\n"))))
