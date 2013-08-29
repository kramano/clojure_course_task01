(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))

(defn has-attr? [k v]
  (fn [node] (= v ((attributes node) k))))

(defn has-tag-name? [tn]
  (fn [node] (= tn (tag node))))

(defn all-tags [data]
  (filter vector?
          (tree-seq vector? children data)))

(defn get-data [] (parse "clojure_google.html"))

(defn get-links []
" 1) Find all elements containing {:class \"r\"}.
  2) Extract href from the element :a.
  3) Return vector of all 10 links.
"
(let [data (parse "clojure_google.html")]
  (->> data
       (all-tags)
       (filter (has-attr? :class "r"))
       (mapcat children)
       (filter (has-tag-name? :a))
       (mapv #(:href (attributes %))))))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))
