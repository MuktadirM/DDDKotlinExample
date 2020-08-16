package com.mtechsolutions.dddkotlinexample.infrastructure.auth

import com.mtechsolutions.dddkotlinexample.domain.auth.*
import com.mtechsolutions.either_android.Either
import java.util.*

class FirebaseAuthFacade : IAuthFacade {
    override suspend fun getSignedInUser(): Optional<User> {
        TODO("Not yet implemented")
    }

    override suspend fun signInWithEmailPassword(
        emailAddress: EmailAddress,
        password: Password
    ): Either<AuthFailure, Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun registerWithEmailPassword(
        fullName: FullName,
        emailAddress: EmailAddress,
        password: Password
    ): Either<AuthFailure, Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun signInWithGoogle(): Either<AuthFailure, Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }

}