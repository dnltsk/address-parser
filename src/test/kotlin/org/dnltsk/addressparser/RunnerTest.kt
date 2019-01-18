package org.dnltsk.addressparser

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.dnltsk.addressparser.parser.AddressParseService
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
class RunnerTest {

    @InjectMocks
    private lateinit var runner: Runner

    @Mock
    private lateinit var addressParseService: AddressParseService

    @Mock
    private lateinit var consolePrinter: ConsolePrinter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `given address processed via parser and printer`() {
        //given
        whenever(addressParseService.parse(any())).thenReturn(listOf(Address("dummy-street", "dummy-hn")))
        val addressString = "Winterallee 3"
        //when
        runner.run(addressString)
        //then
        verify(addressParseService).parse(addressString)
        verify(consolePrinter).printResults(any(), any())
        verify(consolePrinter, never()).printNoAddressProvidedError()
        verify(consolePrinter, never()).printEmptyResult(any())
    }

    @Test
    fun `error message is printed when no address string is provided`() {
        //given (vararg == empty) when
        runner.run()
        //then
        verify(addressParseService, never()).parse(any())
        verify(consolePrinter, never()).printResults(any(), any())
        verify(consolePrinter).printNoAddressProvidedError()
        verify(consolePrinter, never()).printEmptyResult(any())
    }

    @Test
    fun `invalid address handled correctly`() {
        //given
        val addressString = "invalid_address"
        whenever(addressParseService.parse(any())).thenReturn(emptyList())
        //when
        runner.run(addressString)
        //then
        verify(addressParseService).parse(addressString)
        verify(consolePrinter, never()).printResults(any(), any())
        verify(consolePrinter, never()).printNoAddressProvidedError()
        verify(consolePrinter).printEmptyResult(addressString)
    }

}