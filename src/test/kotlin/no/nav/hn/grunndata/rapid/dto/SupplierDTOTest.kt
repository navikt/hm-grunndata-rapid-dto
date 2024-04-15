package no.nav.hn.grunndata.rapid.dto

import no.nav.hm.grunndata.rapid.dto.SupplierDTO
import no.nav.hm.grunndata.rapid.dto.SupplierInfo
import java.time.LocalDateTime
import java.util.*

class SupplierDTOTest {
}

val supplier = SupplierDTO(
    id = UUID.fromString("0d527ba0-d71e-4df5-8b1c-62e6f297e51e"), identifier = "test-123", createdBy = "test",
    updatedBy = "test", name = "testsupplier", info = SupplierInfo(), created = LocalDateTime.MAX, updated = LocalDateTime.MAX
)