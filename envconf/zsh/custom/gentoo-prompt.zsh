#!/bin/zsh

## prompt
autoload -U promptinit
promptinit

t=`prompt -l | grep gentoo | wc -l`
[[ $t = 1 ]] && prompt gentoo

