class Box {
    var estado: Estado = Estado.Libre()

    fun cambiarEstado(nuevoEstado: Estado) {
        estado = nuevoEstado
    }
}