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

@RunWith(SpringRunner::class)
@SpringBootTest
class RunnerTest{

    @InjectMocks
    private lateinit var runner: Runner

    @Mock
    private lateinit var addressParser: AddressParser

    @Mock
    private lateinit var consolePrinter: ConsolePrinter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        whenever(addressParser.parse(any())).thenReturn(Address("dummy-street", "dummy-hn"))
    }

    @Test
    fun `given address processed via parser and printer`() {
        val addressString = "Winterallee 3"
        runner.run(addressString)
        verify(addressParser).parse(addressString)
        verify(consolePrinter).printResult(any(), any())
        verify(consolePrinter, never()).printError()
    }

    @Test
    fun `error message is printed when no address string is provided`() {
        runner.run() //vararg == empty
        verify(addressParser, never()).parse(any())
        verify(consolePrinter, never()).printResult(any(), any())
        verify(consolePrinter).printError()
    }

}