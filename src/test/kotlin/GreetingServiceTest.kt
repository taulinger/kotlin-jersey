import de.taulinger.GreetingService
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class GreetingServiceTest {

    @Test
    fun `when name is not available, do not greet `() {
        val instanceUnderTest = GreetingService()

        assertEquals("don't know you", instanceUnderTest.getGreetings(null))
    }

    @Test
    fun `when name is available, greet`() {
        val instanceUnderTest = GreetingService()

        assertEquals("Hello Foo!", instanceUnderTest.getGreetings("Foo"))
    }
}