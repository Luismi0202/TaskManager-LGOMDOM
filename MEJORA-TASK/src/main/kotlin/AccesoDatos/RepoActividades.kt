package AccesoDatos

import Dominio.Actividad
import Dominio.EstadoTarea
import Dominio.Evento
import Dominio.Tarea
import Servicios.ControlDeHistorial
import java.io.File
import java.nio.file.Paths

/**
 * Repositorio encargado de gestionar la persistencia y recuperación de actividades,
 * tareas y eventos del sistema.
 * Implementa [IActividadRepository] para ofrecer funcionalidades específicas de almacenamiento y manipulación.
 *
 * @property actividades Lista mutable de todas las actividades gestionadas.
 * @property tareas Lista mutable de todas las tareas gestionadas.
 * @property eventos Lista mutable de todos los eventos gestionados.
 */
class RepoActividades(
    override val actividades: MutableList<Actividad> = mutableListOf(),
    override val tareas: MutableList<Tarea> = mutableListOf(),
    override val eventos: MutableList<Evento> = mutableListOf()
) : IActividadRepository {

    init {
        cargarActividades()
    }

    /**
     * Cambia el estado de una tarea específica y actualiza el fichero de actividades.
     * Además, registra el cambio en el historial.
     *
     * @param tarea Tarea a la que se le cambiará el estado.
     * @param historial Referencia al historial para registrar la operación.
     * @param estadoTarea Nuevo estado a asignar a la tarea.
     */
    fun cambiarEstado(tarea: Tarea, historial: ControlDeHistorial, estadoTarea: EstadoTarea) {
        val id = tarea.getIdActividad()
        tarea.estado = estadoTarea
        val archivo = File(RUTA_FICHERO_ACTIVIDADES)

        archivo.writeText("") // Limpiar el archivo antes de escribir

        tareas.forEach { tareaPrincipal ->
            archivo.appendText("${tareaPrincipal.obtenerDetalle()}\n")

            if (tareaPrincipal is Tarea && tareaPrincipal.subTareas.isNotEmpty()) {
                tareaPrincipal.subTareas.forEach { subTarea ->
                    archivo.appendText("    - ${subTarea.obtenerDetalle()}\n")
                }
            }
        }

        println("¡Tarea cerrada con éxito!")
        historial.agregarHistorial("Tarea con id $id con estado cambiado a $estadoTarea con éxito")
    }

    /**
     * Añade una nueva actividad al repositorio, evitando duplicados.
     * Si la actividad es una tarea o evento, la agrega a la lista correspondiente.
     * Actualiza el fichero de almacenamiento persistente.
     *
     * @param actividad Actividad a añadir.
     */
    fun aniadirActividad(actividad: Actividad) {
        if (!actividades.contains(actividad)) { // Evitar duplicados en la lista de actividades
            actividades.add(actividad)

            when (actividad) {
                is Tarea -> if (!tareas.contains(actividad)) tareas.add(actividad)
                is Evento -> if (!eventos.contains(actividad)) eventos.add(actividad)
            }

            Utils.aniadirActividad(RUTA_FICHERO_ACTIVIDADES, actividad)
        } else {
            println("La actividad ya existe, no se añadirá de nuevo.")
        }
    }

    /**
     * Carga todas las actividades almacenadas en el fichero correspondiente.
     * Realiza la deserialización de objetos y los añade a las listas gestionadas,
     * evitando duplicados y errores de carga.
     */
    private fun cargarActividades() {
        val ficheroActividades = Utils.leerArchivo(RUTA_FICHERO_ACTIVIDADES)
        for (linea in ficheroActividades) {
            try {
                val actividad = Utils.deserializarActividad(linea)
                if (actividad != null && !actividades.contains(actividad)) { // Verificar que no sea null y evitar duplicados
                    actividades.add(actividad)

                    when (actividad) {
                        is Tarea -> tareas.add(actividad)
                        is Evento -> eventos.add(actividad)
                    }
                }
            } catch (e: Exception) {
                println("Error al cargar una actividad desde el fichero: ${e.message}")
            }
        }
    }

    companion object {
        /**
         * Ruta relativa del archivo de almacenamiento de actividades.
         */
        val ruta_relativa = "MEJORA-TASK/src/main/kotlin/Datos/Actividades.txt"
        /**
         * Ruta absoluta construida a partir de la ruta relativa, utilizada para el acceso al fichero persistente.
         */
        val RUTA_FICHERO_ACTIVIDADES = Paths.get(ruta_relativa).toAbsolutePath().toString()
    }
}