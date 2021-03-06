package org.dnltsk.addressparser

import org.dnltsk.addressparser.parser.AddressParseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service

@Service
class Runner : CommandLineRunner {

    @Autowired
    private lateinit var parseService: AddressParseService

    @Autowired
    private lateinit var consolePrinter: ConsolePrinter

    override fun run(vararg args: String?) {
        if (!args.isEmpty()) {
            val inputAddress = args[0]!!
            triggerParse(inputAddress)
        } else {
            consolePrinter.printNoAddressProvidedError()
        }
    }

    private fun triggerParse(inputAddress: String) {
        val address = parseService.parse(inputAddress)
        if (address.isEmpty()) {
            consolePrinter.printEmptyResult(inputAddress)
        } else {
            consolePrinter.printResults(inputAddress, address)
        }
    }

}