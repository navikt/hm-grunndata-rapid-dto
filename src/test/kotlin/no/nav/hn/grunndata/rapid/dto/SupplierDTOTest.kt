package no.nav.hn.grunndata.rapid.dto

import no.nav.hm.grunndata.rapid.dto.SupplierDTO
import no.nav.hm.grunndata.rapid.dto.SupplierInfo
import java.util.*

class SupplierDTOTest {
}

val supplier = SupplierDTO(
    id = UUID.randomUUID(), identifier = "test-123", createdBy = "test", updatedBy = "test",
    name = "testsupplier", info = SupplierInfo()
)