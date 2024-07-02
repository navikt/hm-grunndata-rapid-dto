package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class ProdukttypeRegistrationRapidDTO(
    override val id: UUID,
    val isokode: String,
    val produkttype: String,
    val status: ProdukttypeStatus = ProdukttypeStatus.ACTIVE,
    val updatedByUser: String = "system",
    val createdByUser: String = "system",
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val deactivated: LocalDateTime? = null
): RapidDTO

enum class ProdukttypeStatus {
    ACTIVE, INACTIVE
}
