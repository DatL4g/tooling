package dev.datlag.tooling.country.serializer

import dev.datlag.tooling.country.Country
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Optional serializer for [Country] objects.
 *
 * Parses the [Country.Format.Alpha3] code to [String].
 */
object CountryAsAlpha3StringSerializer : KSerializer<Country?> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Country", PrimitiveKind.STRING)

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): Country? {
        return if (decoder.decodeNotNullMark()) {
            Country.parseOrNull(decoder.decodeString())
        } else {
            decoder.decodeNull()
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: Country?) {
        if (value != null) {
            encoder.encodeNotNullMark()
            encoder.encodeString(value.alpha3.code)
        } else {
            encoder.encodeNull()
        }
    }
}