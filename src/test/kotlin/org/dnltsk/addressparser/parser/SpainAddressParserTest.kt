package org.dnltsk.addressparser.parser

import org.assertj.core.api.Assertions
import org.dnltsk.addressparser.Address
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SpainAddressParserTest{

    @Autowired
    private lateinit var addressParser: SpainAddressParser

    @Test
    fun `Spain cases are parsed correctly`() {
        val testCases = listOf(
            Pair("Calle Aduana, 29", Address("Calle Aduana", "29")),
            Pair("Calle 39 No 1540", Address("Calle 39", "1540"))
        )

        testCases.forEachIndexed { i, it ->
            val inputAddress = it.first
            val expectedAddress = it.second
            Assertions.assertThat(addressParser.isEffecting(inputAddress))
                .`as`("issues with test case $i")
                .isEqualTo(true)
            Assertions.assertThat(addressParser.parse(inputAddress))
                .`as`("issues with test case $i")
                .isEqualToComparingFieldByField(expectedAddress)
        }
    }

    @Test
    fun `not Spain cases are ignored`() {
        val testCases = listOf(
            "",//empty string
            "  ",//two spaces
            "   ",//just whitespaces
            "invalid_address",
            "Winterallee 3",
            "Musterstrasse 45",
            "Blaufeldweg 123B",
            "Am Bächle 23",
            "Auf der Vogelwiese 23 b",
            "4, rue de la revolution",
            "200 Broadway Av"
        )

        testCases.forEach {
            Assertions.assertThat(addressParser.isEffecting(it))
                .`as`("issues with test case $it")
                .isEqualTo(false)
        }
    }

}