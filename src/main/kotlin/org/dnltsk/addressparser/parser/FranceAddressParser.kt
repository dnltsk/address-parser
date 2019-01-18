package org.dnltsk.addressparser.parser

import org.dnltsk.addressparser.Address
import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class FranceAddressParser : AddressParser {

    private val franceSignalWords = listOf("rue")
    private val splitPattern = Pattern.compile("\\s+")
    private val groupRegex = Regex("(^.+),\\s+(.+)$")

    override fun isEffecting(inputAddress: String): Boolean {
        val tokens = inputAddress.trim().split(splitPattern)
        val groupMatches = groupRegex.find(inputAddress.trim())
        return tokens.size > 2
            && tokens.any { franceSignalWords.contains(it.toLowerCase()) }
            && groupMatches != null
            && groupMatches.groupValues.size == 3
    }

    override fun parse(inputAddress: String): Address {
        val groupMatches = groupRegex.find(inputAddress.trim())!!
        return Address(
            street = groupMatches.groupValues[2],
            housenumber = groupMatches.groupValues[1]
        )
    }
}