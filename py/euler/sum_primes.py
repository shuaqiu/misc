#!/usr/bin/python
# -*- coding: utf-8 -*-

# Summation of primes
# Problem 10
#
# The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
#
# Find the sum of all the primes below two million.


def calc_v1(n):
    import integer
    return sum(integer.primes(n))
