package no.nav.hn.grunndata.rapid.dto


import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import no.nav.hm.grunndata.rapid.dto.AgreementInfo
import no.nav.hm.grunndata.rapid.dto.Attributes
import no.nav.hm.grunndata.rapid.dto.CompatibleWith
import no.nav.hm.grunndata.rapid.dto.MediaInfo
import no.nav.hm.grunndata.rapid.dto.MediaSourceType
import no.nav.hm.grunndata.rapid.dto.ProductAgreementStatus
import no.nav.hm.grunndata.rapid.dto.ProductRapidDTO
import no.nav.hm.grunndata.rapid.dto.TechData
import org.junit.jupiter.api.Test
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.util.*

class ProductRapidDTOTest {


    @Test
    fun testProductRapidDTO() {
        val uuid = UUID.fromString("0d527ba0-d71e-4df5-8b1c-62e6f297e51e")
        val productDTO = ProductRapidDTO(
            id = uuid,
            supplier = supplier,
            created = LocalDateTime.MAX,
            updated = LocalDateTime.MAX,
            expired = LocalDateTime.MAX,
            published = LocalDateTime.MAX,
            title = "Dette er produkt 1",
            articleName = "Dette er produkt 1 med og med",
            attributes = Attributes(
                shortdescription = "En kort beskrivelse av produktet",
                text = "En lang beskrivelse av produktet",
                compatibleWidth = CompatibleWith(seriesIds = setOf(uuid))
            ),
            hmsArtNr = "111",
            identifier = "hmdb-111",
            supplierRef = "eksternref-111",
            isoCategory = "12001314",
            accessory = false,
            sparePart = true,
            seriesUUID = uuid,
            seriesId = "series-123",
            techData = listOf(TechData(key = "maksvekt", unit = "kg", value = "120")),
            media = setOf(
                MediaInfo(
                    uri = "123.jpg",
                    text = "bilde av produktet",
                    source = MediaSourceType.EXTERNALURL,
                    sourceUri = "https://ekstern.url/123.jpg",
                    updated = LocalDateTime.MAX
                )
            ),
            agreementInfo = AgreementInfo(
                id = uuid,
                identifier = "hmdbid-1",
                rank = 1,
                postNr = 1,
                reference = "AV-142",
                expired = LocalDateTime.MAX,
                status = ProductAgreementStatus.ACTIVE
            ),
            agreements = listOf(
                AgreementInfo(
                    id = uuid,
                    identifier = "hmdbid-1",
                    rank = 1,
                    postNr = 1,
                    reference = "AV-142",
                    expired = LocalDateTime.MAX,
                    status = ProductAgreementStatus.ACTIVE
                ),
                AgreementInfo(
                    id = uuid,
                    identifier = "hmdbid-1",
                    rank = 2,
                    postNr = 2,
                    reference = "AV-143",
                    expired = LocalDateTime.MAX,
                    status = ProductAgreementStatus.ACTIVE
                )
            ),
            createdBy = "REGISTER",
            updatedBy = "REGISTER"
        )
        //Serialize the object to a file
        FileOutputStream("./src/test/resources/latest/Product.json").use {
            it.write(objectMapper().writerWithDefaultPrettyPrinter().writeValueAsBytes(productDTO))
        }
        //Deserialize old version if it works with this version
        val oldProduct = objectMapper().readValue(
            this::class.java.getResourceAsStream("/old/Product.json"),
            ProductRapidDTO::class.java
        )
        oldProduct.shouldNotBeNull()
        oldProduct.title shouldBe "Dette er produkt 1"
    }

}