package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class NewsRegistrationRapidDTO(
    override val id: UUID,
    val title: String,
    val text: String,
    val status: NewsStatus,
    val draftStatus: DraftStatus,
    val published: LocalDateTime,
    val expired: LocalDateTime,
    val created: LocalDateTime,
    val updated: LocalDateTime,
    val author: String,
    val createdByUser: String,
    val updatedByUser: String
): RapidDTO

data class NewsDTO(
    override val id: UUID,
    val identifier: String,
    val title: String,
    val text: String,
    val status: NewsStatus,
    val published: LocalDateTime,
    val expired: LocalDateTime,
    val created: LocalDateTime,
    val updated: LocalDateTime,
    val createdBy: String,
    val updatedBy: String,
    val author: String,
): RapidDTO


enum class NewsStatus {
    ACTIVE, INACTIVE, DELETED
}
