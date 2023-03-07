package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class AgreementDTO(
    override val id: UUID,
    val identifier: String,
    val title: String,
    val resume: String?,
    val text: String?,
    val reference: String,
    val published: LocalDateTime = LocalDateTime.now(),
    val expired: LocalDateTime = LocalDateTime.now(),
    val attachments: List<AgreementAttachment> = emptyList(),
    val posts: List<AgreementPost> = emptyList(),
    val createdBy:String,
    val updatedBy: String,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now()
): RapidDTO

data class AgreementPost (
    val identifier: String,
    val nr: Int,
    val title: String,
    val description: String,
    val created: LocalDateTime = LocalDateTime.now()
)

data class AgreementAttachment (
    val title: String?,
    val media: List<MediaDTO> = emptyList(),
    val description: String?,
)
