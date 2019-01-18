package org.dnltsk.addressparser

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ConsolePrinter{

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    fun printResult(inputAddress: String, address: Address){
        println(inputAddress)
        println(objectMapper.writeValueAsString(address))
    }

    fun printError(){
        System.err.println("No address string provided! Use \"Winterallee 3\" (with quotes) for instance.")
    }

}