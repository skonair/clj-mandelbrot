(ns clj-mandelbrot.fraktal
  (require [clj-mandelbrot.complex :as cx]))

(defn iter [c maxd]
  (loop [z (cx/make-complex 0 0)
         i 0]
    (let [zn (cx/add (cx/pow z) c)
          zv (cx/value zn)]
      (cond
        (>= zv 2) i
        (>= i maxd) i
        :else (recur zn (inc i))))))
