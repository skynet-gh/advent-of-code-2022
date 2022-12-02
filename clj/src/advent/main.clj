(ns advent.main
  (:require
    [advent.day01 :as day01]
    [advent.day02 :as day02])
  (:gen-class))


(defn -main [& args]
  (println "Advent of code 2022")
  (apply day01/-main args)
  (apply day02/-main args))
