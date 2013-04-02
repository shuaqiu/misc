#!/usr/bin/python

# Factorial digit sum
# Problem 20
#
# n! means n x (n - 1) x ... x 3 x 2 x 1
#
# For example, 10! = 10 x 9 x ... x 3 x 2 x 1 = 3628800,
# and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
#
# Find the sum of the digits in the number 100!


# the known factorial
l = [0, 1]


def factorial(n):
   if n <= len(l) - 1:
       return l[n]
   r, i = l[-1], len(l)
   while i <= n:
       r, i = r * i, i + 1
       l.append(r)
   return r


def calc_v1(n):
    return sum([int(c) for c in str(factorial(n)) if c != '0'])
