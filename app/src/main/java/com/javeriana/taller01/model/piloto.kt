package com.javeriana.taller01.model

class Piloto (val driverNumber: Int,
              val countryCode: String,
              val fullName: String,
              val headshotUrl: String,
              val nameAcronym: String,
              val teamColour: String,
              val teamName: String)
{
    override fun toString(): String {
        return "$driverNumber - $fullName"
    }

}