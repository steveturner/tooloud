(ns tooloud.rhythmic
  (:use [overtone.live]))

(defsynth kicki [out-bus 0 bpm 140 kick-vol 3 v 2]
   (let [kickenv (decay (t2a (demand (impulse:kr (/ bpm 30)) 0 (dseq [1 0 1 0 0 1 1 0 1 0 0 1 1 0 1 0] INF))) 0.7)
         kick (* (* kickenv 7) (sin-osc (+ 40 (* kickenv kickenv kickenv 200))))
         kick (clip2 kick 3)]
     
   (out out-bus    (* v (clip2 (+ (* kick-vol kick)) 1)))))

(defsynth kickii [out-bus 0 bpm 140 kick-vol 3 v 2]
   (let [kickenv (decay (t2a (demand (impulse:kr (/ bpm 30)) 0 (dseq [0 1 0 1 0 0 1 1 0 1 0 0 1 1 0 1] INF))) 0.7)
         kick (* (* kickenv 7) (sin-osc (+ 40 (* kickenv kickenv kickenv 200))))
         kick (clip2 kick 3)]
     
   (out out-bus    (* v (clip2 (+ (* kick-vol kick)) 1)))))

(defsynth kickiii [out-bus 0 bpm 140 kick-vol 3 v 2]
   (let [kickenv (decay (t2a (demand (impulse:kr (/ bpm 30)) 0 (dseq [1 1 0 1 0 11 1 0 1 0 0 0 1 0 0] INF))) 0.7)
         kick (* (* kickenv 7) (sin-osc (+ 40 (* kickenv kickenv kickenv 200))))
         kick (clip2 kick 3)]

   (out out-bus    (* v (clip2 (+ (* kick-vol kick)) 1)))))

(defsynth snr [out-bus 0 bpm 140 snare-vol 2 v 2]
 (let [snr (* 8 (pink-noise [1 1]) (apply + (* (decay (impulse (/ bpm 240) 0.5) [0.4 2]) [1 0.05])))
       snr (+ snr (bpf (* 8 snr) 2000))
       snr (clip2 snr 2)]

   (out out-bus    (* v (clip2 (+ (* snare-vol snr)) 1)))))


