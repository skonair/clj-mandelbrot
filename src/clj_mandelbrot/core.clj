(ns clj-mandelbrot.core
  (:gen-class))

(defprotocol IComplex
  (add [c1 c2])
  (pow [c])
  (real [c])
  (imag [c])
  (value [c]))

(deftype Complex [^double r ^double i]
  IComplex
  (add [_ c]
    (Complex. (+ r (real c)) (+ i (imag c))))
  (pow [_]
    (Complex. (+ (* r r) (- (* i i)) )
              (* 2 r i)))
  (real [_] r)
  (imag [_] i)
  (value [_] (Math/sqrt (+ (* r r) (* i i)))))


(def MAXDEPTH 1000)

(defn iter [c]
  (loop [z (Complex. 0 0)
         i 0]
    (let [zn (add (pow z) c)
          zv (value zn)]
      (cond
        (> zv MAXDEPTH) i
        (> i MAXDEPTH) i
        :else (recur zn (inc i))))))

(def palette "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ")
(defn iter-char [i]
  (.charAt palette (int (* i (/ (dec (count palette)) MAXDEPTH)))))

(defn ic [i]
  (-> i
      iter
      iter-char))

(defn mandelbrot-as-chars []
  (loop [x -2
         y -1
         s ""]
    (if (> y 1)
      s
      (if (>= x 1)
        (recur -2 (+ y 1/20) (str s \newline))
        (recur (+ x 1/60) y (str s (ic (Complex. x y))))))))

(defn -main
  "Renders an ASCII-Art Mandelbrot Set"
  [& args]
  (println (mandelbrot-as-chars) ) )
