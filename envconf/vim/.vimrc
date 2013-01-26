" {{{ general setting
" set tab and indent space number
set expandtab
set shiftwidth=4
set tabstop=4
set autoindent
set bs=2
set number
set numberwidth=3
" }}}


" use 256 colors
set t_Co=256


" {{{ plugins management
"
" {{{{ if the pathogen is placed at ~/.vim/bundle
" runtime bundle/vim-pathogen/autoload/pathogen.vim
" call pathogen#infect()
" }}}}
"
" {{{{ if the pathogen is placed at somewhere other than ~/.vim
source ~/misc/envconf/vim/bundle/vim-pathogen/autoload/pathogen.vim
execute pathogen#infect('bundle/{}', '~/misc/envconf/vim/bundle/{}')
" }}}}

call pathogen#helptags()
" }}}


" set fileencodings=utf-8,gb2312,gbk,gb18030
" set termencoding=utf-8


" {{{ color scheme
" colorscheme murphy
colorscheme railscasts
" colorscheme guardian

" set background=dark
" colorscheme solarized
" }}}


" {{{ python mode settings
"
" {{{{ disable python folding
let g:pymode_folding=0
" }}}}

" }}}
