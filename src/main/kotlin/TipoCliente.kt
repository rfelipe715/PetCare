sealed class TipoCliente {
    data class CLienteParticular (val descuento: Double) : TipoCliente()
    data class CLienteConConvenio (val descuento: Double) : TipoCliente()
    data class CLienteMunicipal (val descuento: Double) : TipoCliente()
}