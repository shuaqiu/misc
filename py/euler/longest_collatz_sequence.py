#!/usr/bin/python
# -*- coding: utf-8 -*-

# Longest Collatz sequence
# Problem 14
#
# The following iterative sequence is defined for the set of positive integers:
#
#     n -> n/2 (n is even)
#     n -> 3n + 1 (n is odd)
#
# Using the rule above and starting with 13, we generate the following sequence:
#
#     13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
# It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
#
# Which starting number, under one million, produces the longest chain?
#
# NOTE: Once the chain starts the terms are allowed to go above one million.


def collatz_seq(n):
    seq = []
    k = n
    while k != 1:
        seq.append(k)
        if k & 1 == 0:
            k = k >> 1
        else:
            k = 3 * k + 1
    seq.append(1)
    return seq


def calc_v1(m):
    remain_set = set(range(1, m))
    x, max_len = 0, 0

    for i in xrange(m - 1, 0, -1):
        if i not in remain_set:
            continue
        seq = collatz_seq(i)
        if len(seq) > max_len:
            x, max_len = i, len(seq)
        remain_set -= set(seq)
        if len(remain_set) == 0:
            print "remain set is empty at", i
            break
    print remain_set
    return x, max_len


def collatz_seq_v2(n, numbers):
    if n in numbers:
        return numbers[n]

    k = n
    if k & 1 == 0:
        k = k >> 1
    else:
        k = 3 * k + 1
    numbers[n] = collatz_seq_v2(k, numbers) + 1
    return numbers[n]


def calc_v2(m):
    '''
    ' much faster than v1
    '''
    numbers = {1: 1}
    for i in xrange(m - 1, 1, -1):
        collatz_seq_v2(i, numbers)

    x, max_len = 0, 0
    for i in xrange(m - 1, 0, -1):
        if numbers[i] > max_len:
            x, max_len = i, numbers[i]
    return x, max_len
