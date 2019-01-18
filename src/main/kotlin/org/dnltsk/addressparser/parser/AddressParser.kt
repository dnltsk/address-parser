package org.dnltsk.addressparser.parser

import org.dnltsk.addressparser.Address
import org.springframework.stereotype.Service

@Service
class AddressParser{

    fun parse(address: String): Address {
        return Address("street", "1")
    }

}