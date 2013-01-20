#!/usr/bin/python
# -*- coding: utf-8 -*-

# Sum square difference
# Problem 6
#
# The sum of the squares of the first ten natural numbers is,
#
#    1 ** 2 + 2 ** 2 + ... + 10 ** 2 = 385
# The square of the sum of the first ten natural numbers is,
#
#    (1 + 2 + ... + 10) ** 2 = 55 ** 2 = 3025
# Hence the difference between the sum of the squares of the first ten natural
# numbers and the square of the sum is 3025  385 = 2640.
#
# Find the difference between the sum of the squares of the first one hundred
# natural numbers and the square of the sum.


def sum_square(n):
    return sum([x * x for x in xrange(1, n + 1)])


def square_sum(n):
    mid = (n + 1) * n / 2
    return mid * mid


def calc_v1(n):
    return square_sum(n) - sum_square(n)


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
