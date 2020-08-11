package com.mtechsolutions.either_android.utils

interface SetterOperation<in K, in V> {
    val setter: (K, V) -> Unit

    operator fun set(key: K, value: V) {
        setter(key, value)
    }
}

class SetterOperationImpl<in K, in V>(override val setter: (K, V) -> Unit) : SetterOperation<K, V>