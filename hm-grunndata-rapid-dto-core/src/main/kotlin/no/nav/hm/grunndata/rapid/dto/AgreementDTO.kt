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
    val agreementKey: String = "",
    val published: LocalDateTime = LocalDateTime.now(),
    val expired: LocalDateTime = LocalDateTime.now(),
    val attachments: List<AgreementAttachment> = emptyList(),
    val posts: List<AgreementPost> = emptyList(),
    val isoCategory: List<String> = emptyList(),
    val createdBy: String,
    val updatedBy: String,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val previousAgreement: UUID? = null
) : RapidDTO

data class AgreementPost(
    val identifier: String,
    val id: UUID? = UUID.randomUUID(),
    val type: DelkontraktType = DelkontraktType.WITH_DELKONTRAKT,
    val nr: Int,
    val refNr: String? = null,
    val title: String,
    val description: String,
    val created: LocalDateTime = LocalDateTime.now()
)

enum class DelkontraktType {
    WITH_DELKONTRAKT, WITH_NO_DELKONTRAKT
}

data class AgreementAttachment(
    val id: UUID?,
    val title: String?,
    val media: List<MediaInfo> = emptyList(),
    val description: String?
)

enum class AgreementStatus {
    ACTIVE, INACTIVE, DELETED
}
