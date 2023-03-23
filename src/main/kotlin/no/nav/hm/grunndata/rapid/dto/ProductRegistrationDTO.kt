package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class ProductRegistrationDTO(
    override val id: UUID,
    val supplierId: UUID,
    val supplierRef: String,
    val hmsArtNr: String?,
    val title: String,
    val articleName: String,
    val draftStatus: DraftStatus = DraftStatus.DRAFT,
    val adminStatus: AdminStatus = AdminStatus.NOT_APPROVED,
    val status: RegistrationStatus = RegistrationStatus.ACTIVE,
    val message: String?,
    val adminInfo: AdminInfo?=null,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val published: LocalDateTime?,
    val expired: LocalDateTime?,
    val updatedByUser: String="system",
    val createdByUser: String="system",
    val createdBy: String,
    val updatedBy: String,
    val createdByAdmin: Boolean = false,
    val productDTO: ProductDTO,
    val version: Long?=null
) : RapidDTO

enum class RegistrationStatus {
    ACTIVE, DELETED
}

enum class AdminStatus {
    NOT_APPROVED, APPROVED
}

enum class DraftStatus {
    DRAFT, DONE
}

data class AdminInfo(val approvedBy: String?, val note: String?=null)
