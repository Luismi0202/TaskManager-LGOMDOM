package Dominio

import Utils

/**
 * Clase abstracta que representa una actividad genérica dentro del sistema.
 * Todas las actividades cuentan con una descripción, un usuario asignado, fecha de creación e identificador único.
 *
 * @property descripcion Descripción de la actividad.
 * @property usuario Nombre del usuario asignado a la actividad.
 */
abstract class Actividad(
    private val descripcion: String,
    private val usuario: String
) {
    init {
        require(descripcion.isNotBlank()) { "¡La descripción no puede estar vacía!" }
    }

    /**
     * Fecha de creación de la actividad, por defecto es la fecha actual al momento de instanciar.
     */
    protected open var fechaCreacion: String = Utils.obtenerFechaActual()

    /**
     * Fecha asociada a la actividad, editable.
     */
    open var fecha = fechaCreacion

    /**
     * Identificador único de la actividad, generado a partir de la fecha de creación.
     */
    protected open var id: String = "${CalculoId.generarId(fechaCreacion)}"

    /**
     * Obtiene la descripción de la actividad.
     * @return Descripción de la actividad.
     */
    open fun obtenerDesc() = descripcion

    /**
     * Devuelve el identificador único de la actividad.
     * @return ID de la actividad.
     */
    open fun getIdActividad(): String = id

    /**
     * Obtiene el nombre del usuario asignado a la actividad.
     * @return Nombre del usuario.
     */
    open fun obtenerUsuario(): String = usuario

    /**
     * Devuelve un detalle de la actividad en formato de texto.
     * @return Cadena con detalles de la actividad.
     */
    open fun obtenerDetalle(): String = "$usuario;$id;$descripcion;$fechaCreacion"
}