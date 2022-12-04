(ns advent.2021
  (:require
    [clojure.java.io :as io]
    [clojure.string :as string])
  (:import
    (java.math BigInteger))
  (:gen-class))


(defn get-lines []
  (->> "2021-day03.txt" io/resource slurp string/split-lines (remove string/blank?)))

(defn ge [lines]
  (let [total (count lines)
        one-counts (reduce
                     (fn [agg line]
                       (vec
                         (map-indexed
                           (fn [i c]
                             (+ (if (= \1 c)
                                  1 0)
                                (or (get agg i)
                                    0)))
                           line)))
                     []
                     lines)
        gamma (->> one-counts
                   (mapv
                    (fn [ones]
                      (if (< ones (- total ones))
                        1 0)))
                   string/join)
        epsilon (->> one-counts
                     (mapv
                      (fn [ones]
                        (if (< ones (- total ones))
                          0 1)))
                     string/join)]
    {:gamma gamma
     :epsilon epsilon}))
(defn mult [n1 n2]
  (long
    (* (BigInteger. n1 2)
       (BigInteger. n2 2))))
(defn part1 [lines]
  (let [{:keys [gamma epsilon]} (ge lines)]
    (mult gamma epsilon)))

(defn eliminate [all-lines k]
  (loop [i 0
         lines all-lines]
    (let [criteria (get (ge lines) k)
          remaining (->> lines
                         (filter
                           (fn [line]
                             (= (get criteria i)
                                (get line i)))))]
      (if (<= (count remaining) 1)
        (first remaining)
        (recur (inc i) remaining)))))

(defn part2 [lines]
  (let [oxygen (eliminate lines :gamma)
        co2 (eliminate lines :epsilon)]
    (mult oxygen co2)))

(defn -main [& _]
  (let [lines (get-lines)]
    (println "Day 03")
    (println " Part 1:" (part1 lines))
    (println " Part 2:" (part2 lines))))
