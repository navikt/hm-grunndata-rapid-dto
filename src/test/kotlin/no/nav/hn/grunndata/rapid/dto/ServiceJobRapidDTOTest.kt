package no.nav.hn.grunndata.rapid.dto

import io.kotest.matchers.shouldBe
import no.nav.hm.grunndata.rapid.dto.ServiceAgreementInfo
import no.nav.hm.grunndata.rapid.dto.ServiceAgreementStatus
import no.nav.hm.grunndata.rapid.dto.ServiceAttributes
import no.nav.hm.grunndata.rapid.dto.ServiceJobRapidDTO
import org.junit.jupiter.api.Test
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.util.UUID

class ServiceJobRapidDTOTest {

    @Test
    fun serviceJobRapidDTOTest() {
        val uuid = UUID.fromString("b8d6f44f-5b11-47e2-94dd-288a46c2fee1")
        val  supplierId = UUID.fromString("c1a2b3c4-d5e6-47f8-90ab-1c2d3e4f5a6b")
        val agreementId = UUID.fromString("d4e5f6a7-b8c9-40d1-92ab-3c4d5e6f7a8b")
        val serviceJob = ServiceJobRapidDTO(
            id = uuid,
            title = "Repair job for wheelchair",
            supplierId = supplierId,
            supplierRef = "SUPP-12345",
            hmsNr = "123456",
            isoCategory = "16021501",
            updatedBy = "REGISTER",
            createdBy = "REGISTER",
            attributes = ServiceAttributes(
                keywords = setOf("repair", "wheelchair"),
                url = "https://example.com/repair-service",
                text = "We provide repair services for wheelchairs."
            ),
            agreements = listOf(ServiceAgreementInfo(
                agreementId = agreementId,
                published = LocalDateTime.MIN,
                expired = LocalDateTime.MAX,
                status = ServiceAgreementStatus.ACTIVE,
            )),
            published = LocalDateTime.MIN,
            expired = LocalDateTime.MAX,
            created = LocalDateTime.MIN,
            updated = LocalDateTime.MAX,
            createdByUser = "system",
            updatedByUser = "system"
        )
        // Serialize the object to a file
        FileOutputStream("./src/test/resources/latest/ServiceJob.json").use {
            it.write(objectMapper().writerWithDefaultPrettyPrinter().writeValueAsBytes(serviceJob))
        }

        // Deserialize old version if it works with this version
        val oldServiceJob = objectMapper().readValue(
            this::class.java.getResourceAsStream("/old/ServiceJob.json"),
            ServiceJobRapidDTO::class.java
        )
        oldServiceJob.attributes.text shouldBe "We provide repair services for wheelchairs."
        oldServiceJob.id shouldBe uuid
    }

}