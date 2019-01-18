package org.dnltsk.addressparser

import org.dnltsk.addressparser.parser.AddressParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service

@Service
class Runner : CommandLineRunner {

    @Autowired
    private lateinit var addressParser: AddressParser

    override fun run(vararg args: String?) {
        if(!args.isEmpty()){
            val addressString = args!!.get(0)!!
            println(addressString)
            println(addressParser.parse(addressString))
        }else{
            println("no address string defined. use, \"Winterallee 3\" (with quotes) for instance")
        }
    }

}