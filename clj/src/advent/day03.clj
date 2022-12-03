(ns advent.day03
  (:require
    [clojure.java.io :as io]
    [clojure.set :refer [intersection]]
    [clojure.string :as string])
  (:gen-class))


(defn get-rucksacks []
  (->> "day03.txt" io/resource slurp string/split-lines))

(defn priority [c]
  (let [n (int c)]
    (if (<= n 90)
      (- n 38)
      (- n 96))))

(defn part1 [rucksacks]
  (->> rucksacks
       (map
         (fn [rucksack]
           (let [half (quot (count rucksack) 2)
                 compartment1 (set (take half rucksack))
                 compartment2 (set (drop half rucksack))]
             (reduce + 0 (map priority (intersection compartment1 compartment2))))))
       (reduce + 0)))

(defn part2 [rucksacks]
  (->> rucksacks
       (partition 3)
       (map
         (fn [groups]
           (->> groups
                (map set)
                (apply intersection)
                first
                priority)))
       (reduce + 0)))

(defn -main [& _]
  (let [rucksacks (get-rucksacks)]
    (println "Day 03")
    (println " Part 1:" (part1 rucksacks))
    (println " Part 2:" (part2 rucksacks))))
