package model

class Canino(
    codigoAtencion: String,
    nombre: String,
    tipoDueno: TipoDueno
) : Paciente(codigoAtencion, nombre, "Canino", tipoDueno) {

    override fun calcularTarifaBase(minutosUso: Int): Double {
        val tarifaHora = 12000.0
        // Descuento del 20% al tiempo si es convenio
        val minutosCobro = if (tipoDueno == TipoDueno.CONVENIO) minutosUso * 0.8 else minutosUso.toDouble()
        return (minutosCobro / 60.0) * tarifaHora
    }
}
