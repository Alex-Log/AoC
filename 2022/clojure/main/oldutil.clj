(ns main.util
  (:require [clojure.string :as s]))

(defn read-input-day
  "Read the filename 's'.txt from resources, applying 'parse-line-fn' to all lines, if indicated"
  [s & [parse-line-fn]]
  (let [fname (str "resources/" s ".txt")
        ret   (s/split-lines (slurp fname))
        ret   (if parse-line-fn (map parse-line-fn ret) ret)]
    ret))

(defn read-input-day-og
  "Read the text files under resources by passing in name"
  [file-name]
  (let [file-path (str "../resources/" file-name ".txt")]
    (s/split-lines (slurp file-path))))


(defn parse-split-string [x]
  (s/split x #" "))