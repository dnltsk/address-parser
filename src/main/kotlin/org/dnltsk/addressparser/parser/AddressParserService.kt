package org.dnltsk.addressparser.parser

import org.dnltsk.addressparser.Address
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AddressParserService {

    @Autowired
    private lateinit var addressParsers: List<AddressParser>

    /**
     * returns a list of possible transformed address objects
     */
    fun parse(inputAddress: String): List<Address> {
        return addressParsers.mapNotNull {
            if (it.fitsToInputAddress(inputAddress)) {
                it.parse(inputAddress)
            } else {
                null
            }
        }
    }

}