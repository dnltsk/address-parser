package org.dnltsk.addressparser.parser

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class AddressParserServiceTest{

    /*
     * TODO: test AddressParserServiceTest correct behaviour of AddressParserService
     * (skipped due to issues with mocking & stubbing of List<AddressParser>)
     */

    @Test
    fun `dummy test`() {
        assertThat(true).isTrue()
    }
}