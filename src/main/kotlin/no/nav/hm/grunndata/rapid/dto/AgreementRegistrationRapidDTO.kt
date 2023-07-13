package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class AgreementRegistrationRapidDTO(
    override val id: UUID,
    val draftStatus: DraftStatus = DraftStatus.DRAFT,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val published: LocalDateTime = LocalDateTime.now(),
    val expired: LocalDateTime = LocalDateTime.now(),
    val createdByUser: String,
    val updatedByUser: String,
    val createdBy: String,
    val updatedBy: String,
    val version: Long? = 0L,
    val agreementDTO: AgreementDTO,
) : RapidDTO

