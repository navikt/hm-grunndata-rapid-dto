package no.nav.hn.grunndata.rapid.dto

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
import no.nav.hm.grunndata.rapid.dto.ProductRegistrationRapidDTO
import org.junit.jupiter.api.Test
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.util.*

class AgreementRegistrationDTOTest {

    @Test
    fun testAgreementRegistrationDTO() {
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
                    id = UUID.randomUUID(),
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
        //Serialize the object to a file
        FileOutputStream("./src/test/resources/latest/AgreementRegistration.json").use {
            it.write(objectMapper().writerWithDefaultPrettyPrinter().writeValueAsBytes(agreementRegistrationRapidDTO))
        }

        //Deserialize old version if it works with this version
        val oldAgreementRegistration = objectMapper().readValue(
            this::class.java.getResourceAsStream("/old/AgreementRegistration.json"),
            AgreementRegistrationRapidDTO::class.java
        )

        oldAgreementRegistration.shouldNotBeNull()
        oldAgreementRegistration.agreementDTO.title shouldBe "Manuelle Rullestoler"
    }
}