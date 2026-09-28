import kotlinx.coroutines.runBlocking
import model.*
import app.PetCare

/**
 * Se inicia app PetCare con datos de prueba, se finaliza con un dato erroneo que dará error
 * - Comentar ese codigo para que aplicación funcione correctamente
 */
fun main() = runBlocking {
    val petCare = PetCare()
    println("=== SISTEMA PETCARE INICIADO ===\n")

    try {
        println("Intentando registrar paciente con código '123ABC'...")
        val pacienteInvalido = Canino("123ABC", "Firulais", TipoDueno.PARTICULAR)
    } catch (e: Exception) {
        println("Excepción capturada correctamente: ${e.message}\n")
    }

    // Datos de prueba
    val max = Canino("CA12CD", "Max", TipoDueno.CONVENIO)
    val luna = Canino("CA99ZA", "Luna", TipoDueno.PARTICULAR)
    val misi = Felino("FE22TO", "Misi", TipoDueno.PARTICULAR)
    val loro = Exotico("EX44RG", "Loro", TipoDueno.MUNICIPAL, silvestre = true)

    // Entradas
    petCare.registrarEntrada(max)
    petCare.registrarEntrada(luna)
    petCare.registrarEntrada(misi)
    petCare.registrarEntrada(loro)

    println("\n--- INICIANDO SALIDAS ---")
    // Salidas
    petCare.registrarSalida("CA12CD", 75)
    petCare.registrarSalida("FE22TO", 18) // Felino < 20 min gratis
    petCare.registrarSalida("EX44RG", 120)
    
    // Provocar error controlado (paciente no encontrado)
    // (comentar para que la aplicación funcione correctamente y no se caiga)
    petCare.registrarSalida("ZZ99ZZ", 100)

    petCare.mostrarReporte()
}
