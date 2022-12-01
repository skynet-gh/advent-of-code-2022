(ns build
  (:require
    [clojure.tools.build.api :as b]))


(def lib 'skynet/advent-of-code-2022)
(def version (format "0.1.%s" (b/git-count-revs nil)))
(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))
(def uber-file (format "target/%s.jar" (name lib)))

(def source-dirs ["src"])

(defn clean [_]
  (b/delete {:path "target"}))

(defn uber [_]
  (clean nil)
  (b/copy-dir {:src-dirs source-dirs
               :target-dir class-dir})
  (b/compile-clj {:basis basis
                  :src-dirs source-dirs
                  :class-dir class-dir})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis basis
           :main 'advent.main}))
