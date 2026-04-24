package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class PaakrevdGodkjenningskursRegistrationRapidDTO(
    override val id: UUID,
    val isokode: String,
    val tittel: String,
    val kursId: Int,
    val status: PaakrevdGodkjenningskursStatus = PaakrevdGodkjenningskursStatus.ACTIVE,
    val updatedByUser: String = "system",
    val createdByUser: String = "system",
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val deactivated: LocalDateTime? = null
): RapidDTO

enum class PaakrevdGodkjenningskursStatus {
    ACTIVE, INACTIVE
}
