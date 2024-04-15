package no.nav.hn.grunndata.rapid.dto

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import no.nav.hm.grunndata.rapid.dto.ProductAgreementRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.ProductAgreementStatus
import no.nav.hm.grunndata.rapid.dto.RapidDTO
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


    @Test
    fun testRapidDTO() {
        rapidDTOVersion.shouldNotBeNull()
        supplier.javaClass.simpleName shouldBe "SupplierDTO"
    }
}
