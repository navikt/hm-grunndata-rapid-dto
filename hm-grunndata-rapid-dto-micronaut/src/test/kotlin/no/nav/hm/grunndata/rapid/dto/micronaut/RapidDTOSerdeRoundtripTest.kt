package no.nav.hm.grunndata.rapid.dto.micronaut

import io.micronaut.serde.ObjectMapper
import io.micronaut.serde.annotation.SerdeImport
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.lang.reflect.Modifier
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID

@MicronautTest
class RapidDTOSerdeRoundtripTest(private val objectMapper: ObjectMapper) {

    @TestFactory
    fun `all SerdeImport types should roundtrip`() =
        importedTypes().map { type ->
            DynamicTest.dynamicTest("roundtrip ${type.simpleName}") {
                val instance = sampleInstance(type)
                val json = objectMapper.writeValueAsString(instance)
                val restored = objectMapper.readValue(json, type)

                assertNotNull(restored, "Deserialized value is null for ${type.name}")
            }
        }

    private fun importedTypes(): List<Class<*>> {
        return RapidDTOSerdeImportConfig::class.java
            .getAnnotationsByType(SerdeImport::class.java)
            .map { it.value.java }
            .filterNot { it.isInterface || Modifier.isAbstract(it.modifiers) }
            .distinct()
            .sortedBy { it.name }
    }

    private fun sampleInstance(clazz: Class<*>, depth: Int = 0): Any {
        require(depth < 8) { "Max recursion depth reached while creating ${clazz.name}" }

        if (clazz.isEnum) return clazz.enumConstants.first()

        val constructor = clazz.declaredConstructors
            .filter { Modifier.isPublic(it.modifiers) }
            .minByOrNull { it.parameterCount }
            ?: error("No public constructor for ${clazz.name}")

        val args = constructor.parameterTypes
            .map { sampleValue(it, depth + 1) }
            .toTypedArray()

        return constructor.newInstance(*args)
    }

    private fun sampleValue(type: Class<*>, depth: Int): Any? = when {
        type == String::class.java -> "test"
        type == java.lang.Integer.TYPE || type == java.lang.Integer::class.java -> 1
        type == java.lang.Long.TYPE || type == java.lang.Long::class.java -> 1L
        type == java.lang.Boolean.TYPE || type == java.lang.Boolean::class.java -> true
        type == java.lang.Double.TYPE || type == java.lang.Double::class.java -> 1.0
        type == java.lang.Float.TYPE || type == java.lang.Float::class.java -> 1.0f
        type == java.lang.Short.TYPE || type == java.lang.Short::class.java -> 1.toShort()
        type == java.lang.Byte.TYPE || type == java.lang.Byte::class.java -> 1.toByte()
        type == java.lang.Character.TYPE || type == java.lang.Character::class.java -> 'a'
        type == UUID::class.java -> UUID.fromString("00000000-0000-0000-0000-000000000001")
        type == LocalDateTime::class.java -> LocalDateTime.of(2025, 1, 1, 12, 0)
        type == LocalDate::class.java -> LocalDate.of(2025, 1, 1)
        type == LocalTime::class.java -> LocalTime.of(12, 0)
        type.isEnum -> type.enumConstants.first()
        Set::class.java.isAssignableFrom(type) -> emptySet<Any>()
        Map::class.java.isAssignableFrom(type) -> emptyMap<Any, Any>()
        List::class.java.isAssignableFrom(type) || Collection::class.java.isAssignableFrom(type) -> emptyList<Any>()
        type.isArray -> java.lang.reflect.Array.newInstance(type.componentType, 0)
        type.isInterface || Modifier.isAbstract(type.modifiers) -> null
        else -> sampleInstance(type, depth)
    }
}
