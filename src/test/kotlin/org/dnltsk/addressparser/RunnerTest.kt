package org.dnltsk.addressparser

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.dnltsk.addressparser.parser.AddressParser
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class RunnerTest{

    @InjectMocks
    private lateinit var runner: Runner

    @Mock
    private lateinit var addressParser: AddressParser

    @Test
    fun `given address is forwarded`() {
        val addressString = "Winterallee 3"
        runner.run(addressString)
        verify(addressParser).parse(addressString)
    }

    @Test
    fun `parser is not triggered when no address string is given`() {
        runner.run() //vararg == empty
        verify(addressParser, never()).parse(any())
    }

}