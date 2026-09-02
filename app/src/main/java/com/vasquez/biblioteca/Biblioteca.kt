package com.vasquez.biblioteca

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ofPattern
import java.time.temporal.ChronoUnit

//clase
data class Prestamo(
    val libros: String,
    val tipoUsuario: String,
    val fechaPrestamo: String,
    val fechaEntrega: String,
    val fechaDevolucion: String
)

//main
fun main(){
}