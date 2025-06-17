package no.nav.hn.grunndata.rapid.dto

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import no.nav.hm.grunndata.rapid.dto.ProductAgreementRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.ProductAgreementStatus
import org.junit.jupiter.api.Test
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.util.*

class ProductAgreementRegistrationDTOTest {
    @Test
    fun productAgreementRegistrationTest() {
        val uuid = UUID.fromString("a03629d9-0c33-48b8-b997-fe940a153718")
        val postId = UUID.fromString("37d81fa9-8407-46b8-938c-e9b1408fbe10")
        val productAgreementRapidDTO = ProductAgreementRegistrationRapidDTO(
            id = uuid,
            productId = uuid,
            title = "beskrivelse av produktet",
            supplierId = uuid,
            supplierRef = "eksternref-1",
            hmsArtNr = "111",
            agreementId = uuid,
            reference = "AV-142",
            post = 1,
            rank = 1,
            status = ProductAgreementStatus.ACTIVE,
            createdBy = "REGISTER",
            created = LocalDateTime.MAX,
            updated = LocalDateTime.MAX,
            published = LocalDateTime.MAX,
            expired = LocalDateTime.MAX,
            postId = postId
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
        oldProductAgreementRegistration.shouldNotBeNull()
        oldProductAgreementRegistration.post shouldBe 1
    }
}