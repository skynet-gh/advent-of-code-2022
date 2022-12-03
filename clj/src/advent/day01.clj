(ns advent.day01
  (:require
    [clojure.java.io :as io]
    [clojure.string :as string])
  (:gen-class))


(defn get-elves []
  (->> (-> "day01.txt" io/resource slurp (string/split #"\n\n"))
       (mapv
         (fn [foods]
           (->> foods
                string/split-lines
                (map parse-long)
                (reduce + 0))))
       sort
       reverse))

(defn part1 [sorted-elves]
  (last sorted-elves))

(defn part2 [sorted-elves]
  (->> sorted-elves
       (take 3)
       (reduce + 0)))

(defn -main [& _]
  (let [elves (get-elves)]
    (println "Day 01")
    (println " Part 1:" (part1 elves))
    (println " Part 2:" (part2 elves))))
