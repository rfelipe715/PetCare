package model

open class Paciente(
    val codigoAtencion: String,
    val nombre: String,
    val especie: String,
    val tipoDueno: TipoDueno
) {
    init {
        // Validar formato: dos letras, dos dígitos, dos letras
        // EJEMPLO: CA12CD
        require(codigoAtencion.matches(Regex("^[a-zA-Z]{2}\\d{2}[a-zA-Z]{2}$"))) {
            "Código de atención inválido. Debe tener el formato: 2 letras, 2 números, 2 letras."
        }
    }

    // Método open para demostrar polimorfismo
    open fun calcularTarifaBase(minutosUso: Int): Double {
        return 0.0
    }
}
