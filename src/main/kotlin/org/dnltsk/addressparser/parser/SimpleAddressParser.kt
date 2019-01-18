package org.dnltsk.addressparser.parser

import org.dnltsk.addressparser.Address
import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class SimpleAddressParser : AddressParser {

    internal val splitPattern = Pattern.compile("\\s")

    override fun fitsToInputAddress(inputAddress: String): Boolean {
        return inputAddress.split(splitPattern).size == 2
    }

    override fun parse(inputAddress: String): Address {
        val addressTokens = inputAddress.split(splitPattern)
        return Address(
            street = addressTokens[0],
            housenumber = addressTokens[1]
        )
    }

}