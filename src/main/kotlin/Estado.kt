sealed class Estado {
    data class Libre(val mensaje: String = ""): Estado()
    data class EnAtencion(val mensaje: String = ""): Estado()
    data class EnProcesoDeRegistro(val mensaje: String = ""): Estado()
    data class FueraDeServicio(val mensaje: String = ""): Estado()
}