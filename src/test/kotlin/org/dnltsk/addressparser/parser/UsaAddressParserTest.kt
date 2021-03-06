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
class UsaAddressParserTest {

    @Autowired
    private lateinit var addressParser: UsaAddressParser

    @Test
    fun `USA cases are parsed correctly`() {
        val testCases = listOf(
            Pair("200 Broadway Av", Address("Broadway Av", "200"))
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
    fun `not USA cases are ignored`() {
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
            "Calle Aduana, 29",
            "Calle 39 No 1540"
        )

        testCases.forEach {
            Assertions.assertThat(addressParser.isEffecting(it))
                .`as`("issues with test case $it")
                .isEqualTo(false)
        }
    }

}
