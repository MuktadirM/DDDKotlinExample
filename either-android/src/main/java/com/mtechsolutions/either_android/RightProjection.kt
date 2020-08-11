package com.mtechsolutions.either_android

import com.mtechsolutions.either_android.Either.Left;
import com.mtechsolutions.either_android.Either.Right;
import com.mtechsolutions.either_android.Option.Some;
import com.mtechsolutions.either_android.Option.None;


class RightProjection<out L, out R>(val e: Either<L, R>) {

    fun get(): R = when (e) {
        is Right -> e.r
        is Left -> throw NoSuchElementException("Either.right.value on Left")
    }

    fun forEach(f: (R) -> Unit) {
        when (e) {
            is Right -> f(e.r)
        }
    }


    fun exists(predicate: (R) -> Boolean): Boolean = when (e) {
        is Right -> predicate(e.r)
        is Left -> false
    }

    fun <X> map(f: (R) -> X): Either<L, X> = flatMap { Right<L, X>(f(it)) }

    fun filter(predicate: (R) -> Boolean): Option<Either<L, R>> = when (e) {
        is Right -> {
            if (predicate(e.r)) {
                Option.Some(e)
            } else {
                None
            }
        }
        is Left -> None
    }

    fun toList(): List<R> = when (e) {
        is Right -> listOf(e.r)
        is Left -> listOf()
    }

    fun toOption(): Option<R> = when (e) {
        is Right -> Some(e.r)
        is Left -> None
    }

}

fun <L, R> RightProjection<L, R>.getOrElse(default: () -> R): R = when (e) {
    is Right -> e.r
    is Left -> default()
}

fun <X, L, R> RightProjection<L, R>.flatMap(f: (R) -> Either<L, X>): Either<L, X> = when (e) {
    is Left -> Left(e.l)
    is Right -> f(e.r)
}


fun <L, R, X, Y> RightProjection<L, R>.map(x: Either<L, X>, f: (R, X) -> Y): Either<L, Y> = flatMap { r -> x.right().map { xx -> f(r, xx) } }

