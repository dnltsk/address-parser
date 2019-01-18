package org.dnltsk.addressparser

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RunnerTest{

    @Test
    fun contextTest() {
        assertThat(true).isTrue()
    }
}