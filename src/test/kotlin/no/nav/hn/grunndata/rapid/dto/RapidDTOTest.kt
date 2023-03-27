package no.nav.hn.grunndata.rapid.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import no.nav.hm.grunndata.rapid.dto.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*


class RapidDTOTest() {

    val supplier = SupplierDTO(id = UUID.randomUUID(), identifier = "test-123", createdBy = "test", updatedBy = "test",
        name = "testsupplier", info = SupplierInfo()
    )

    val objectMapper = ObjectMapper()
        .registerModule(kotlinModule())
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true)
        .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)

    @Test
    fun testRapidDTO() {
        rapidDTOVersion.shouldNotBeNull()
        supplier.javaClass.simpleName shouldBe "SupplierDTO"
    }

    @Test
    fun registrationDTOSerializer() {
        val productDTO = ProductDTO(
            id = UUID.randomUUID(),
            supplier = supplier,
            title = "Dette er produkt 1",
            articleName = "Dette er produkt 1 med og med",
            attributes = mapOf(
                AttributeNames.shortdescription to "En kort beskrivelse av produktet",
                AttributeNames.text to "En lang beskrivelse av produktet"
            ),
            hmsArtNr = "111",
            identifier = "hmdb-111",
            supplierRef = "eksternref-111",
            isoCategory = "12001314",
            accessory = false,
            sparePart = false,
            seriesId = "series-123",
            techData = listOf(TechData(key = "maksvekt", unit = "kg", value = "120")),
            media = listOf(
                MediaDTO(
                    uri = "123.jpg",
                    text = "bilde av produktet",
                    source = MediaSourceType.EXTERNALURL,
                    sourceUri = "https://ekstern.url/123.jpg"
                )
            ),
            agreementInfo = AgreementInfo(
                id = UUID.randomUUID(),
                identifier = "hmdbid-1",
                rank = 1,
                postNr = 1,
                reference = "AV-142",
                expired = LocalDateTime.now()
            ),
            createdBy = "REGISTER",
            updatedBy = "REGISTER"
        )
        val registration = ProductRegistrationDTO(
            id = productDTO.id,
            supplierId = productDTO.supplier.id,
            supplierRef = productDTO.supplierRef,
            hmsArtNr = productDTO.hmsArtNr,
            title = productDTO.title,
            articleName = productDTO.articleName,
            draftStatus = DraftStatus.DRAFT,
            adminStatus = AdminStatus.PENDING,
            status = RegistrationStatus.ACTIVE,
            message = "Melding til leverandør",
            adminInfo = null,
            createdByAdmin = false,
            expired = null,
            published = null,
            updatedByUser = "email",
            createdByUser = "email",
            productDTO = productDTO,
            version = 1,
            createdBy = "REGISTER",
            updatedBy = "REGISTER"
        )
        println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(registration))

    }

    @Test
    fun registrationDTODeserializer() {
        val registrationDTO = objectMapper.readValue(RapidDTO::class.java.classLoader
            .getResourceAsStream("registration.json"), ProductRegistrationDTO::class.java)
        registrationDTO.adminStatus shouldBe AdminStatus.PENDING

    }
}
