package no.nav.hn.grunndata.rapid.dto

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import no.nav.hm.grunndata.rapid.dto.AdminStatus
import no.nav.hm.grunndata.rapid.dto.DraftStatus
import no.nav.hm.grunndata.rapid.dto.MediaInfo
import no.nav.hm.grunndata.rapid.dto.MediaSourceType
import no.nav.hm.grunndata.rapid.dto.SeriesAttributes
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
        val uuid = UUID.fromString("03f4f08f-36ec-4b7e-87ca-5542612a7ee5")
        val seriesRegistration = SeriesRegistrationRapidDTO(
            id = uuid,
            supplierId = uuid,
            identifier = "HMDB-123",
            title = "Manuelle Rullestoler",
            text = "En lang beskrivelse av serien",
            isoCategory = "12001314",
            createdBy = "HMDB",
            updatedBy = "HMDB",
            created = LocalDateTime.MAX,
            updated = LocalDateTime.MAX,
            status = SeriesStatus.ACTIVE,
            adminStatus = AdminStatus.APPROVED,
            expired = LocalDateTime.MAX,
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
                        sourceUri = "https://localhost/123.jpg",
                        updated = LocalDateTime.MAX
                    )
                ),
                attributes = SeriesAttributes(keywords = listOf("keyword1", "keyword2"))
            )
        )
        //Serialize the object to a file
        FileOutputStream("./src/test/resources/latest/SeriesRegistration.json").use {
            it.write(objectMapper().writerWithDefaultPrettyPrinter().writeValueAsBytes(seriesRegistration))
        }

        //Deserialize old version if it works with this version
        val oldSeriesRegistration = objectMapper().readValue(
            this::class.java.getResourceAsStream("/old/SeriesRegistration.json"),
            SeriesRegistrationRapidDTO::class.java
        )
        oldSeriesRegistration.shouldNotBeNull()
        oldSeriesRegistration.title shouldBe "Manuelle Rullestoler"

    }
}