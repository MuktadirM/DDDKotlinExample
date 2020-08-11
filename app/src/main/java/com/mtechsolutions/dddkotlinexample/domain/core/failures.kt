package com.mtechsolutions.dddkotlinexample.domain.core

sealed class ValueFailure<T>{
    data class InvalidEmail<T>(val failedValue:T):ValueFailure<T>()
    data class Empty<T>(val failedValue:T):ValueFailure<T>()
    data class ShortPassword<T>(val failedValue:T):ValueFailure<T>()
    data class ExceedingLength<T>(val failedValue: T,val max:Int):ValueFailure<T>()
    data class MatchPasswordFail<T>(val failedValue:T):ValueFailure<T>()
    data class InvalidFullName<T>(val failedValue:T):ValueFailure<T>()
    data class Multiline<T>(val failedValue:T):ValueFailure<T>()
}