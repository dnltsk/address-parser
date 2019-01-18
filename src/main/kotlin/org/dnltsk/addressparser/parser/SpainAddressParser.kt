package org.dnltsk.addressparser.parser

import org.dnltsk.addressparser.Address
import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class SpainAddressParser : AddressParser {

    //TODO: improve parsing (current regex is not good enough!)

    private val usaSignalWords = listOf("calle")
    private val splitPattern = Pattern.compile("\\s+")
    private val groupRegex = Regex("^(.+)(,|No)\\s+(.+)\$")

    override fun isEffecting(inputAddress: String): Boolean {
        val tokens = inputAddress.trim().split(splitPattern)
        val groupMatches = groupRegex.find(inputAddress.trim())
        return tokens.size > 2
            && tokens.any { usaSignalWords.contains(it.toLowerCase()) }
            && groupMatches != null
            && groupMatches.groupValues.size == 4
    }

    override fun parse(inputAddress: String): Address {
        val groupMatches = groupRegex.find(inputAddress.trim())!!
        return Address(
            street = groupMatches.groupValues[1].trim(),
            housenumber = groupMatches.groupValues[3].trim()
        )
    }
}