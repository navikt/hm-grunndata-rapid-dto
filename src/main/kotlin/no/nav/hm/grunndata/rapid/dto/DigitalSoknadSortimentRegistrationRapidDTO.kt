package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class DigitalSoknadSortimentRegistrationRapidDTO(
    override val id: UUID,
    val sortimentKategori: String,
    val postId: UUID,
    val status: DigitalSoknadSortimentStatus = DigitalSoknadSortimentStatus.ACTIVE,
    val updatedByUser: String = "system",
    val createdByUser: String = "system",
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val deactivated: LocalDateTime? = null
): RapidDTO

enum class DigitalSoknadSortimentStatus {
    ACTIVE, INACTIVE
}
