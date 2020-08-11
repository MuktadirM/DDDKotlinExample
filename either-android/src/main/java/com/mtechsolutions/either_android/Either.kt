package com.mtechsolutions.either_android

import com.mtechsolutions.either_android.*
import com.mtechsolutions.either_android.Either.Left
import com.mtechsolutions.either_android.Either.Right
import com.mtechsolutions.either_android.utils.hashCodeForNullable


sealed class Either<out L, out R> : EitherLike {

    companion object {
        fun <L> left(left: L): Left<L, Nothing> = Left(left)
        fun <R> right(right: R): Right<Nothing, R> = Right(right)
    }

    fun left(): LeftProjection<L, R> = LeftProjection(this)
    fun right(): RightProjection<L, R> = RightProjection(this)

    abstract operator fun component1(): L?
    abstract operator fun component2(): R?

    fun toDisjunction(): Disjunction<L, R> = when (this) {
        is Right -> Disjunction.Right(r)
        is Left -> Disjunction.Left(l)
    }

    fun <X> fold(fl: (L) -> X, fr: (R) -> X): X = when (this) {
        is Left -> fl(l)
        is Right -> fr(r)
    }

    fun swap(): Either<R, L> = when (this) {
        is Left -> Right(this.l)
        is Right -> Left(this.r)
    }

    class Left<out L, out R>(val l: L) : Either<L, R>(), LeftLike {
        override fun component1(): L = l
        override fun component2(): R? = null

        override fun equals(other: Any?): Boolean = when (other) {
            is Left<*, *> -> l == other.l
            else -> false

        }

        override fun hashCode(): Int = l.hashCodeForNullable(43) { a, b -> a * b }

        override fun toString(): String = "Either.Left($l)"
    }

    class Right<out L, out R>(val r: R) : Either<L, R>(), RightLike {
        override fun component1(): L? = null
        override fun component2(): R = r

        override fun equals(other: Any?): Boolean = when (other) {
            is Right<*, *> -> r == other.r
            else -> false
        }

        override fun hashCode(): Int = r.hashCodeForNullable(43) { a, b -> a * b }

        override fun toString(): String = "Either.Right($r)"
    }
}

fun <T> Either<T, T>.merge(): T = when (this) {
    is Left -> this.l
    is Right -> this.r
}

fun <L, R> Pair<L, R>.toLeft(): Left<L, R> = Left(this.component1())

fun <L, R> Pair<L, R>.toRight(): Either.Right<L, R> = Right(this.component2())

inline fun <T> eitherTry(body: () -> T): Either<Throwable, T> = try {
    Right(body())
} catch(t: Throwable) {
    Left(t)
}

fun <T, L, R> List<T>.eitherTraverse(f: (T) -> Either<L, R>): Either<L, List<R>> = foldRight(Right(emptyList())) { i: T, accumulator: Either<L, List<R>> ->
    when (val either = f(i)) {
        is Right -> either.right().map(accumulator) { head: R, tail: List<R> ->
            head prependTo tail
        }
        is Left -> Left(either.l)
    }
}

fun <L, R> List<Either<L, R>>.eitherSequential(): Either<L, List<R>> = eitherTraverse { it: Either<L, R> -> it }

inline fun <X, T> Option<T>.toEitherRight(left: () -> X): Either<X, T> = if (isEmpty()) {
    Left(left())
} else {
    Right(get())
}

inline fun <X, T> Option<T>.toEitherLeft(right: () -> X): Either<T, X> = if (isEmpty()) {
    Right(right())
} else {
    Left(get())
}