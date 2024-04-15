package no.nav.hn.grunndata.rapid.dto

import no.nav.hm.grunndata.rapid.dto.AdminStatus
import no.nav.hm.grunndata.rapid.dto.AgreementRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.DraftStatus
import no.nav.hm.grunndata.rapid.dto.MediaInfo
import no.nav.hm.grunndata.rapid.dto.MediaSourceType
import no.nav.hm.grunndata.rapid.dto.SeriesData
import no.nav.hm.grunndata.rapid.dto.SeriesRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.SeriesStatus
import org.junit.jupiter.api.Test
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.util.*

class SeriesRegistrationDTOTest {

    @Test
    fun testSeriesRegistrationDTO() {
        val id = UUID.randomUUID()
        val seriesRegistration = SeriesRegistrationRapidDTO(
            id = id,
            supplierId = supplier.id,
            identifier = "HMDB-123",
            title = "Manuelle Rullestoler",
            text = "En lang beskrivelse av serien",
            isoCategory = "12001314",
            createdBy = "HMDB",
            updatedBy = "HMDB",
            created = LocalDateTime.now(),
            updated = LocalDateTime.now(),
            status = SeriesStatus.ACTIVE,
            adminStatus = AdminStatus.APPROVED,
            expired = LocalDateTime.now().plusYears(2),
            draftStatus = DraftStatus.DONE,
            count = 1,
            version = 1,
            createdByUser = "admin",
            updatedByUser = "admin",
            createdByAdmin = true,
            seriesData = SeriesData(
                media = setOf(
                    MediaInfo(
                        uri = "123.jog",
                        text = "bilde av serien",
                        source = MediaSourceType.REGISTER,
                        sourceUri = "https://localhost/123.jpg"
                    )
                )
            )
        )
        //Serialize the object to a file
        FileOutputStream("./src/test/resources/latest/SeriesRegistration.json").use {
            it.write(objectMapper().writerWithDefaultPrettyPrinter().writeValueAsBytes(seriesRegistration))
        }

        //Deserialize old version if it works with this version
        val oldAgreementRegistration = objectMapper().readValue(
            this::class.java.getResourceAsStream("/old/SeriesRegistration.json"),
            SeriesRegistrationRapidDTO::class.java
        )
    }
}