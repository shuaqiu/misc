#!/usr/bin/python
# -*- coding: utf-8 -*-

#


def prob1():
    return 2 << 37


def prob2():
    def trans(s, shift):
        t = ""
        for c in s:
            if str.isalpha(c):
                ch = ord(c) + shift
                if ch > ord("z"):
                    ch -= 26
                c = chr(ch)
            t += c
        return t

    return trans("map", 2)


def get_content(page):
    import httplib
    import StringIO

    conn = httplib.HTTPConnection("www.pythonchallenge.com")
    conn.request("GET", page)
    resp = conn.getresponse()

    data = resp.read()

    sio = StringIO.StringIO(data)
    return sio.readlines()


def prob3():
    lines = get_content("/pc/def/ocr.html")
    if lines is None:
        return (False, "could not read the content")

    res = ""
    for line in lines[37:1256]:
        for c in line:
            if str.isalpha(c):
                res += c
    return res


def prob4():
    lines = get_content("/pc/def/equality.html")
    if lines is None:
        return (False, "not content")

    import re
    contents = "".join(lines[21:1271]).replace("\n", "")
    reg = r"[a-z][A-Z]{3}[a-z][A-Z]{3}[a-z]"
    found = re.findall(reg, contents)

    return "".join([s[4] for s in found])
