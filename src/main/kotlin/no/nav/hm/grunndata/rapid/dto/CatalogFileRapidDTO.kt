package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.UUID

data class CatalogFileRapidDTO(
    override val id: UUID,
    val fileName: String,
    val fileSize: Long,
    val orderRef: String,
    override val partitionKey: String = orderRef,
    val supplierId: UUID,
    val updatedByUser: String,
    val created: LocalDateTime,
    val updated: LocalDateTime,
    val status: CatalogFileStatus,
): RapidDTO

enum class CatalogFileStatus {
    PENDING, DONE, ERROR
}