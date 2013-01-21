#!/usr/bin/python
# -*- coding: utf-8 -*-

# Special Pythagorean triplet
# Problem 9
#
# A Pythagorean triplet is a set of three natural numbers, a  b  c, for which,
#
#    a * a + b * b = c * c

# For example, 3 * 3 + 4 * 4 = 9 + 16 = 25 = 5 * 5.
#
# There exists exactly one Pythagorean triplet for which a + b + c = 1000.
# Find the product abc.


def calc_v1(n):
    l = []
    for a in xrange(1, n / 3 + 1):
        for b in xrange(a + 1, n / 2 + 1):
            c = n - a - b
            if c > b and a * a + b * b == c * c:
                l.append((a * b * c, a, b, c))
    return l
