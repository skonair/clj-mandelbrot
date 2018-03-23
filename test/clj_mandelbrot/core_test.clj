(ns clj-mandelbrot.core-test
  (:require [clojure.test :refer :all]
            [clj-mandelbrot.core :refer :all]))

(deftest a-test
  (testing "value of a complex number"
    (is (= (Math/sqrt 18) (value (->Complex 3 3))))))



