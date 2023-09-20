package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class SeriesImportRapidDTO(
    override val id: UUID,
    val identifier: String,
    val supplierId: UUID,
    val transferId: UUID,
    val name: String,
    val message: String?=null,
    val status: SeriesStatus = SeriesStatus.ACTIVE,
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