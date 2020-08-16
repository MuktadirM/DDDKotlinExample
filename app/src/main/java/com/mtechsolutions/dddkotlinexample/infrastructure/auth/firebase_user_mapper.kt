package com.mtechsolutions.dddkotlinexample.infrastructure.auth

import com.google.firebase.auth.FirebaseUser
import com.mtechsolutions.dddkotlinexample.domain.auth.EmailAddress
import com.mtechsolutions.dddkotlinexample.domain.auth.FullName
import com.mtechsolutions.dddkotlinexample.domain.auth.UniqueId
import com.mtechsolutions.dddkotlinexample.domain.auth.User

class FirebaseUserMapper{
    fun toDomain(user : FirebaseUser): User? {
        return if (user == null) null else user.email?.let { EmailAddress.emailAddress(it) }?.let {
            User(
                uid = UniqueId.fromUniqueString(user.uid),
                name = FullName.fullName(if (user.displayName == null ) user.email!!.split('@',ignoreCase = false).first()else "No name"),
                email = it
            )
        };
    }
}
