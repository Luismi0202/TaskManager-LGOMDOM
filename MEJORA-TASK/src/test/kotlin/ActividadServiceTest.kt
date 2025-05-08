import AccesoDatos.RepoActividades
import Dominio.*
import Presentacion.ConsolaUI
import Servicios.ActividadService
import Servicios.ControlDeHistorial
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.*

class ActividadServiceTest : DescribeSpec({

    val mockConsola = mockk<ConsolaUI>(relaxed = true)
    val mockRepo = mockk<RepoActividades>(relaxed = true)
    val mockHistorial = mockk<ControlDeHistorial>(relaxed = true)

    val actividadService = ActividadService(
        consola = mockConsola,
        repo = mockRepo,
        historial = mockHistorial
    )

    describe("agregarSubtarea") {
        it("debería agregar una subtarea a una tarea existente") {
            val tareaMadre = mockk<Tarea>(relaxed = true) { every { getIdActividad() } returns "123" }
            val subtarea = mockk<Tarea>(relaxed = true)

            every { mockRepo.tareas } returns mutableListOf(tareaMadre)
            every { mockConsola.pedirInfo(any()) } returnsMany listOf("123", "Descripción", "Usuario")
            every { mockConsola.pedirEtiqueta() } returns EtiquetasTareas.URGENTE
            every { Tarea.creaInstancia(any(), any(), any()) } returns subtarea

            actividadService.agregarSubtarea()

            verify { tareaMadre.agregarSubTarea(subtarea) }
            verify { mockHistorial.agregarHistorial("Subtarea agregada a la tarea 123") }
        }

        it("debería manejar el caso en que no se encuentra la tarea madre") {
            every { mockRepo.tareas } returns mutableListOf()
            every { mockConsola.pedirInfo(any()) } returns "999"

            actividadService.agregarSubtarea()

            verify { mockConsola.mostrarMensaje("Error: No se encontró la tarea madre con el ID proporcionado.") }
            verify(exactly = 0) { mockHistorial.agregarHistorial(any()) }
        }
    }

    describe("filtrarPorEstado") {
        it("debería mostrar tareas con estado ABIERTA") {
            val tarea1 = mockk<Tarea>(relaxed = true) { every { estado } returns EstadoTarea.ABIERTA }
            every { mockRepo.tareas } returns mutableListOf(tarea1)
            every { mockConsola.pedirOpcion(any(), any(), any()) } returnsMany listOf(1, 0)

            actividadService.filtrarPorEstado()

            verify { mockConsola.mostrarMensaje(tarea1.obtenerDetalle()) }
        }

        it("debería manejar un repositorio vacío") {
            every { mockRepo.tareas } returns mutableListOf()
            every { mockConsola.pedirOpcion(any(), any(), any()) } returns 0

            actividadService.filtrarPorEstado()

            verify { mockConsola.mostrarMensaje("No hay tareas con este estado.") }
        }
    }
})