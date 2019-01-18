package org.dnltsk.addressparser.parser

import org.dnltsk.addressparser.Address
import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class MoreComplexAddressParser : AddressParser {

    private val nonGermanSignalWords = listOf("broadway", "av", "rue", "calle")
    private val splitPattern = Pattern.compile("\\s+")
    private val groupRegex = Regex("^(\\D+)\\s+(\\d.+)\$")

    override fun isEffecting(inputAddress: String): Boolean {
        val tokens = inputAddress.trim().split(splitPattern)
        val groupMatches = groupRegex.find(inputAddress.trim())
        return tokens.size > 2
            && !tokens.any { nonGermanSignalWords.contains(it.toLowerCase()) }
            && groupMatches != null
            && groupMatches.groupValues.size == 3
    }

    override fun parse(inputAddress: String): Address {
        val groupMatches = groupRegex.find(inputAddress.trim())!!
        return Address(
            street = groupMatches.groupValues[1],
            housenumber = groupMatches.groupValues[2]
        )
    }
}