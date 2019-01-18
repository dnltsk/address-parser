package org.dnltsk.addressparser

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * TODO: instead of System.outs we should use a proper ConsoleWriter!
 */
@Service
class ConsolePrinter {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    fun printResults(inputAddress: String, addresses: List<Address>) {
        println(inputAddress)
        addresses.forEach {
            println(objectMapper.writeValueAsString(it))
        }
    }

    fun printEmptyResult(inputAddress: String) {
        System.err.println(inputAddress)
        System.err.println("Couldn't find a parser for the given address.")
    }

    fun printNoAddressProvidedError() {
        System.err.println("No address string provided! Use \"Winterallee 3\" (with quotes) for instance.")
    }


}