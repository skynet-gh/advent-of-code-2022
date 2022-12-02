(ns advent.main
  (:require
    [clojure.java.io :as io]
    [clojure.string :as string])
  (:gen-class))


(defn day1 []
  (->> (-> "day01.txt" io/resource slurp (string/split #"\n\n"))
       (mapv
         (fn [foods]
           (->> foods
                string/split-lines
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


(defn day2 []
  (-> "day02.txt" io/resource slurp string/split-lines))
(def day2-part1-score
  {"A X" 4 "A Y" 8 "A Z" 3 "B X" 1 "B Y" 5 "B Z" 9 "C X" 7 "C Y" 2 "C Z" 6})
(defn day2-part1 [lines]
  (reduce + 0 (map day2-part1-score lines)))
(def day2-part2-score
  {"A X" 3 "A Y" 4 "A Z" 8 "B X" 1 "B Y" 5 "B Z" 9 "C X" 2 "C Y" 6 "C Z" 7})
(defn day2-part2 [lines]
  (reduce + 0 (map day2-part2-score lines)))

(defn -main [& _]
  (println "Advent of code 2022")
  (let [elves (day1)]
    (println "Day 01")
    (println " Part 1:" (day1-part1 elves))
    (println " Part 2:" (day1-part2 elves)))
  (let [lines (day2)]
    (println "Day 02")
    (println " Part 1:" (day2-part1 lines))
    (println " Part 2:" (day2-part2 lines))))
