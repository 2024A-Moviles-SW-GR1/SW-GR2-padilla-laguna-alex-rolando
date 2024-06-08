package com.example.kotlin.entities

data class Company(
    val id: Int, var name: String, val projects: MutableList<Project>
)
