package com.mtechsolutions.dddkotlinexample.domain.auth

sealed class AuthFailure<T>{
    data class CancelledByUser<T>(val failure: T):AuthFailure<T>()
    {
        override fun toString(): String {
            return "$failure"
        }
    }
    data class ServerError<T>(val failure: T):AuthFailure<T>()
    {
        override fun toString(): String {
            return "$failure"
        }
    }
    data class EmailAlreadyInUse<T>(val failure: T) :AuthFailure<T>()
    {
        override fun toString(): String {
            return "$failure"
        }
    }
    data class InvalidEmailAndPasswordCombination<T>(val failure: T):AuthFailure<T>()
    {
        override fun toString(): String {
            return "$failure"
        }
    }
}