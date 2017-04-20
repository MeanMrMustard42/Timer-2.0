# Timer-2.0.
Expanded, more comprehensive version of the Timer class from my old Bank-Account repo. This was committed directly from the Git command line, so anyone should be able to do a git pull and download and import it directly into your IDE without any problems.

A couple bugs/needed clarifications, as of version 1.1:

You do, unfortunately, need to declare a new Thread with your Timer object as a parameter in order to run the counting commands properly. I'll keep trying to find a way around this, but the rest of the timer functions perfectly.

Another thing some users may be confused about is how the timer will count down below zero  This was done on purpose; I figured negative timer values may useful to some devs in certain situations. Use the set() and isAtZero() methods before starting your timer if you don't want this to happen.

Also, I don't know why I committed with my other Github account. It doesn't matter, I don't use it anymore.
