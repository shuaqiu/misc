#!/usr/bin/python
# -*- coding: utf-8 -*-

# Smallest multiple
# Problem 5
#
# 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
#
# What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?


def list_remove(src, to_remove):
    for e in to_remove:
        if e in src:
            src.remove(e)


def calc_v1(n):
    import integer
    #factors = [integer.factorize(x) for x in xrange(n + 1, 2, -1)]
    factors = [integer.factorize(x) for x in xrange(2, n + 1)]

    l = []
    for f in factors:
        list_remove(l, f)
        l.extend(f)

    r = 1
    for i in l:
        r *= i
    return r


funcs = dict([(name, func) for name, func in locals().items() if name.startswith("calc")])


def main():
    import sys
    name, version, n = sys.argv
    if version in funcs:
        print funcs[version](int(n))
    else:
        print "usage:", name, "<" + "|".join(sorted(funcs.keys())) + ">", "integer"


if __name__ == "__main__":
    main()
