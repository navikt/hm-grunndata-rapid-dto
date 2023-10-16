package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class SeriesRapidDTO (
    override val id: UUID = UUID.randomUUID(),
    val status: SeriesStatus = SeriesStatus.ACTIVE,
    val title: String,
    val supplierId: UUID,
    val identifier: String,
    val createdBy: String,
    val updatedBy: String,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val expired: LocalDateTime = created.plusYears(15)
) : RapidDTO


enum class SeriesStatus {
    ACTIVE,
    INACTIVE,
    PENDING,
    REJECTED
}