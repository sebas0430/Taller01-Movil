package com.javeriana.taller01.model

data class CountryItem(
    val cca2: String?,
    val cca3: String?,
    val flags: FlagsDto?
)

data class FlagsDto(
    val alt: String?,
    val png: String?,
    val svg: String?
)