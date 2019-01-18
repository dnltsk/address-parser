package org.dnltsk.addressparser

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * TODO: transform stupid tests to reasonable tests :)
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class ConsolePrinterTest {

    @Autowired
    private lateinit var consolePrinter: ConsolePrinter

    @Test
    fun `printing results doesn't throw an exception`() {
        consolePrinter.printResults(
            "dummy-address",
            listOf(Address("dummy-street", "dummy-hn"))
        )
    }

    @Test
    fun `printing error doesn't throw an exception`() {
        consolePrinter.printNoAddressProvidedError()
    }

    @Test
    fun `printing ParseException doesn't throw an exception`() {
        consolePrinter.printEmptyResult("dummy-address")
    }
}