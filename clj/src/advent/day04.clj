(ns advent.day04
  (:require
    [clojure.java.io :as io]
    [clojure.string :as string])
  (:gen-class))


(defn get-assignments []
  (->> "day04.txt" io/resource slurp string/split-lines))

(defn part1 [assignments]
  (->> assignments
       (filter
         (fn [assignment]
           (let [[_ a1 a2 b1 b2] (re-find #"(\d+)-(\d+),(\d+)-(\d+)" assignment)]
             (or (and (<= (parse-long a1)
                          (parse-long b1))
                      (>= (parse-long a2)
                          (parse-long b2)))
                 (and (>= (parse-long a1)
                          (parse-long b1))
                      (<= (parse-long a2)
                          (parse-long b2)))))))
       count))

(defn part2 [assignments]
  (->> assignments
       (filter
         (fn [assignment]
           (let [[_ a1 a2 b1 b2] (re-find #"(\d+)-(\d+),(\d+)-(\d+)" assignment)]
             (or (and (<= (parse-long a2)
                          (parse-long b1))
                      (>= (parse-long a1)
                          (parse-long b2)))
                 (and (>= (parse-long a2)
                          (parse-long b1))
                      (<= (parse-long a1)
                          (parse-long b2)))))))
       count))

(defn -main [& _]
  (let [assignments (get-assignments)]
    (println "Day 04")
    (println " Part 1:" (part1 assignments))
    (println " Part 2:" (part2 assignments))))
