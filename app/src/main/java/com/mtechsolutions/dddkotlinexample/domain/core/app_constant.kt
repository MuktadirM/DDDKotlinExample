package com.mtechsolutions.dddkotlinexample.domain.core

import java.util.regex.Pattern
import java.util.regex.Pattern.compile

val emailRegexPattern : Pattern = compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)