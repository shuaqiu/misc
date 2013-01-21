#!/usr/bin/python
# -*- coding: utf-8 -*-

#


def get_func(module_name, func_name):
    if type(func_name) != str:
        print "unknow function name."
        return None

    module = __import__(module_name)
    if hasattr(module, func_name):
        return getattr(module, func_name)
    return None


def run_int(module_name, func_name, n):
    func = get_func(module_name, func_name)
    if func is None:
        print "not such function", func_name, "in module", module_name

    return func(n)


def run(module_name, func_name, param):
    if param.isdigit():
        return run_int(module_name, func_name, int(param))


def main():
    import sys
    try:
        self, module_name, func_name, param = sys.argv
    except ValueError:
        print "usage:", self, module_name, func_name, "parameter"

    print run(module_name, func_name, param)

if __name__ == "__main__":
    main()
