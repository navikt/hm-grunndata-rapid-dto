package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class SeriesImportRapidDTO(
    override val id: UUID,
    val transferId: UUID,
    val supplierSeriesRef: String,
    val seriesDTO: SeriesRapidDTO,
    val adminStatus: AdminStatus,
    val adminMessage: String?=null,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val version: Long=0L
): RapidDTO

enum class SeriesStatus {
    ACTIVE,
    INACTIVE,
    PENDING,
    REJECTED
}