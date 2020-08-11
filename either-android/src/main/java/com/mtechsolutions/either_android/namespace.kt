package com.mtechsolutions.either_android

fun <T> List<T>.tail(): List<T> = this.drop(1)

infix fun <T> T.prependTo(list: List<T>): List<T> = listOf(this) + list

fun <T> List<T>.destructured(): Pair<T, List<T>> = first() to tail()