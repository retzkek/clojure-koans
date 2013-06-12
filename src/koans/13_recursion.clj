(defn is-even? [n]
  (if (= n 0)
    true
    (not (is-even? (dec n)))))

(defn is-even-bigint? [n]
  (loop [n   n
         acc true]
    (if (= n 0)
      acc
      (recur (dec n) (not acc)))))

(defn recursive-reverse [coll]
  (loop [result '()
         remain coll]
    (if (empty? remain)
      result
      (recur (conj result (first remain)) (rest remain)))))

; works for the first few, but kinda against the spirit of the problem,
; and chokes on 1000!
;(defn factorial [n]
;  (reduce * (range 1 (+ n 1))))

; works, but could be better
;(defn factorial [n]
; (loop [result 1N
;       i 1N
;         n n]
;    (if (> i n)
;      result
;      (recur (* result i) (inc i) n))))

(defn factorial [n]
  (loop [result 1N
         n n]
    (if (= n 0)
      result
      (recur (* result n) (dec n)))))

(meditations
  "Recursion ends with a base case"
  (= true (is-even? 0))

  "And starts by moving toward that base case"
  (= false (is-even? 1))

  "Having too many stack frames requires explicit tail calls with recur"
  (= false (is-even-bigint? 100003N))

  "Reversing directions is easy when you have not gone far"
  (= '(1) (recursive-reverse [1]))

  "Yet more difficult the more steps you take"
  (= '(5 4 3 2 1) (recursive-reverse [1 2 3 4 5]))

  "Simple things may appear simple."
  (= 1 (factorial 1))

  "They may require other simple steps."
  (= 2 (factorial 2))

  "Sometimes a slightly bigger step is necessary"
  (= 6 (factorial 3))

  "And eventually you must think harder"
  (= 24 (factorial 4))

  "You can even deal with very large numbers"
  (< 1000000000000000000000000N (factorial 1000N))

  "But what happens when the machine limits you?"
  (< 1000000000000000000000000N (factorial 100003N)))
