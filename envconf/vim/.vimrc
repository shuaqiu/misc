" {{{ general setting
" set tab and indent space number
set expandtab
set shiftwidth=4
set tabstop=4
" }}}

" use 256 colors
set t_Co=256

" {{{ plugins management
runtime bundle/vim-pathogen/autoload/pathogen.vim
call pathogen#infect()
" }}}

" set fileencodings=utf-8,gb2312,gbk,gb18030
" set termencoding=utf-8

" color scheme
" colorscheme murphy
colorscheme railscasts
" colorscheme guardian
" set background=dark
" colorscheme solarized


