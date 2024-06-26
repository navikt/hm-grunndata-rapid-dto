package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class SeriesImportRapidDTO(
    override val id: UUID,
    val supplierId: UUID,
    val transferId: UUID,
    val title: String,
    val text: String,
    val isoCategory: String,
    val status: SeriesStatus = SeriesStatus.ACTIVE,
    val seriesData: SeriesData = SeriesData(),
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val expired: LocalDateTime,
    val version: Long
): RapidDTO


