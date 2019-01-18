package org.dnltsk.addressparser.parser

import org.assertj.core.api.Assertions.assertThat
import org.dnltsk.addressparser.Address
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SimpleAddressParserTest {

    @Autowired
    private lateinit var addressParser: SimpleAddressParser

    @Test
    fun `most simple cases are parsed correctly`() {
        val testCases = listOf(
            Pair("Winterallee 3", Address("Winterallee", "3")),
            Pair("Musterstrasse 45", Address("Musterstrasse", "45")),
            Pair("Blaufeldweg 123B", Address("Blaufeldweg", "123B"))
        )

        testCases.forEachIndexed { i, it ->
            val inputAddress = it.first
            val expectedAddress = it.second
            assertThat(addressParser.fitsToInputAddress(inputAddress))
                .`as`("issues with test case $i")
                .isEqualTo(true)
            assertThat(addressParser.parse(inputAddress))
                .`as`("issues with test case $i")
                .isEqualToComparingFieldByField(expectedAddress)
        }
    }

    @Test
    fun `not simple cases are ignored`() {
        val testCases = listOf(
            "",//empty string
            "   ",//just whitespaces
            "invalid_address",
            "Am Bächle 23",
            "Auf der Vogelwiese 23 b",
            "4, rue de la revolution",
            "200 Broadway Av",
            "Calle Aduana, 29",
            "Calle 39 No 1540"
        )

        testCases.forEach {
            assertThat(addressParser.fitsToInputAddress(it))
                .`as`("issues with test case $it")
                .isEqualTo(false)
        }
    }

}