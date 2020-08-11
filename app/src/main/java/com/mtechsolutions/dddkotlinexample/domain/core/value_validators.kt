package com.mtechsolutions.dddkotlinexample.domain.core

import com.mtechsolutions.either_android.Either
import com.mtechsolutions.either_android.Either.Companion.left
import com.mtechsolutions.either_android.Either.Companion.right


fun validateMaxStringLength(input:String, maxLength:Int):Either<ValueFailure<String>,String>{
    if (input.length <= maxLength){
        return right(input);
    }
    else
    {
        return left(ValueFailure.ExceedingLength(input,maxLength))
    }
}

fun validateFieldNotEmpty(input:String):Either<ValueFailure<String>, String>{
    if (input.isEmpty()) {
        return left(ValueFailure.Empty(input));
    } else {
        return right(input);
    }
}

fun validateEmailAddress(email:String):Either<ValueFailure<String>, String>{
    if (emailRegexPattern.matcher(email).matches()) {
        return right(email);
    } else {
        return left(ValueFailure.InvalidEmail(failedValue= email));
    }
}

fun validatePassword(input: String):Either<ValueFailure<String>, String>{
    if (input.length >= 6) {
        return right(input);
    } else {
        return left(ValueFailure.ShortPassword(failedValue= input));
    }
}

fun validateFullName(input:String):Either<ValueFailure<String>, String>{
    if (input.length >= 3) {
        return right(input);
    } else {
        return left(ValueFailure.InvalidFullName(failedValue= input));
    }
}

fun validatePasswordMatch(input: String,other:String):Either<ValueFailure<String>, String>{
    if (input == other) {
        return right(input);
    } else {
        return left(ValueFailure.MatchPasswordFail(failedValue= other));
    }
}

fun validateSingleLine(input: String):Either<ValueFailure<String>, String>{
    if (input.contains('\n')) {
        return left(ValueFailure.Multiline(failedValue= input));
    } else {
        return right(input);
    }
}

