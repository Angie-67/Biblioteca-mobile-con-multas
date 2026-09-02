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
    //ingreso de datos
    println("===========INGRESO DE DATOS==============")
    print("Titulo del libro: ")
    val titulo = readln()

    print("Tipo de usuario: ")
    val tipoUser = readln().lowercase()

    print("Fecha de Préstamo: ")
    val datePrestamo = readln()

    print("Fecha de Entrega: ")
    val Entrega = readln()

    print("Fecha de Devolución: ")
    val Devolucion = readln()

    //datos
    val consulta = Prestamo(
        libros = titulo,
        tipoUsuario = tipoUser,
        fechaPrestamo = datePrestamo,
        fechaEntrega = Entrega,
        fechaDevolucion = Devolucion
    )

    val diasretraso = calcularRetraso(consulta)
    val moradiaria = moraPorTipo(consulta.tipoUsuario)

    //mostrar
    println()
    println("==============================================")
    println("==========SISTEMA DE DEVOLUCIÓN===============")
    println("==============================================")
    println("Libro: ${titulo}")
    println("Tipo de usuario: ${tipoUser}")
    println("Fecha de préstamo: ${datePrestamo}")
    println("Fecha de entrega: ${Entrega}")
    println("Fecha de devolución: ${Devolucion}")
    println("Estado: Devuelto en ${diasretraso} dias de retraso")
    println()

    //tabla
    println("==============DETALLE DE MORA=================")
    println("-------------------------------------------------------")
    println(String.format("%-5s %-15s %-15s %-15s", "Día", "Fecha", "Mora diaria", "Acumulado"))
    println("--------------------------------------------------------")

    val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val fechainicial = LocalDate.parse(Entrega, formato)

    var acumulacion = 0.00

    if (diasretraso > 0) {
        for (dia in 1..diasretraso) {
            val fechamora = fechainicial.plusDays(dia.toLong())
            acumulacion = acumulacion + moradiaria
            println(
                String.format("%-5d %-15s S/ %-12.2f S/ %-12.2f", dia, fechamora.format(formato), moradiaria, acumulacion))
        }
    } else {
        //
    }
    println("-----------------------------------------------------------")
    println(String.format("%-30s S/ %.2f", "TOTAL A PAGAR:", acumulacion))
    println("------------------------------------------------------------")
}