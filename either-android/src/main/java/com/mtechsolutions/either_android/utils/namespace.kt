package com.mtechsolutions.either_android.utils

fun <T> identity(): (T) -> T = { t: T -> t }

fun <P1, T> constant(t: T): (P1) -> T {
    return { _: P1 -> t }
}

typealias Predicate<T> = (T) -> Boolean

fun <T : Any> Predicate<T>.mapNullable(): (T?) -> Boolean {
    return { it?.let { this@mapNullable(it) } ?: false }
}

inline fun <T> T?.hashCodeForNullable(i: Int, f: (Int, Int) -> Int): Int {
    return when (this) {
        null -> i
        else -> f(i, this.hashCode())
    }
}