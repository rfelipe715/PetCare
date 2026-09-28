package model

class Felino(
    codigoAtencion: String,
    nombre: String,
    tipoDueno: TipoDueno
) : Paciente(codigoAtencion, nombre, "Felino", tipoDueno) {

    override fun calcularTarifaBase(minutosUso: Int): Double {
        if (minutosUso < 20) return 0.0
        val tarifaHora = 9000.0
        return (minutosUso / 60.0) * tarifaHora
    }
}
