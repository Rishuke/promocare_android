package com.esgi.promocare_android.model

data class LoginResponse(
    val token: String,
    val user: User,


) {
     class User {
        private val id = 0
        private val name: String? = null
        private val email: String? = null

    }
}
