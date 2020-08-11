package com.mtechsolutions.either_android.utils

class GetterSetterOperation<in K, V>(override val getter: (K) -> V, override val setter: (K, V) -> Unit) :
        GetterOperation<K, V>,
        SetterOperation<K, V>