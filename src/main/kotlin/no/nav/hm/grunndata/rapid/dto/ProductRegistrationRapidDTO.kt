package no.nav.hm.grunndata.rapid.dto

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import java.time.LocalDateTime
import java.util.*

data class ProductRegistrationRapidDTO (
    override val id: UUID,
    val draftStatus: DraftStatus = DraftStatus.DRAFT,
    val adminStatus: AdminStatus = AdminStatus.PENDING,
    val registrationStatus: RegistrationStatus = RegistrationStatus.ACTIVE,
    val message: String?,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val published: LocalDateTime?,
    val expired: LocalDateTime?,
    val createdBy: String,
    val updatedBy: String,
    val createdByAdmin: Boolean = false,
    val productDTO: ProductRapidDTO,
    val version: Long?=null
) : RapidDTO

enum class RegistrationStatus {
    ACTIVE, INACTIVE, DELETED
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

