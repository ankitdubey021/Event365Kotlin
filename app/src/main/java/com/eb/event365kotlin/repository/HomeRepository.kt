package com.eb.event365kotlin.repository

import com.eb.event365kotlin.common.ApiService

class HomeRepository(val apiService: ApiService){
    fun fetchProfile() = apiService.getProfile("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMwMiwiaWF0IjoxNTc0MzM1MzA0fQ.s-gxrTPAE4x5qJsfeIgHe4UtLoT59qHZfslB2JlJ0SU")
}