package model

class Exotico(
    codigoAtencion: String,
    nombre: String,
    tipoDueno: TipoDueno,
    val silvestre: Boolean // solo disponible en Paciente Exotico
) : Paciente(codigoAtencion, nombre, "Exótico", tipoDueno) {

    override fun calcularTarifaBase(minutosUso: Int): Double {
        val tarifaHora = 20000.0
        var costo = (minutosUso / 60.0) * tarifaHora
        if (silvestre) {
            costo *= 1.30 // 30% recargo
        }
        return costo
    }
}
