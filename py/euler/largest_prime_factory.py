#!/usr/bin/python
# -*- coding: utf-8 -*-

# Largest prime factor
# Problem 3
#
# The prime factors of 13195 are 5, 7, 13 and 29.
#
# What is the largest prime factor of the number 600851475143 ?


def calc_v1(n):
    '''
    ' Wrong:
    '
    ' this function is wrong for some number.
    '''
    import integer
    for i in xrange(2, n + 1):
        if n % i == 0:
            parner = n / i
            if integer.prime(parner)[0]:
                return parner


def calc_v2(n):
    '''
    ' each composite number n can be expreseed as a group of primes.
    ' for example: 60 = 2 * 2 * 3 * 5
    '''
    i, p = 2, 2
    while i <= n:
        while n % i == 0:
            n = n / i
        i, p = i + 1, i
    return p


funcs = dict([(name, func) for name, func in locals().items() if name.startswith("calc")])


def main():
    import sys
    name, version, n = sys.argv
    if version in funcs:
        print funcs[version](int(n))
    else:
        print "usage: python", name, "<" + "|".join(sorted(funcs.keys())) + ">", "integer"

if __name__ == "__main__":
    main()
