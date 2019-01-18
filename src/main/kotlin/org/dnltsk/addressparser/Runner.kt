package org.dnltsk.addressparser

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service

@Service
class Runner : CommandLineRunner {

    override fun run(vararg args: String?) {
        println("Hi!")
    }

}