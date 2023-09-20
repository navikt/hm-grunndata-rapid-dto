package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class SeriesRapidDTO (
    override val id: UUID = UUID.randomUUID(),
    val status: SeriesStatus = SeriesStatus.ACTIVE,
    val name: String,
    val supplierId: UUID,
    val identifier: String,
    val createdBy: String,
    val updatedBy: String,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now()
) : RapidDTO