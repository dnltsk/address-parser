package org.dnltsk.addressparser

import org.dnltsk.addressparser.parser.AddressParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service

@Service
class Runner : CommandLineRunner {

    @Autowired
    private lateinit var addressParser: AddressParser

    @Autowired
    private lateinit var consolePrinter: ConsolePrinter

    override fun run(vararg args: String?) {
        if(!args.isEmpty()){
            val inputAddress = args!!.get(0)!!
            val address = addressParser.parse(inputAddress)
            consolePrinter.printResult(inputAddress, address)
        }else{
            consolePrinter.printError()
        }
    }

}