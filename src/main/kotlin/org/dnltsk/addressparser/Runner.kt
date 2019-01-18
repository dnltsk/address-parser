package org.dnltsk.addressparser

import org.dnltsk.addressparser.parser.AddressParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import java.text.ParseException

@Service
class Runner : CommandLineRunner {

    @Autowired
    private lateinit var addressParser: AddressParser

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
        try {
            val address = addressParser.parse(inputAddress)
            consolePrinter.printResult(inputAddress, address)
        } catch (e: ParseException) {
            consolePrinter.printParseException(inputAddress, e)
        }
    }

}