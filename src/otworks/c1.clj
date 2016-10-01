(ns otworks.c1
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples gen-inst]]))

(get-samples "~/Code/overtone-works/samples" ["s1" "s2"])

(gen-inst "tri" ["free-verb" "lf-tri"])
(gen-inst "smplr" ["free-verb" "lpf" "warp1-lfo"])

(def tri2 (tri :freq 72 :amp 0.4 :att 20))

(ctl tri2 :freq 59)

(def s2-smplr (smplr s2 :pointer 2.3 :freq-scale 0.6 :window-size 13 :amp 0.9))

(ctl s2-smplr :freq-scale 0.575 :window-size 4 :pointer 2.29)

(ctl s2-smplr :gate 0)
