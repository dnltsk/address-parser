package org.dnltsk.addressparser

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.ParseException

/**
 * TODO: instead of System.outs we should use a proper ConsoleWriter!
 */
@Service
class ConsolePrinter{

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    fun printResult(inputAddress: String, address: Address){
        println(inputAddress)
        println(objectMapper.writeValueAsString(address))
    }

    fun printNoAddressProvidedError(){
        System.err.println("No address string provided! Use \"Winterallee 3\" (with quotes) for instance.")
    }

    fun printParseException(inputAddress: String, parseException: ParseException){
        System.err.println(inputAddress)
        System.err.println("Cannot parse given address: ${parseException.message}")
    }

}