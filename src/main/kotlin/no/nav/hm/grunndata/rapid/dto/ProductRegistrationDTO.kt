package no.nav.hm.grunndata.rapid.dto

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import java.time.LocalDateTime
import java.util.*

data class ProductRegistrationDTO (
    override val id: UUID,
    val draftStatus: DraftStatus = DraftStatus.DRAFT,
    val adminStatus: AdminStatus = AdminStatus.PENDING,
    val registrationStatus: RegistrationStatus = RegistrationStatus.ACTIVE,
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
    @JsonEnumDefaultValue
    PENDING,
    APPROVED,
    REJECTED
}

enum class DraftStatus {
    DRAFT, DONE
}

data class AdminInfo(val approvedBy: String?, val note: String?=null)
