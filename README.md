misc

====

some misc codes

---

### env setup

1. clone

  # at home, eg. /home/shuaqiu
  git clone --recursive https://github.com/shuaqiu/misc.git

2. install

  ln -s misc/envconf/zsh/.zshrc .zshrc
  ln -s misc/envconf/vim/.vimrc .vimrc
  ln -s misc/envconf/urxvt/.Xresources .Xresources


3. install for root

  # at /root
  ln -s /home/shuaqiu/misc misc
  # then run the install for root
