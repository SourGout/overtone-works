(ns otworks.c1
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples gen-inst]]))

(get-samples "~/Code/overtone-works/samples/" ["s1" "s2" "s3" "s4" "s5"])

(gen-inst "tri" ["free-verb" "lf-tri"])
(gen-inst "smplr" ["free-verb" "lpf" "warp1-lfo"])


(definst warp
  [buf 0 point 1 fs 1 ws 10 ebn -1 overlaps 1 wrr 0.0 interp 1 amp 1 att 1 rel 1 gate 1]
  (let [src
        (*
         (warp1 :bufnum buf :pointer point :freq-scale fs :window-size ws :envbufnum ebn :overlaps overlaps :window-rand-ratio wrr :interp interp)
         (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))]
    (* src amp)))


(definst warplfo
  [buf 0 point 1 fs 1 ws 10 ebn -1 overlaps 1 wrr 0.0 interp 1 amp 1 att 1 rel 1 gate 1]
  (let [src
        (*
         (warp1 :bufnum buf :pointer (+ 0.5 (* 0.1 (sin-osc:kr point))) :freq-scale fs :window-size ws :envbufnum ebn :overlaps overlaps :window-rand-ratio wrr :interp interp)
         (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))]
    (* src amp)))


(def warpl (warplfo s5 :ws 1 :interp 1 :att 1 :fs -1 :rel 5 :overlaps 5))

(def warpl2 (warplfo s4 :ws 0.05 :fs 5 :interp 4 :att 1 :rel 2 :wrr 1))

(def warpl3 (warplfo s5 :ws 0.1 :interp 2 :att 0.6 :rel 5 :fs 1.5 :pointer 1.2))

(ctl warpl3 :amp 1.5 :fs -1.5)

(ctl warpl3 :fs 1.5)

(ctl warpl :amp 1 :ws 0.6 :fs -0.5)

(ctl warpl2 :amp 2 :ws 5 :fs 1.1 :rel 1 :wrr 2) 

(ctl warpl :gate 0)

(ctl warpl2 :gate 0)

(ctl warpl3 :gate 0)

(stop)

;; (definst sinsynth
;;   [freq 440 amp 1]
;;   (* (sin-osc freq) amp))

;; (sinsynth :freq 220 :amp 0.5)

;; (def sinsynth1 (sinsynth :freq 220 :amp 0.5))
;; (ctl sinsynth1 :freq 480 :amp 0.7)

;; (definst sinsynth2                      
;;   [freq 440 amp 1 mix 1 room 0.8 damp 0.6]
;;    (* (free-verb
;;       :in (sin-osc freq)
;;       :mix mix :room room :damp damp)
;;       amp))
