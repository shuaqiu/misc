#!/usr/bin/python
# -*- coding: utf-8 -*-

# Multiples of 3 and 5
# Problem 1
#
# If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
#
# Find the sum of all the multiples of 3 or 5 below 1000.

def calc_v1(n):
    return sum([x for x in xrange(1, n) if x % 3 == 0 or x % 5 == 0])

def calc_v2(n):
    return sum(set([x for x in xrange(0, n, 3)]) | set([x for x in xrange(0, n, 5)]))

funcs = dict([(name, func) for name, func in locals().items() if name.startswith("calc")])

def main():
    import sys
    name, version, n = sys.argv
    #try:
    #    func = eval(version, {})
    #    print "version:", version, "result:",
    #    print func(int(n))
    #except NameError:
    #    print "undefined version of method:", version, "at", name
    if funcs.has_key(version):
        print "version:", version, "result:",
        print funcs[version](int(n))
    else:
        print "undefined version of method:", version, "at", name
        print "usage: python", name, "<" + "|".join(sorted(funcs.keys())) + ">", "<integer>"

if __name__ == "__main__":
    main()
