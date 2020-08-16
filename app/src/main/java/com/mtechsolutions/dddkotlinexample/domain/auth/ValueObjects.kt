package com.mtechsolutions.dddkotlinexample.domain.auth;

import com.mtechsolutions.dddkotlinexample.domain.core.*
import com.mtechsolutions.either_android.Either
import com.mtechsolutions.either_android.Either.Companion.right
import java.util.UUID.randomUUID


class EmailAddress : ValueObjects<String>{
    override lateinit var value: Either<ValueFailure, String>;

    companion object {
        fun  emailAddress(email:String) = EmailAddress(validateEmailAddress(email))
    }

    private constructor(value:Either<ValueFailure, String>):this(){
        this.value = value
    }

    constructor()
}

class Password : ValueObjects<String>{
    override lateinit var value: Either<ValueFailure, String>

    companion object{
        fun password(password: String) = Password(validatePassword(password))
    }

    private constructor(value:Either<ValueFailure, String>):this(){
        this.value = value
    }

    constructor()

}

class FullName : ValueObjects<String>{
    override lateinit var value: Either<ValueFailure, String>
    companion object {
        fun fullName(input : String) = FullName(validateFullName(input))
    }
    private constructor(value:Either<ValueFailure, String>):this(){
        this.value = value
    }
    constructor()
}

class UniqueId :ValueObjects<String>{
    override lateinit var value: Either<ValueFailure, String>

    companion object {
        fun uniqueId() = UniqueId(right(randomUUID().toString()))
        fun fromUniqueString(uid:String) = UniqueId(right(uid))
    }
    private constructor(value:Either<ValueFailure, String>):this(){
        this.value = value
    }
    constructor()
}
