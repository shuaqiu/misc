PROMPT='%{$fg_no_bold[cyan]%}[%*]%{$fg_bold[magenta]%}%~$(git_prompt_info)
%{$fg_bold[magenta]%}%# %{$fg_no_bold[green]%}'
RPROMPT='%{$fg_bold[yellow]%}%n%{$fg_no_bold[green]%}'

ZSH_THEME_GIT_PROMPT_PREFIX="%{$fg_bold[yellow]%}["
ZSH_THEME_GIT_PROMPT_SUFFIX="%{$reset_color%}"
ZSH_THEME_GIT_PROMPT_DIRTY="*]"
ZSH_THEME_GIT_PROMPT_CLEAN="]"
