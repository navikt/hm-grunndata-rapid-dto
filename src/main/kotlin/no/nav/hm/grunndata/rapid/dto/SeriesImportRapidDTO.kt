package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class SeriesImportRapidDTO(
    override val id: UUID,
    val seriesDTO: SeriesRapidDTO,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val version: Long=0L
): RapidDTO
