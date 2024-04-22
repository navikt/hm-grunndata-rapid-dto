package no.nav.hn.grunndata.rapid.dto

import io.kotest.matchers.shouldBe
import no.nav.hm.grunndata.rapid.dto.SeriesRapidDTO
import no.nav.hm.grunndata.rapid.dto.SeriesStatus
import org.junit.jupiter.api.Test
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.util.UUID

class SeriesRapidDTOTest {

    @Test
    fun seriesRapidDTOTest() {
        val uuid = UUID.fromString("a7d6f44f-5b11-47e2-94dd-288a46c2fee0")
        val series = SeriesRapidDTO (
            id = uuid,
            status = SeriesStatus.ACTIVE,
            title = "Dette er en tittel",
            text = "Dette er en tekst",
            isoCategory = "12001314",
            supplierId = uuid,
            identifier = "hmdb-111",
            createdBy = "HMDB",
            updatedBy = "HMDB",
            created = LocalDateTime.MAX,
            updated = LocalDateTime.MAX,
            expired = LocalDateTime.MAX
        )
        //Serialize the object to a file
        FileOutputStream("./src/test/resources/latest/Series.json").use {
            it.write(objectMapper().writerWithDefaultPrettyPrinter().writeValueAsBytes(series))
        }

        //Deserialize old version if it works with this version
        val oldSeries = objectMapper().readValue(
            this::class.java.getResourceAsStream("/old/Series.json"),
            SeriesRapidDTO::class.java
        )

        oldSeries.title shouldBe "Dette er en tittel"
        oldSeries.id shouldBe uuid
    }
}