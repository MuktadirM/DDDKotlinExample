package com.mtechsolutions.dddkotlinexample.domain.core

class NotAuthenticatedError : Error() {}

class UnexpectedValueError(private val valueFailure: Any) : Exception() {
    override fun toString(): String {
        val explanation = "Encountered a ValueFailure at an unrecoverable point. Terminating."
        return "Failure was : $valueFailure"
    }

}