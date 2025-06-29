package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

class ProductAgreementRegistrationRapidDTO(
    override val id: UUID,
    override val partitionKey: String = id.toString(),
    val productId: UUID?=null,
    val title: String,
    val articleName: String="",
    val supplierId: UUID,
    val supplierRef: String,
    val hmsArtNr: String?,
    val agreementId: UUID,
    val reference: String,
    val post: Int,
    val postId: UUID,
    val rank: Int,
    val status: ProductAgreementStatus,
    val createdBy: String,
    val updatedBy: String = createdBy,
    val created: LocalDateTime,
    val updated: LocalDateTime,
    val published: LocalDateTime,
    val expired: LocalDateTime,
    val accessory: Boolean = false,
    val sparePart: Boolean =false,
    val mainProduct: Boolean = true,
): RapidDTO

enum class ProductAgreementStatus {
    ACTIVE,
    INACTIVE,
    DELETED
}