package com.mtechsolutions.dddkotlinexample.domain.core

sealed class ValueFailure{
    data class InvalidEmail<T>(val failedValue:T):ValueFailure()
    data class Empty<T>(val failedValue:T):ValueFailure()
    data class ShortPassword<T>(val failedValue:T):ValueFailure()
    data class ExceedingLength<T>(val failedValue: T,val max:Int):ValueFailure()
    data class MatchPasswordFail<T>(val failedValue:T):ValueFailure()
    data class InvalidFullName<T>(val failedValue:T):ValueFailure()
    data class Multiline<T>(val failedValue:T):ValueFailure()
    object UnknownValueFailure:ValueFailure()
}