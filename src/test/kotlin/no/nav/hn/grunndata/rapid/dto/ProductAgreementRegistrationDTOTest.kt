package no.nav.hn.grunndata.rapid.dto

import no.nav.hm.grunndata.rapid.dto.ProductAgreementRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.ProductAgreementStatus
import org.junit.jupiter.api.Test
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.util.*

class ProductAgreementRegistrationDTOTest {

    @Test
    fun productAgreementRegistrationTest() {
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

        //Serialize the object to a file
        FileOutputStream("./src/test/resources/latest/ProductAgreementRegistration.json").use {
            it.write(objectMapper().writerWithDefaultPrettyPrinter().writeValueAsBytes(productAgreementRapidDTO))
        }

        //Deserialize old version if it works with this version
        val oldProductAgreementRegistration = objectMapper().readValue(
            this::class.java.getResourceAsStream("/old/ProductAgreementRegistration.json"),
            ProductAgreementRegistrationRapidDTO::class.java
        )
    }
}