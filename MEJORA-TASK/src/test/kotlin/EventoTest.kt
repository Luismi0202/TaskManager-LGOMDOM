// Archivo: src/test/kotlin/dominio/EventoTest.kt
import dominio.Evento
import dominio.EventoParams
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EventoTest {
    //PRUEBA ANTES DE USARLO POR PARÁMETROS
    @Test
    fun testCrearEventoConConstructorAntiguo() {
        val evento = Evento.creaInstancia(
            "Descripción", "Usuario", "Ubicación", "01-01-2024"
        )
        assertEquals("Descripción", evento.obtenerDesc())
        assertEquals("Usuario", evento.obtenerUsuario())
    }

    //PRUEBA DESPUÉS DE USARLO POR PARÁMETROS
    @Test
    fun testCrearEventoConEventoParams() {
        val params = EventoParams(
            usuario = "Usuario",
            id = "123",
            descripcion = "Descripción",
            fechaCreacion = "01-01-2024",
            fecha = "02-01-2024",
            ubicacion = "Ubicación"
        )
        val evento = Evento.creaInstancia(params)
        assertEquals("Descripción", evento.obtenerDesc())
        assertEquals("Usuario", evento.obtenerUsuario())
        assertEquals("Ubicación", evento.obtenerDetalle().split(";").last())
    }
}