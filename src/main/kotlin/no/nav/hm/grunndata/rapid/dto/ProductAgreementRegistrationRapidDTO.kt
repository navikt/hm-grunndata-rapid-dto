package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

class ProductAgreementRegistrationRapidDTO(
    override val id: UUID,
    val productId: UUID?=null,
    val title: String,
    val supplierId: UUID,
    val supplierRef: String,
    val hmsArtNr: String?,
    val agreementId: UUID,
    val reference: String,
    val post: Int,
    val postId: UUID?=null,
    val rank: Int,
    val status: ProductAgreementStatus,
    val createdBy: String,
    val updatedBy: String = createdBy,
    val created: LocalDateTime,
    val updated: LocalDateTime,
    val published: LocalDateTime,
    val expired: LocalDateTime
): RapidDTO

enum class ProductAgreementStatus {
    ACTIVE,
    INACTIVE,
    DELETED
}