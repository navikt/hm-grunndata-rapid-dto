package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class DigitalSoknadRegistrationRapidDTO(
    override val id: UUID,
    val hmsArtNr: String,
    val status: DigitalSoknadStatus = DigitalSoknadStatus.ACTIVE,
    val updatedByUser: String = "system",
    val createdByUser: String = "system",
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val deactivated: LocalDateTime? = null
): RapidDTO

enum class DigitalSoknadStatus {
    ACTIVE, INACTIVE
}
