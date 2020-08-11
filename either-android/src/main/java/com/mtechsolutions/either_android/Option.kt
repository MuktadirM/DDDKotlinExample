package com.mtechsolutions.either_android

import com.mtechsolutions.either_android.utils.GetterOperation
import com.mtechsolutions.either_android.utils.GetterOperationImpl
import com.mtechsolutions.either_android.utils.Predicate
import com.mtechsolutions.either_android.utils.hashCodeForNullable


sealed class Option<out T> {

    companion object {
        fun <T> empty(): Option<T> = None
    }

    abstract fun isEmpty(): Boolean

    fun nonEmpty(): Boolean = isDefined()

    fun isDefined(): Boolean = !isEmpty()

    abstract fun get(): T

    fun orNull(): T? = if (isEmpty()) {
        null
    } else {
        get()
    }

    inline fun <R> map(f: (T) -> R): Option<R> = if (isEmpty()) {
        None
    } else {
        Some(f(get()))
    }

    inline fun <P1, R> map(p1: Option<P1>, f: (T, P1) -> R): Option<R> = if (isEmpty()) {
        None
    } else {
        p1.map { pp1 -> f(get(), pp1) }
    }

    inline fun <R> fold(ifEmpty: () -> R, some: (T) -> R): R = if (isEmpty()) {
        ifEmpty()
    } else {
        some(get())
    }

    inline fun <R> flatMap(f: (T) -> Option<R>): Option<R> = if (isEmpty()) {
        None
    } else {
        f(get())
    }

    inline fun filter(predicate: Predicate<T>): Option<T> = if (nonEmpty() && predicate(get())) {
        this
    } else {
        None
    }

    inline fun filterNot(predicate: Predicate<T>): Option<T> = if (nonEmpty() && !predicate(get())) {
        this
    } else {
        None
    }

    inline fun exists(predicate: Predicate<T>): Boolean = nonEmpty() && predicate(get())

    inline fun forEach(f: (T) -> Unit) {
        if (nonEmpty()) f(get())
    }


    fun toList(): List<T> = if (isEmpty()) {
        listOf()
    } else {
        listOf(get())
    }

    infix fun <X> and(value: Option<X>): Option<X> = if (isEmpty()) {
        None
    } else {
        value
    }

    object None : Option<Nothing>() {
        override fun get() = throw NoSuchElementException("None.get")

        override fun isEmpty() = true

        override fun equals(other: Any?): Boolean = when (other) {
            is None -> true
            else -> false
        }

        override fun hashCode(): Int = Integer.MAX_VALUE
    }

    class Some<out T>(val t: T) : Option<T>() {
        override fun get() = t

        override fun isEmpty() = false

        override fun equals(other: Any?): Boolean = when (other) {
            is Some<*> -> t == other.get()
            else -> false
        }

        override fun hashCode(): Int = t.hashCodeForNullable(17) { a, b -> a + b }

        override fun toString(): String = "Some<$t>"
    }
}

fun <T> Option<T>.getOrElse(default: () -> T): T = if (isEmpty()) {
    default()
} else {
    get()
}

fun <T> Option<T>.orElse(alternative: () -> Option<T>): Option<T> = if (isEmpty()) {
    alternative()
} else {
    this
}

infix fun <T> Option<T>.or(value: Option<T>): Option<T> = if (isEmpty()) {
    value
} else {
    this
}

fun <T> T?.toOption(): Option<T> = if (this != null) {
    Option.Some(this)
} else {
    Option.None
}

inline fun <T> optionTry(body: () -> T): Option<T> = try {
    Option.Some(body())
} catch(e: Exception) {
    Option.None
}

val <K, V> Map<K, V>.option: GetterOperation<K, Option<V>>
    get () {
        return GetterOperationImpl { k -> this[k].toOption() }
    }


fun <T> Array<out T>.firstOption(): Option<T> {
    return firstOrNull().toOption()
}

fun BooleanArray.firstOption(): Option<Boolean> {
    return firstOrNull().toOption()
}

fun ByteArray.firstOption(): Option<Byte> {
    return firstOrNull().toOption()
}

fun CharArray.firstOption(): Option<Char> {
    return firstOrNull().toOption()
}

fun DoubleArray.firstOption(): Option<Double> {
    return firstOrNull().toOption()
}

fun FloatArray.firstOption(): Option<Float> {
    return firstOrNull().toOption()
}


fun IntArray.firstOption(): Option<Int> {
    return firstOrNull().toOption()
}


fun LongArray.firstOption(): Option<Long> {
    return firstOrNull().toOption()
}


fun ShortArray.firstOption(): Option<Short> {
    return firstOrNull().toOption()
}

fun <T> Iterable<T>.firstOption(): Option<T> {
    return firstOrNull().toOption()
}

fun <T> List<T>.firstOption(): Option<T> {
    return firstOrNull().toOption()
}

fun <T> Sequence<T?>.firstOption(): Option<T> {
    return firstOrNull().toOption()
}


fun String.firstOption(): Option<Char> {
    return firstOrNull().toOption()
}

fun <T> Array<out T>.firstOption(predicate: Predicate<T>): Option<T> {
    return firstOrNull(predicate).toOption()
}

inline fun BooleanArray.firstOption(predicate: (Boolean) -> Boolean): Option<Boolean> {
    return firstOrNull(predicate).toOption()
}

inline fun ByteArray.firstOption(predicate: (Byte) -> Boolean): Option<Byte> {
    return firstOrNull(predicate).toOption()
}

inline fun CharArray.firstOption(predicate: (Char) -> Boolean): Option<Char> {
    return firstOrNull(predicate).toOption()
}

inline fun DoubleArray.firstOption(predicate: (Double) -> Boolean): Option<Double> {
    return firstOrNull(predicate).toOption()
}

inline fun FloatArray.firstOption(predicate: (Float) -> Boolean): Option<Float> {
    return firstOrNull(predicate).toOption()
}

inline fun IntArray.firstOption(predicate: (Int) -> Boolean): Option<Int> {
    return firstOrNull(predicate).toOption()
}

inline fun LongArray.firstOption(predicate: (Long) -> Boolean): Option<Long> {
    return firstOrNull(predicate).toOption()
}

inline fun ShortArray.firstOption(predicate: (Short) -> Boolean): Option<Short> {
    return firstOrNull(predicate).toOption()
}

fun <T> Iterable<T>.firstOption(predicate: Predicate<T>): Option<T> {
    return firstOrNull(predicate).toOption()
}

fun <T> Sequence<T>.firstOption(predicate: Predicate<T>): Option<T> {
    return firstOrNull(predicate).toOption()
}

inline fun String.firstOption(predicate: (Char) -> Boolean): Option<Char> {
    return firstOrNull(predicate).toOption()
}

fun <T, R> List<T>.optionTraverse(f: (T) -> Option<R>): Option<List<R>> = foldRight(
    Option.Some(
        emptyList()
    )
) { i: T, accumulator: Option<List<R>> ->
    f(i).map(accumulator) { head: R, tail: List<R> ->
        head prependTo tail
    }
}

fun <T> List<Option<T>>.optionSequential(): Option<List<T>> = optionTraverse { it }

fun <T> List<Option<T>>.flatten(): List<T> {
    return filter { it.isDefined() }.map { it.get() }
}

fun <P1, R> ((P1) -> R).optionLift(): (Option<P1>) -> Option<R> {
    return { it.map(this) }
}