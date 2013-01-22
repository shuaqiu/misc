#!/usr/bin/python
# -*- coding: utf-8 -*-

# Consecutive prime sum
# Problem 50
#
# The prime 41, can be written as the sum of six consecutive primes:
#
#     41 = 2 + 3 + 5 + 7 + 11 + 13
# This is the longest sum of consecutive primes that adds to a prime below
# one-hundred.
#
# The longest sum of consecutive primes below one-thousand that adds to a prime,
# contains 21 terms, and is equal to 953.
#
# Which prime, below one-million, can be written as the sum of the most
# consecutive primes?


def calc_max_len(n, primes):
    s = 0
    for max_len, p in enumerate(primes):
        s += p
        if s > n:
            return max_len


def calc_v1(n):
    import integer
    primes = sorted(list(integer.primes(n)))
    primes_len = len(primes)

    max_len = calc_max_len(n, primes)
    print "max_len:", max_len

    for count in xrange(max_len - 1, 2, -1):
        for i in xrange(primes_len - count):
            sum_primes = sum(primes[i:(i + count)])
            if sum_primes > n:
                break
            if sum_primes in primes:
                return sum_primes, count, primes[i:(i + count)]
    return None
