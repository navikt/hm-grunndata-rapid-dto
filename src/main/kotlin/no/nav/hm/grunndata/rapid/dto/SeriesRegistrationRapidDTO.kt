package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.UUID

data class SeriesRegistrationRapidDTO(
    override val id: UUID,
    val draftStatus: DraftStatus = DraftStatus.DRAFT,
    val supplierId: UUID,
    val identifier: String,
    val title: String,
    val text: String,
    val formattedText: String? = null,
    val isoCategory: String,
    val status: SeriesStatus = SeriesStatus.ACTIVE,
    val adminStatus: AdminStatus = AdminStatus.APPROVED,
    val seriesData: SeriesData,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val expired: LocalDateTime = LocalDateTime.now().plusYears(15),
    val published: LocalDateTime = LocalDateTime.now(),
    val createdBy: String,
    val updatedBy: String,
    val updatedByUser: String,
    val createdByUser: String,
    val createdByAdmin: Boolean = false,
    val count: Int = 0,
    val version: Long? = 0L,
    val mainProduct: Boolean? = null
) : RapidDTO

data class SeriesData(
    val media: Set<MediaInfo> = emptySet(),
    val attributes: SeriesAttributes = SeriesAttributes(),
)

data class SeriesAttributes(
    val keywords: Set<String>? = null,
    val url: String? = null,
    val documentUrls: List<String>? = null,
    val compatibleWith: CompatibleWith? = null
)