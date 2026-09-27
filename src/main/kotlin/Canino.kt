class Canino(
    nombre: String, tipoDueno: TipoDueno
) : Mascota(
    tarifaBaseHora = 12000.0,
    especie = "canino",
    minutosAtencion = 0.0,
    nombre = nombre,
    tipoDueno = tipoDueno,
) {
    val DESCUENTO_CLIENTE_CONVENIO: Double = 20.0 // 20%

    override fun obtenerTarifaTotal(): Double {
        if (tipoDueno == TipoDueno.CLienteConConvenio()) {
            return super.obtenerTarifaTotal() * (1 - DESCUENTO_CLIENTE_CONVENIO / 100)
        }
        return super.obtenerTarifaTotal()
    }
}