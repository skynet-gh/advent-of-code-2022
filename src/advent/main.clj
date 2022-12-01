(ns advent.main
  (:require
    [clojure.java.io :as io]
    [clojure.string :as string])
  (:gen-class))


(defn day1 []
  (let [elves (-> "day01.txt" io/resource slurp (string/split #"\n\n"))]
    (->> elves
         (map
           (fn [foods]
             (->> (string/split foods #"\n")
                  (map parse-long)
                  (reduce + 0)))))))
(defn day1-part1 []
  (reduce max (day1)))

(defn day1-part2 []
  (->> (day1)
       sort
       reverse
       (take 3)
       (reduce + 0)))

(defn -main [& _]
  (println "Advent of code 2022")
  (println "Day 01")
  (println " Part 1:" (day1-part1))
  (println " Part 2:" (day1-part2)))
