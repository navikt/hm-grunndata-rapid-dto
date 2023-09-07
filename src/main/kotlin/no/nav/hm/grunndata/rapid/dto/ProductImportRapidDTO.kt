package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class ProductImportRapidDTO (
    override val id: UUID,
    val transferId: UUID,
    val supplierId: UUID,
    val supplierRef: String,
    val version: Long,
    val productDTO: ProductRapidDTO,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now()
): RapidDTO