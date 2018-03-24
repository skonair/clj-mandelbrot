(ns clj-mandelbrot.complex-test
  (:require [clojure.test :refer :all]
            [clj-mandelbrot.complex :refer :all]))

(deftest a-test
  (testing "value of a complex number"
    (is (= (Math/sqrt 18) (value (make-complex 3 3))))))



