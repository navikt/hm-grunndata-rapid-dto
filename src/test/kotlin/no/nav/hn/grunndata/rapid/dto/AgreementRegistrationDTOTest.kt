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
import org.junit.jupiter.api.Test
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.util.*

class AgreementRegistrationDTOTest {

    @Test
    fun testAgreementRegistrationDTO() {
        val uuid = UUID.fromString("636c264b-b974-43b6-b823-a11b9f532b97")
        val agreementDTO = AgreementDTO(
            id = uuid,
            identifier = "HMDB-123",
            title = "Manuelle Rullestoler",
            resume = "Kort Beskrivelse av avtalen",
            text = "En lang beskrivelse av avtalen",
            status = AgreementStatus.ACTIVE,
            reference = "1234-1",
            published = LocalDateTime.MAX,
            expired = LocalDateTime.MAX,
            isoCategory = listOf("12001314"),
            attachments = listOf(
                AgreementAttachment(
                    id = uuid,
                    title = "Endringskatalog", description = "En beskrivelse", media = listOf(
                        MediaInfo(
                            source = MediaSourceType.HMDB,
                            uri = "123.pdf",
                            type = MediaType.IMAGE,
                            sourceUri = "123.pdf",
                            text = "Beskrivelse av dokumentet",
                            updated = LocalDateTime.MAX
                        )
                    )
                )
            ),
            posts = listOf(
                AgreementPost(
                    id = uuid,
                    identifier = "HMDB-43",
                    nr = 1,
                    title = "post 1",
                    description = "Beskrivelse av post",
                    created = LocalDateTime.MAX
                )
            ),
            createdBy = "HMDB",
            updatedBy = "HMDB",
            created = LocalDateTime.MAX,
            updated = LocalDateTime.MAX
        )

        val agreementRegistrationRapidDTO = AgreementRegistrationRapidDTO(
            id = uuid,
            draftStatus = DraftStatus.DONE,
            created = LocalDateTime.MAX,
            updated = LocalDateTime.MAX,
            createdByUser = "admin",
            updatedByUser = "admin",
            published = LocalDateTime.MAX,
            expired = LocalDateTime.MAX,
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