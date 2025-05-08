import Servicios.ControlDeHistorial
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.file.shouldExist
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.slf4j.Logger
import java.io.File

class ControlDeHistorialTest : StringSpec({

    val testFilePath = "test_historial.txt" // Archivo temporal para pruebas
    val mockLogger = mockk<Logger>(relaxed = true) // Logger mockeado

    // Clase modificada para pruebas con logger mockeado
    val controlDeHistorial = object : ControlDeHistorial() {
        override val logger: Logger = mockLogger
    }

    beforeTest {
        // Cambiar la ruta del historial a un archivo temporal para pruebas
        ControlDeHistorial.RUTA_HISTORIAL = testFilePath
        File(testFilePath).writeText("") // Limpiar el archivo antes de cada prueba
    }

    afterTest {
        // Eliminar el archivo temporal después de la prueba
        File(testFilePath).delete()
    }

    "debería agregar entradas al historial y registrar en el logger" {
        val mensaje = "Este es un mensaje de prueba"

        // Ejecutar el método
        controlDeHistorial.agregarHistorial(mensaje)

        // Verificar que el archivo existe
        val historialFile = File(testFilePath)
        historialFile.shouldExist()

        // Verificar que el archivo tiene exactamente una línea
        val lineCount = historialFile.readLines().size
        lineCount shouldBe 1

        // Verificar que el archivo contiene el mensaje
        val contenido = historialFile.readText()
        contenido shouldContain mensaje

        // Verificar que el logger registró el mensaje
        val logSlot = slot<String>()
        verify { mockLogger.info(capture(logSlot)) }
        logSlot.captured shouldContain mensaje
    }

    "debería manejar errores al escribir en el archivo" {
        val mensaje = "Este es otro mensaje de prueba"

        // Simular un error al escribir en el archivo
        val historialFile = File(testFilePath)
        historialFile.setWritable(false) // Hacer que el archivo sea de solo lectura

        // Ejecutar el método
        controlDeHistorial.agregarHistorial(mensaje)

        // Verificar que el logger registró el error
        val errorSlot = slot<String>()
        verify { mockLogger.error(capture(errorSlot), any<Exception>()) }
        errorSlot.captured shouldContain "Error al escribir en el historial"

        // Restaurar permisos del archivo
        historialFile.setWritable(true)
    }
})