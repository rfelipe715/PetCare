open class Mascota (
    val tarifaBaseHora: Double,
    var minutosAtencion: Double,
    val tipoDueno: TipoDueno,
    val nombre: String,
    val especie: String,
) {
    open fun obtenerTarifaTotal(): Double {
        return tarifaBaseHora * obtenerTotalHoras()
    }

    private fun obtenerTotalHoras(): Double {
        val MINUTOS_A_HORA: Double = 60.0
        return minutosAtencion * MINUTOS_A_HORA
    }
}