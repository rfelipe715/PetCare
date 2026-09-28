package model

data class Ticket(
    val numeroTicket: Int,
    val paciente: Paciente,
    val tiempoMinutos: Int,
    val montoTotal: Double
)
