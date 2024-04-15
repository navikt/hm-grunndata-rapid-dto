package no.nav.hn.grunndata.rapid.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import no.nav.hm.grunndata.rapid.dto.AgreementAttachment
import no.nav.hm.grunndata.rapid.dto.AgreementDTO
import no.nav.hm.grunndata.rapid.dto.AgreementPost
import no.nav.hm.grunndata.rapid.dto.AgreementRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.AgreementStatus
import no.nav.hm.grunndata.rapid.dto.DraftStatus
import no.nav.hm.grunndata.rapid.dto.MediaInfo
import no.nav.hm.grunndata.rapid.dto.MediaSourceType
import no.nav.hm.grunndata.rapid.dto.MediaType
import no.nav.hm.grunndata.rapid.dto.ProductAgreementRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.ProductAgreementStatus
import no.nav.hm.grunndata.rapid.dto.RapidDTO
import no.nav.hm.grunndata.rapid.dto.SeriesRapidDTO
import no.nav.hm.grunndata.rapid.dto.SupplierDTO
import no.nav.hm.grunndata.rapid.dto.SupplierInfo
import no.nav.hm.grunndata.rapid.dto.rapidDTOVersion
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.UUID


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
    fun seriesDTODeserializer() {
        val seriesRapidDTO = objectMapper.readValue(
            RapidDTO::class.java.classLoader
                .getResourceAsStream("series.json"), SeriesRapidDTO::class.java
        )
        seriesRapidDTO.title shouldBe "Dette er en serie"
        seriesRapidDTO.text shouldBe "en beskrivelse"
    }

    @Test
    fun productAgreementDTOSerializer() {
        val productAgreementRapidDTO = ProductAgreementRegistrationRapidDTO(
            id = UUID.randomUUID(),
            productId = UUID.randomUUID(),
            title = "beskrivelse av produktet",
            supplierId = UUID.randomUUID(),
            supplierRef = "eksternref-1",
            hmsArtNr = "111",
            agreementId = UUID.randomUUID(),
            reference = "AV-142",
            post = 1,
            rank = 1,
            status = ProductAgreementStatus.ACTIVE,
            createdBy = "REGISTER",
            created = LocalDateTime.now(),
            updated = LocalDateTime.now(),
            published = LocalDateTime.now(),
            expired = LocalDateTime.now().plusYears(4)
        )
        println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(productAgreementRapidDTO))
    }

    @Test
    fun productAgreementDTODeserializer() {
        val productAgreementRapidDTO = objectMapper.readValue(
            RapidDTO::class.java.classLoader
                .getResourceAsStream("productAgreementRegistration.json"),
            ProductAgreementRegistrationRapidDTO::class.java
        )
        productAgreementRapidDTO.title shouldBe "beskrivelse av produktet"
    }
}
