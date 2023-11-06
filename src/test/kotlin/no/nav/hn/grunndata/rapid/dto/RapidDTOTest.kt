package no.nav.hn.grunndata.rapid.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import no.nav.hm.grunndata.rapid.dto.*
import org.junit.jupiter.api.Test
import java.awt.SystemColor.text
import java.time.LocalDateTime
import java.util.*
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Local


class RapidDTOTest() {

    private val supplier = SupplierDTO(
        id = UUID.randomUUID(), identifier = "test-123", createdBy = "test", updatedBy = "test",
        name = "testsupplier", info = SupplierInfo()
    )

    private val objectMapper: ObjectMapper = ObjectMapper()
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
        val productDTO = ProductRapidDTO(
            id = UUID.randomUUID(),
            supplier = supplier,
            title = "Dette er produkt 1",
            articleName = "Dette er produkt 1 med og med",
            attributes = Attributes(
                shortdescription = "En kort beskrivelse av produktet",
                text = "En lang beskrivelse av produktet",
                compatibleWidth = CompatibleWith(seriesIds = setOf(UUID.randomUUID()) )
            ),
            hmsArtNr = "111",
            identifier = "hmdb-111",
            supplierRef = "eksternref-111",
            isoCategory = "12001314",
            accessory = false,
            sparePart = true,
            seriesId = "series-123",
            techData = listOf(TechData(key = "maksvekt", unit = "kg", value = "120")),
            media = setOf (
                MediaInfo(
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
        val registration = ProductRegistrationRapidDTO(
            id = productDTO.id,
            draftStatus = DraftStatus.DRAFT,
            adminStatus = AdminStatus.PENDING,
            registrationStatus = RegistrationStatus.ACTIVE,
            message = "Melding til leverandør",
            createdByAdmin = false,
            expired = null,
            published = null,
            productDTO = productDTO,
            version = 1,
            createdBy = "REGISTER",
            updatedBy = "REGISTER"
        )
        println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(registration))

    }

    @Test
    fun registrationDTODeserializer() {
        val registrationDTO = objectMapper.readValue(
            RapidDTO::class.java.classLoader
                .getResourceAsStream("registration.json"), ProductRegistrationRapidDTO::class.java
        )
        registrationDTO.adminStatus shouldBe AdminStatus.PENDING

    }

    @Test
    fun agreementRegistrationDTOSerializer() {
        val agreementDTO = AgreementDTO(
            id = UUID.randomUUID(),
            identifier = "HMDB-123",
            title = "Manuelle Rullestoler",
            resume = "Kort Beskrivelse av avtalen",
            text = "En lang beskrivelse av avtalen",
            status = AgreementStatus.ACTIVE,
            reference = "1234-1",
            published = LocalDateTime.now(),
            expired = LocalDateTime.now().plusYears(2),
            isoCategory = listOf("12001314"),
            attachments = listOf(
                AgreementAttachment(
                    title = "Endringskatalog", description = "En beskrivelse", media = listOf(
                        MediaInfo(
                            source = MediaSourceType.HMDB,
                            uri = "123.pdf",
                            type = MediaType.IMAGE,
                            sourceUri = "123.pdf",
                            text = "Beskrivelse av dokumentet"
                        )
                    )
                )
            ),
            posts = listOf(
                AgreementPost(
                    identifier = "HMDB-43",
                    nr = 1,
                    title = "post 1",
                    description = "Beskrivelse av post"
                )
            ),
            createdBy = "HMDB",
            updatedBy = "HMDB",
            created = LocalDateTime.now(),
            updated = LocalDateTime.now()
        )

        val agreementRegistrationRapidDTO = AgreementRegistrationRapidDTO(
            id = agreementDTO.id,
            draftStatus = DraftStatus.DONE,
            created = LocalDateTime.now(),
            updated = LocalDateTime.now(),
            createdByUser = "admin",
            updatedByUser = "admin",
            published = LocalDateTime.now(),
            expired = LocalDateTime.now().plusYears(2),
            createdBy = "REGISTER",
            updatedBy = "REGISTER",
            agreementDTO = agreementDTO
        )
        println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(agreementRegistrationRapidDTO))
    }

    @Test
    fun agreementDTODeserializer() {
        val agreementRegistrationRapidDTO = objectMapper.readValue(
            RapidDTO::class.java.classLoader
                .getResourceAsStream("agreementRegistration.json"), AgreementRegistrationRapidDTO::class.java
        )
        agreementRegistrationRapidDTO.agreementDTO.reference shouldBe "1234-1"

    }

    @Test
    fun seriesDTODeserializer() {
            val seriesRapidDTO = objectMapper.readValue(
                RapidDTO::class.java.classLoader
                .getResourceAsStream("series.json"), SeriesRapidDTO::class.java
        )
        seriesRapidDTO.title shouldBe "Dette er en serie"
        seriesRapidDTO.text shouldBe "en beskrivelse"
    }
}
