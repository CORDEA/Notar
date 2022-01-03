package jp.cordea.notar.api

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

data class PageProperties(
    val title: List<PageProperty>,
    val properties: List<PageProperty>
)

sealed class PageProperty {
    data class Text(
        val content: String
    ) : PageProperty()

    object Unknown : PageProperty()
}

class PagePropertiesSerializer : KSerializer<PageProperties> {
    private val serializer = MapSerializer(String.serializer(), JsonObject.serializer())
    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun deserialize(decoder: Decoder): PageProperties {
        val map = decoder.decodeSerializableValue(serializer)
        return PageProperties(
            title = map.getValue("title").getValue("title").jsonArray.map { element ->
                deserialize(element.jsonObject)
            },
            properties = emptyList()
        )
    }

    override fun serialize(encoder: Encoder, value: PageProperties) {
        throw NotImplementedError()
    }

    private fun deserialize(obj: JsonObject): PageProperty {
        return when (obj.getValue("type").jsonPrimitive.content) {
            "text" -> PageProperty.Text(
                obj
                    .getValue("plain_text")
                    .jsonPrimitive
                    .content
            )
            else -> PageProperty.Unknown
        }
    }
}
