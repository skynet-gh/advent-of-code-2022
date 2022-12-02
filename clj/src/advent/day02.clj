(ns advent.day02
  (:require
    [clojure.java.io :as io]
    [clojure.string :as string])
  (:gen-class))


(defn get-lines []
  (-> "day02.txt" io/resource slurp string/split-lines))
(def part1-score
  {"A X" 4 "A Y" 8 "A Z" 3 "B X" 1 "B Y" 5 "B Z" 9 "C X" 7 "C Y" 2 "C Z" 6})
(defn part1 [lines]
  (reduce + 0 (map part1-score lines)))
(def part2-score
  {"A X" 3 "A Y" 4 "A Z" 8 "B X" 1 "B Y" 5 "B Z" 9 "C X" 2 "C Y" 6 "C Z" 7})
(defn part2 [lines]
  (reduce + 0 (map part2-score lines)))


(defn -main [& _]
  (let [lines (get-lines)]
    (println "Day 02")
    (println " Part 1:" (part1 lines))
    (println " Part 2:" (part2 lines))))
