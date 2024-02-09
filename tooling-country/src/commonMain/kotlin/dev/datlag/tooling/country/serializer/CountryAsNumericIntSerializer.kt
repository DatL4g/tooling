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

@Serializer(forClass = Country::class)
object CountryAsNumericIntSerializer : KSerializer<Country?> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Country", PrimitiveKind.INT)

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): Country? {
        return if (decoder.decodeNotNullMark()) {
            Country.parseOrNull(decoder.decodeInt())
        } else {
            decoder.decodeNull()
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: Country?) {
        if (value != null) {
            encoder.encodeNotNullMark()
            encoder.encodeInt(value.numeric.code)
        } else {
            encoder.encodeNull()
        }
    }
}