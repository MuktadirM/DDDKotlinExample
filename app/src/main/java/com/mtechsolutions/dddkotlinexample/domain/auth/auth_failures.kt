package com.mtechsolutions.dddkotlinexample.domain.auth

sealed class AuthFailure{
    data class CancelledByUser<T>(val failure: T):AuthFailure()
    {
        override fun toString(): String {
            return "$failure"
        }
    }
    data class ServerError<T>(val failure: T):AuthFailure()
    {
        override fun toString(): String {
            return "$failure"
        }
    }
    data class EmailAlreadyInUse<T>(val failure: T) :AuthFailure()
    {
        override fun toString(): String {
            return "$failure"
        }
    }
    data class InvalidEmailAndPasswordCombination<T>(val failure: T):AuthFailure()
    {
        override fun toString(): String {
            return "$failure"
        }
    }

    object UnknownAuthFailure : AuthFailure()
}