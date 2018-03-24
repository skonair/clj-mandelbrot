(ns clj-mandelbrot.complex)

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

(defn make-complex [r i]
  (Complex. r i))
