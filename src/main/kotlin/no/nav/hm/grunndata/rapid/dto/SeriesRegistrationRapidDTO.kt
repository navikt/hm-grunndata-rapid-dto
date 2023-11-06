package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class SeriesRegistrationRapidDTO(
    override val id: UUID,
    val draftStatus: DraftStatus = DraftStatus.DRAFT,
    val supplierId:UUID,
    val identifier: String,
    val title: String,
    val text: String,
    val status: SeriesStatus = SeriesStatus.ACTIVE,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val expired: LocalDateTime = LocalDateTime.now().plusYears(15),
    val createdBy: String,
    val updatedBy: String,
    val updatedByUser: String,
    val createdByUser: String,
    val createdByAdmin: Boolean = false,
    val version: Long? = 0L
): RapidDTO