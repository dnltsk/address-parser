package org.dnltsk.addressparser.parser

import org.dnltsk.addressparser.Address
import org.springframework.stereotype.Service
import java.text.ParseException
import java.util.regex.Pattern

@Service
class AddressParser{

    @Throws(ParseException::class)
    fun parse(inputAddress: String): Address {
        val tokens = inputAddress.split(Pattern.compile("\\s"))
        if(tokens.size == 2){
            return Address(tokens[0], tokens[1])
        }
        throw ParseException("Address must have two tokens", 0)
    }

}