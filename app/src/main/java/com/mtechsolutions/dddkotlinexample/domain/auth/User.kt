package com.mtechsolutions.dddkotlinexample.domain.auth

data class User(val uid:UniqueId,val name: FullName,val email: EmailAddress);