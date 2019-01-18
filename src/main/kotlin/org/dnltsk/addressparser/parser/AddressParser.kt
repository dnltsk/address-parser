package org.dnltsk.addressparser.parser

import org.dnltsk.addressparser.Address

interface AddressParser {

    fun fitsToInputAddress(inputAddress: String): Boolean
    fun parse(inputAddress: String): Address

}

