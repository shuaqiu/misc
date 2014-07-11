#!/bin/bash


sudo mkdir /usr/share/fonts/monaco
sudo cp MONACO.TTF /usr/share/font/monaco
 
cd /usr/share/fonts/monaco
sudo mkfontdir
sudo mkfontscale
