(ns advent.main
  (:require
    [clojure.java.io :as io]
    [clojure.string :as string])
  (:gen-class))


(defn day1 []
  (->> (-> "day01.txt" io/resource slurp (string/split #"\n\n"))
       (mapv
         (fn [foods]
           (->> (string/split foods #"\n")
                (map parse-long)
                (reduce + 0))))
       sort
       reverse))

(defn day1-part1 [sorted-elves]
  (last sorted-elves))

(defn day1-part2 [sorted-elves]
  (->> sorted-elves
       (take 3)
       (reduce + 0)))

(defn -main [& _]
  (println "Advent of code 2022")
  (let [elves (day1)]
    (println "Day 01")
    (println " Part 1:" (day1-part1 elves))
    (println " Part 2:" (day1-part2 elves))))
