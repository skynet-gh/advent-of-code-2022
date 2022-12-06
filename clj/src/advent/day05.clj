(ns advent.day05
  (:require
    [clojure.java.io :as io]
    [clojure.string :as string])
  (:gen-class))


(defn parse []
  (let [[stacks procedure] (-> "day05.txt" io/resource slurp (string/split #"\n\n"))
        rows (drop-last (string/split-lines stacks))]
    {:stacks (reduce
               (fn [agg row]
                 (reduce
                   (fn [agg x]
                     (let [[i col] x
                           [_ crate] col]
                       (if (= \space crate)
                         agg
                         (update agg i (fnil #(conj % crate) [])))))
                   agg
                   (->> row
                        (partition-all 4)
                        (map-indexed vector)
                        doall)))
               {}
               rows)
     :procedures
     (mapv
       (fn [p]
         (let [[_ n source dest] (re-find #"move (\d+) from (\d+) to (\d+)" p)]
           {:n (int (parse-long n))
            :source (int (dec (parse-long source)))
            :dest (int (dec (parse-long dest)))}))
       (string/split-lines procedure))}))


(defn rearrange [stacks procedures reverse?]
  (if (seq procedures)
    (let [{:keys [n source dest]} (first procedures)
          removed (take n (get stacks source))]
      (recur
        (-> stacks
            (update source #(drop n %))
            (update dest #(concat (if reverse?
                                    (reverse removed)
                                    removed)
                                  %)))
        (rest procedures)
        reverse?))
    stacks))

(defn part1 [{:keys [stacks procedures]}]
  (let [stacks (rearrange stacks procedures true)]
    (string/join (map first (vals (sort-by first stacks))))))


(defn part2 [{:keys [stacks procedures]}]
  (let [stacks (rearrange stacks procedures false)]
    (string/join (map first (vals (sort-by first stacks))))))


(defn -main [& _]
  (let [parsed (parse)]
    (println "Day 05")
    (println " Part 1:" (part1 parsed))
    (println " Part 2:" (part2 parsed))))
