package dev.datlag.tooling.country.serializer

import dev.datlag.tooling.country.Country
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Default serializer for [Country] objects.
 *
 * Parses the [Country.Code.Alpha2] code to [String].
 */
object CountryAsAlpha2StringSerializer : KSerializer<Country?> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Country", PrimitiveKind.STRING)

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): Country? {
        return if (decoder.decodeNotNullMark()) {
            Country.forCodeOrNull(decoder.decodeString())
        } else {
            decoder.decodeNull()
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: Country?) {
        if (value != null) {
            encoder.encodeNotNullMark()
            encoder.encodeString(value.codeAlpha2.code)
        } else {
            encoder.encodeNull()
        }
    }
}