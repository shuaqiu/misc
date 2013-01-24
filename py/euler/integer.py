#!/usr/bin/python
# -*- coding: utf-8 -*-

#


def prime(n):
    '''
    ' Test a number whether is a prime
    '
    ' The simplest primality test is as follows: Given an input number n, check
    ' whether any integer m from 2 to n/2 divides n. If n is divisible by any m
    ' then n is composite, otherwise it is prime.
    '
    ' However, rather than testing all m up to n/2, it is only necessary to test
    ' m up to sqrt(n): if n is composite then it can be factored into two values,
    ' at least one of which must be less than or equal to sqrt(n).
    '
    ' The efficiency can also be improved by skipping all even m except 2, since
    ' if any even number divides n then 2 does.
    '
    ' It can be improved further by observing that all primes are of the form
    ' 6k - 1 or 6k + 1, with 2 and 3 being the only exceptions. This is because
    ' all integers can be expressed as (6k + i) for some integer k and for
    ' i = -1, 0, 1, 2, 3, or 4; 2 divides (6k + 0), (6k + 2), (6k + 4); and 3
    ' divides (6k + 3). So a more efficient method is to test if n is divisible
    ' by 2 or 3, then to check through all the numbers of form
    ' 6k - 1 or 6k + 1 <= sqrt(n) . This is 3 times as fast as testing all m.
    '''
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


def primes(n):
    '''
    ' use Sieve of Eratosthenes, to find all primes from 2 to n
    ' see http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
    '
    ' To find all the prime numbers less than or equal to a given integer n by
    ' Eratosthenes' method:
    ' 1. Create a list of consecutive integers from 2 to n: (2, 3, 4, ..., n).
    ' 2. Initially, let p equal 2, the first prime number.
    ' 3. Starting from p, count up in increments of p and mark each of these
    '    numbers greater than p itself in the list. These will be multiples of p:
    '    2p, 3p, 4p, etc.; note that some of them may have already been marked.
    ' 4. Find the first number greater than p in the list that is not marked.
    '    If there was no such number, stop. Otherwise, let p now equal this
    '    number (which is the next prime), and repeat from step 3.
    ' When the algorithm terminates, all the numbers in the list that are not
    ' marked are prime.
    '''
    prime_set = set(range(2, n))
    import math
    for i in xrange(2, int(math.sqrt(n)) + 1):
        if i in prime_set:
            prime_set -= set(range(i * i, n, i))
    return prime_set


def factorize(n):
    '''
    ' Find all prime factor for number n
    '
    '
    '''
    if n <= 3:
        return [n]

    factor = []
    i = 2
    while i <= n:
        while n % i == 0:
            factor.append(i)
            n = n / i
        i += 1
    return factor


def factors(n):
    '''
    '''
    import math
    small = set([x for x in xrange(2, int(math.sqrt(n)) + 1) if n % x == 0])
    big = set([n / x for x in small])
    return small | big | set([1, n])


def prime_factors(n):
    return [x for x in factors(n) if prime(x)[0]]
