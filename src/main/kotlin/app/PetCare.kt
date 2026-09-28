package app

import kotlinx.coroutines.delay
import model.*

class PetCare {
    val boxes: List<Box> = List(10) { Box(it + 1) } // Solo 10 Box máximo
    val historialTickets = mutableListOf<Ticket>()
    private var contadorTickets = 1

    suspend fun registrarEntrada(paciente: Paciente) {
        val boxLibre = boxes.firstOrNull { it.estado is EstadoBox.Libre }
        
        if (boxLibre == null) {
            println("Error: No hay boxes libres para registrar a ${paciente.nombre}.")
            return
        }

        boxLibre.estado = EstadoBox.EnProceso("Registrando entrada...")
        println("Box ${boxLibre.numero}: Registrando entrada de ${paciente.nombre} (Procesando...)")
        
        delay(3000) // Simula  espera
        
        boxLibre.estado = EstadoBox.EnAtencion(paciente)
        println("-> Éxito: Paciente ${paciente.nombre} (${paciente.codigoAtencion}) asignado al Box ${boxLibre.numero}.")
    }

    suspend fun registrarSalida(codigoAtencion: String, minutosUso: Int) {
        val box = boxes.firstOrNull { 
            val estado = it.estado
            estado is EstadoBox.EnAtencion && estado.paciente.codigoAtencion == codigoAtencion
        }

        if (box == null) {
            println("Error: Paciente con código $codigoAtencion no encontrado.")
            return
        }

        val paciente = (box.estado as EstadoBox.EnAtencion).paciente
        
        box.estado = EstadoBox.EnProceso("Calculando tarifa...")
        println("Box ${box.numero}: Procesando salida de ${paciente.nombre}...")
        
        delay(6500) // Simula espera
        
        try {
            var costoBase = paciente.calcularTarifaBase(minutosUso)
            val iva = costoBase * 0.19
            var total = costoBase + iva

            if (paciente.tipoDueno == TipoDueno.MUNICIPAL) {
                total *= 0.5
            }

            if (total <= 0.0 && !(paciente is Felino && minutosUso < 20)) {
                 throw IllegalArgumentException("La tarifa no puede ser negativa o cero.")
            }

            val ticket = Ticket(contadorTickets++, paciente, minutosUso, total)
            historialTickets.add(ticket)
            
            box.estado = EstadoBox.Libre
            println("-> Ticket #${ticket.numeroTicket} emitido. Total a pagar: $${total.toInt()}")

        } catch (e: Exception) {
            println("Error procesando salida: ${e.message}")
            box.estado = EstadoBox.EnAtencion(paciente) // Revierte estado a "En Atencion"
        }
    }

    fun mostrarReporte() {
        println("\n--- REPORTE CIERRE DE TURNO ---")
        // Ejemplo de funciones de orden superior
        val totalRecaudado = historialTickets.sumOf { it.montoTotal }
        val cajasLibres = boxes.count { it.estado is EstadoBox.Libre }
        val clientesConvenio = historialTickets.filter { it.paciente.tipoDueno == TipoDueno.CONVENIO }.map { it.paciente.nombre }
        
        historialTickets.forEach {
            println("Ticket #${it.numeroTicket} | ${it.paciente.especie} - ${it.paciente.codigoAtencion} | ${it.tiempoMinutos} min | $${it.montoTotal.toInt()}")
        }
        println("Total recaudado: $${totalRecaudado.toInt()}")
        println("Boxes disponibles al cierre: $cajasLibres")
        println("Pacientes en convenio atendidos: $clientesConvenio")
    }
}
