(ns clj-mandelbrot.core
  (require [clj-mandelbrot.complex :as cx]
           [clj-mandelbrot.fraktal :as fr]
           [clj-mandelbrot.png :as png]
           )
  (:gen-class))

(def palette "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ")
(defn iter-char [i maxd]
  (.charAt palette (int (* i (/ (dec (count palette)) maxd)))))

(defn ic [i maxd]
  (-> i
      (fr/iter maxd)
      (iter-char maxd)))

(defn mandelbrot-as-chars
  ([xs ys xe ye xd yd maxd]
  (loop [x xs
         y ys
         s ""]
    (if (> y ye)
      s
      (if (>= x xe)
        (recur xs (+ y yd) (str s \newline))
        (recur (+ x xd) y (str s (ic (cx/make-complex x y) maxd )))))))
  ([] (mandelbrot-as-chars -2 -1 1 1 1/60 1/20 1000))
  )

(defn -main
  "Renders an ASCII-Art Mandelbrot Set"
  [& args]
  (cond
    (nil? args) (println (mandelbrot-as-chars))
    (= "png" (first args)) (png/draw-mandelbrot 450 300 1000)
    :else (println (mandelbrot-as-chars))
    ))
