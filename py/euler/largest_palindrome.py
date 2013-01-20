#!/usr/bin/python
# -*- cording: utf-8 -*-

# Largest palindrome product
# Problem 4
#
# A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 x 99.
#
# Find the largest palindrome made from the product of two 3-digit numbers.


def palindrome(n):
    s = str(n)
    if s == s[::-1]:
        return True
    return False

def calc_v1(digit_len):
    min_num, max_num = int("9" + "0" * (digit_len - 1)), int("9" * digit_len) 

    max_palindrome, x, y = 0, 0, 0
    for i in xrange(max_num, min_num, -1):
        for j in xrange(max_num, min_num, -1):
            product = i * j
            if palindrome(product) and product > max_palindrome:
                max_palindrome, x, y = product, i, j
    return max_palindrome, x, y

def calc_v2(digit_len):
    min_num, max_num = int("9" + "0" * (digit_len - 1)), int("9" * digit_len)
    max_multiple_11 = max_num
    for i in xrange(max_num, min_num, -1):
        if i % 11 == 0:
            max_multiple_11 = i
            break

    max_palindrome, x, y = 0, 0, 0
    for i in xrange(max_num, min_num, -1):
        for j in xrange(max_multiple_11, min_num, -11):
            product = i * j
            if palindrome(product) and product > max_palindrome:
                max_palindrome, x, y = product, i, j
    return max_palindrome, x, y

funcs = dict([(name, func) for name, func in locals().items() if name.startswith("calc")])

def main():
    import sys
    name, version, n = sys.argv
    if funcs.has_key(version):
        print funcs[version](int(n))
    else:
        print "usage:", name, "<" + "|".join(sorted(funcs.keys())) + ">", "integer"

if __name__ == "__main__":
    main()


