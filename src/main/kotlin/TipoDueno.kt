sealed class TipoDueno {
    data class CLienteParticular (val descuento: Double = 0.0) : TipoDueno()
    data class CLienteConConvenio (val descuento: Double = 0.0) : TipoDueno()
    data class CLienteMunicipal (val descuento: Double = 0.0) : TipoDueno()
}