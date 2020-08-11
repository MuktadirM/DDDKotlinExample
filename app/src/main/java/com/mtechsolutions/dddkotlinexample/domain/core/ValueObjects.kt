package com.mtechsolutions.dddkotlinexample.domain.core

import com.mtechsolutions.either_android.Either
import com.mtechsolutions.either_android.Either.Companion.left
import com.mtechsolutions.either_android.Either.Companion.right

abstract class ValueObjects<T>() : IValidatable{
    abstract val value: Either<ValueFailure<T>, T>

    override fun isValid(): Boolean {
       return value.isRight()
    }

    fun getOrCrush() : T{
        return value.fold({itLeft -> throw UnexpectedValueError(itLeft)},{itRight-> itRight})
    }

    fun failureOrUnit():Either<ValueFailure<T>, Unit>{
        return value.fold(
            {l-> left(l)},
            {r->right(Unit)}
        )
    }

    override fun toString(): String {
        return "Value object $value"
    }
}

class StringSingleLine private constructor(override val value: Either<ValueFailure<String>, String>)
    : ValueObjects<String>() {
    companion object{
        fun getValidSingleLine(input:String) = StringSingleLine(value = validateSingleLine(input))
    }
}
