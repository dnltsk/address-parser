package org.dnltsk.addressparser

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.dnltsk.addressparser.parser.AddressParser
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.text.ParseException

@RunWith(SpringRunner::class)
@SpringBootTest
class RunnerTest {

    @InjectMocks
    private lateinit var runner: Runner

    @Mock
    private lateinit var addressParser: AddressParser

    @Mock
    private lateinit var consolePrinter: ConsolePrinter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `given address processed via parser and printer`() {
        whenever(addressParser.parse(any())).thenReturn(Address("dummy-street", "dummy-hn"))
        val addressString = "Winterallee 3"
        runner.run(addressString)
        verify(addressParser).parse(addressString)
        verify(consolePrinter).printResult(any(), any())
        verify(consolePrinter, never()).printNoAddressProvidedError()
        verify(consolePrinter, never()).printParseException(any(), any())
    }

    @Test
    fun `error message is printed when no address string is provided`() {
        runner.run() //vararg == empty
        verify(addressParser, never()).parse(any())
        verify(consolePrinter, never()).printResult(any(), any())
        verify(consolePrinter).printNoAddressProvidedError()
        verify(consolePrinter, never()).printParseException(any(), any())
    }

    @Test
    fun `ParseException is catched correctly`() {
        //given
        val addressString = "dummy address"
        val throwedParseException = ParseException("dummy-exception", 0)
        whenever(addressParser.parse(any())).thenThrow(throwedParseException)
        //when
        runner.run(addressString)
        //then
        verify(addressParser).parse(addressString)
        verify(consolePrinter, never()).printResult(any(), any())
        verify(consolePrinter, never()).printNoAddressProvidedError()
        verify(consolePrinter).printParseException(addressString, throwedParseException)
    }

}