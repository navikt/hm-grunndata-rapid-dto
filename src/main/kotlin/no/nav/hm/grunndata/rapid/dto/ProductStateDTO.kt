package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class ProductStateDTO(
    override val id: UUID,
    val transferId: UUID,
    val supplierId: UUID,
    val supplierRef: String,
    val productDTO: ProductDTO,
    val adminStatus: AdminStatus?=null,
    val message: String? = null,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now()
): RapidDTO
