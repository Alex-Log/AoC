(ns main.util
  (:require [clojure.string :as s]
            [clojure.java.io :as io]))

(defn read-input-day
  "Read the filename 's'.txt from resources, applying 'parse-line-fn' to all lines, if indicated"
  [file-name & [parse-line-fn]]
  (let [fname (io/file (io/resource file-name)) 
        ret   (s/split-lines (slurp fname))
        ret   (if parse-line-fn (map parse-line-fn ret) ret)]
    ret)) 

(defn parse-split-string [x]
  (s/split x #" "))