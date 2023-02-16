package no.nav.hn.grunndata.dto

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import no.nav.hm.grunndata.dto.SupplierDTO
import no.nav.hm.grunndata.dto.SupplierInfo
import org.junit.jupiter.api.Test
import java.util.*


class RapidDTOTest {

    @Test
    fun testRapidDTO() {
        val supplier = SupplierDTO(id = UUID.randomUUID(), identifier = "test-123", createdBy = "test", updatedBy = "test",
            name = "testsupplier", info = SupplierInfo())
        supplier.dtoVersion.shouldNotBeNull()
        supplier.javaClass.simpleName shouldBe "SupplierDTO"
    }

}