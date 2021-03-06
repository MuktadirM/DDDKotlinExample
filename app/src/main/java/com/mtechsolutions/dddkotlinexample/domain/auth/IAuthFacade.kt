package com.mtechsolutions.dddkotlinexample.domain.auth


import com.mtechsolutions.either_android.Either
import java.util.*

interface IAuthFacade{
    suspend fun getSignedInUser(): Optional<User>
    suspend fun signInWithEmailPassword(emailAddress: EmailAddress,password: Password) : Either<AuthFailure, Unit>
    suspend fun registerWithEmailPassword(fullName: FullName,emailAddress: EmailAddress,password: Password):Either<AuthFailure,Unit>
    suspend fun signInWithGoogle():Either<AuthFailure,Unit>
    suspend fun signOut():Unit
}