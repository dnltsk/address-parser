package org.dnltsk.addressparser.parser

import org.dnltsk.addressparser.Address

interface AddressParser {

    fun isEffecting(inputAddress: String): Boolean
    fun parse(inputAddress: String): Address

}

