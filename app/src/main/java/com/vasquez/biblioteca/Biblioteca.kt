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

//tipo de usuario
fun moraPorTipo(tipoUsuario: String): Double {
    if (tipoUsuario == "docente") {
        return 3.00
    }
    if (tipoUsuario == "estudiante") {
        return 1.50
    }
    return 1.50
}

//calcular dias de retraso
fun calcularRetraso(prestamo: Prestamo): Int {
    val formato = ofPattern("dd/MM/yyyy")
    val entrega = LocalDate.parse(prestamo.fechaEntrega, formato)
    val devolucion = LocalDate.parse(prestamo.fechaDevolucion, formato)
    val dias = ChronoUnit.DAYS.between(entrega,devolucion)
    return dias.toInt()
}

//main
fun main(){
}