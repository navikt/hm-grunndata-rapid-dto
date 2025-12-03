package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.UUID

data class ServiceJobRapidDTO(
    override val id: UUID,
    override val partitionKey: String = id.toString(),
    val title: String,
    val supplierId: UUID,
    val supplierRef: String?,
    val hmsNr: String,
    val isoCategory: String,
    val published: LocalDateTime,
    val expired: LocalDateTime,
    val updated: LocalDateTime = LocalDateTime.now(),
    val draftStatus: DraftStatus = DraftStatus.DRAFT,
    val status: ServiceStatus = ServiceStatus.ACTIVE,
    val created: LocalDateTime = LocalDateTime.now(),
    val updatedBy: String,
    val createdBy: String,
    val createdByUser: String,
    val updatedByUser: String,
    val attributes: ServiceAttributes,
    val agreements: List<ServiceAgreementInfo> = emptyList(),
    val version: Long? = 0L,
): RapidDTO

enum class ServiceStatus {
    ACTIVE, INACTIVE, DELETED
}

data class ServiceAttributes(
    val keywords: Set<String>? = null,
    val url: String? = null,
    val text: String? = null,
    val serviceFor: ServiceFor? = null,
)

data class ServiceFor(
    val seriesIds: Set<UUID> = emptySet(), val productIds: Set<UUID> = emptySet()
)

data class ServiceAgreementInfo(
    val serviceId: UUID,
    val supplierId: UUID,
    val supplierRef: String? = null,
    val agreementId: UUID,
    val status: ServiceAgreementStatus = ServiceAgreementStatus.INACTIVE,
    val published: LocalDateTime,
    val expired: LocalDateTime,
)

enum class ServiceAgreementStatus {
    INACTIVE, ACTIVE, DELETED
}

