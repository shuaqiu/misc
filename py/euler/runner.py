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


def run(module_name, func_name, param):
    func = get_func(module_name, func_name)
    if func is None:
        print "not such function", func_name, "in module", module_name

    if param.isdigit():
        return func(int(param))
    else:
        import os
        if os.path.isfile(param):
            with open(param) as f:
                rtn = func(f)
            f.close()
            return rtn


def main():
    import sys
    try:
        self, module_name, func_name, param = sys.argv
    except ValueError:
        print "usage:", self, module_name, func_name, "parameter"

    print run(module_name, func_name, param)

if __name__ == "__main__":
    main()
