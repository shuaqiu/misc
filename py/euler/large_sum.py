#!/usr/bin/python
# -*- coding: utf-8 -*-

# Large sum
# Problem 13
#
# Work out the first ten digits of the sum of the following one-hundred 50-digit
# numbers. (@see problem_13.data)


def calc_v1(file):
    s = sum([int(line) for line in file])
    return s
