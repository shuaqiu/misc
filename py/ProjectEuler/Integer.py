#!/usr/bin/python
# -*- coding: utf-8 -*-

#

def prime(n):
    if n <= 1:
        return False, n, n
    elif n == 2 or n == 3:
        return True, n, n
    elif n % 2 == 0:
        return False, n, 2
    elif n % 3 == 0:
        return False, n, 3

    import math
    k = 1
    sqrtn = math.sqrt(n)
    while 6 * k - 1 <= sqrtn:
        if n % (6 * k - 1) == 0:
            return False, n, 6 * k - 1
        elif n % (6 * k + 1) == 0:
            return False, n, 6 * k + 1
        k += 1
    return True, n, k

def factors(n):
    import math
    small = [x for x in xrange(2, int(math.sqrt(n))) if n % x == 0]
    big = [n / x for x in small]
    small.extend(big)
    return small

def prime_factors(n):
    return [x for x in reversed(sorted(factors(n))) if prime(x)[0] == True]
