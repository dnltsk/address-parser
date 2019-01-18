package org.dnltsk.addressparser.parser

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.dnltsk.addressparser.Address
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.text.ParseException

@RunWith(SpringRunner::class)
@SpringBootTest
class AddressParserTest {

    @Autowired
    private lateinit var addressParser: AddressParser

    @Test
    fun `simple addresses are parsed correctly`() {
        val testCases = listOf(
            Pair("Winterallee 3", Address("Winterallee", "3")),
            Pair("Musterstrasse 45", Address("Musterstrasse", "45")),
            Pair("Blaufeldweg 123B", Address("Blaufeldweg", "123B"))
        )
        testCases.forEachIndexed { i, it ->
            val inputAddress = it.first
            val expectedAddress = it.second
            assertThat(addressParser.parse(inputAddress))
                .`as`("issues with test case $i")
                .isEqualToComparingFieldByField(expectedAddress)
        }
    }

    @Test
    fun `wrong addresses should throw a ParseException`() {
        val testCases = listOf(
            "wrongAddress",
            "wrong_address",
            "wrong address XXX"
        )
        testCases.forEachIndexed { i, inputAddress ->
            assertThatThrownBy {
                addressParser.parse(inputAddress)
            }
                .`as`("issues with test case $i")
                .isInstanceOf(ParseException::class.java)
        }
    }
}