package org.dnltsk.addressparser.parser

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class AddressParserTest{

    @Autowired
    private lateinit var addressParser: AddressParser

    @Test
    fun `dummy response is returned`() {
        val address = addressParser.parse("dummy-address")
        assertThat(address).isNotNull
    }
}