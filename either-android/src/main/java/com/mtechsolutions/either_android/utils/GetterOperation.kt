package com.mtechsolutions.either_android.utils

interface GetterOperation<in K, out V> {
    val getter: (K) -> V
    operator fun get(key: K): V {
        return getter(key)
    }
}

class GetterOperationImpl<in K, out V>(override val getter: (K) -> V) : GetterOperation<K, V>

