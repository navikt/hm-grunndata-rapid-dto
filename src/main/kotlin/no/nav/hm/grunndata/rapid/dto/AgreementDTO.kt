package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.UUID


data class AgreementDTO(
    override val id: UUID,
    val identifier: String,
    val title: String,
    val resume: String?,
    val text: String?,
    val status: AgreementStatus = AgreementStatus.ACTIVE,
    val reference: String,
    val published: LocalDateTime = LocalDateTime.now(),
    val expired: LocalDateTime = LocalDateTime.now(),
    val attachments: List<AgreementAttachment> = emptyList(),
    val posts: List<AgreementPost> = emptyList(),
    val isoCategory: List<String> = emptyList(),
    val createdBy: String,
    val updatedBy: String,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now()
) : RapidDTO

data class AgreementPost(
    val identifier: String,
    val nr: Int,
    val title: String,
    val description: String,
    val created: LocalDateTime = LocalDateTime.now()
)

data class AgreementAttachment(
    val id: UUID = UUID.randomUUID(),
    val title: String?,
    val media: List<MediaInfo> = emptyList(),
    val description: String?
)

enum class AgreementStatus {
    ACTIVE, INACTIVE, DELETED
}
