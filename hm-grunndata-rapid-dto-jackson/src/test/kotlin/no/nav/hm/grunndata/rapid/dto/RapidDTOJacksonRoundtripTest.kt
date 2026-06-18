package no.nav.hm.grunndata.rapid.dto



import com.fasterxml.jackson.annotation.JsonInclude
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import tools.jackson.databind.DeserializationFeature
import tools.jackson.databind.SerializationFeature
import tools.jackson.databind.cfg.DateTimeFeature
import tools.jackson.databind.cfg.EnumFeature
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.KotlinModule
import java.lang.Boolean
import java.lang.Byte
import java.lang.Double
import java.lang.Float
import java.lang.Long
import java.lang.Short
import java.lang.reflect.Array
import java.lang.reflect.Modifier
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID

class RapidDTOJacksonRoundtripTest {

    val objectMapper = JsonMapper.builderWithJackson2Defaults()
        .addModule(KotlinModule.Builder().build())
        .disable(DateTimeFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
        .disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(EnumFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true)
        .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
        .changeDefaultPropertyInclusion { it.withValueInclusion(JsonInclude.Include.NON_NULL) }
        .build()

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
           return io.github.classgraph.ClassGraph()
                .acceptPackages("no.nav.hm.grunndata.rapid.dto")
                .enableClassInfo()
                .scan()
                .use { scan ->
                    scan.getAllClasses()
                        .filter { it.packageName == "no.nav.hm.grunndata.rapid.dto" } // exact package only
                        .filterNot { it.isInterface || it.isAbstract || it.name.contains('$') }
                        .sortedBy { it.name }
                        .map { it.loadClass() }
                }
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
        type == Integer.TYPE || type == Integer::class.java -> 1
        type == Long.TYPE || type == Long::class.java -> 1L
        type == Boolean.TYPE || type == Boolean::class.java -> true
        type == Double.TYPE || type == Double::class.java -> 1.0
        type == Float.TYPE || type == Float::class.java -> 1.0f
        type == Short.TYPE || type == Short::class.java -> 1.toShort()
        type == Byte.TYPE || type == Byte::class.java -> 1.toByte()
        type == Character.TYPE || type == Character::class.java -> 'a'
        type == UUID::class.java -> UUID.fromString("00000000-0000-0000-0000-000000000001")
        type == LocalDateTime::class.java -> LocalDateTime.of(2025, 1, 1, 12, 0)
        type == LocalDate::class.java -> LocalDate.of(2025, 1, 1)
        type == LocalTime::class.java -> LocalTime.of(12, 0)
        type.isEnum -> type.enumConstants.first()
        Set::class.java.isAssignableFrom(type) -> emptySet<Any>()
        Map::class.java.isAssignableFrom(type) -> emptyMap<Any, Any>()
        List::class.java.isAssignableFrom(type) || Collection::class.java.isAssignableFrom(type) -> emptyList<Any>()
        type.isArray -> Array.newInstance(type.componentType, 0)
        type.isInterface || Modifier.isAbstract(type.modifiers) -> null
        else -> sampleInstance(type, depth)
    }
}
