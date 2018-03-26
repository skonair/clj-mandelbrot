(ns clj-mandelbrot.png
  (require [clj-mandelbrot.complex :as cx]
           [clj-mandelbrot.fraktal :as fr])
  (import java.io.File)
  (import java.awt.Color)
  (import java.awt.image.BufferedImage)
  (import javax.imageio.ImageIO))

(defn get-raster [w h maxd]
  (for [y (range (inc h))
        x (range w)
        :let [
              fx (/ 3 w)
              fy (/ 2 h)
              i (fr/iter (cx/make-complex (- (* fx x) 2) (- (* fy  y) 1)) maxd )
              color (int (* (Math/log (inc i))  (/ 255 (Math/log maxd))))]
        ]
    [x y (- 255 color)]
    )
  )

(defn draw-mandelbrot [w h maxd]
  (let [bi (BufferedImage. w (inc h) BufferedImage/TYPE_INT_RGB)
        g (.createGraphics bi)]
    (doseq [[x y color] (get-raster w (inc h) maxd)]
      (.setColor g (Color. ^int color ^int (mod color 113) ^int (mod color 173)) )
      (.fillRect g x y 1 1))
    (ImageIO/write bi "png"  (File. "mandelbrot.png"))))
