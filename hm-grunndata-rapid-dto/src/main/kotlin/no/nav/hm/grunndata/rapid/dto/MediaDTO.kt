package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*


// sent by hm-grunndata-media
data class MediaRapidDTO (
    override val id: UUID,
    val oid: UUID,
    val sourceUri: String,
    val uri:    String,
    val priority: Int = -1,
    val type: MediaType = MediaType.IMAGE,
    val text:   String?=null,
    val source: MediaSourceType = MediaSourceType.HMDB
):RapidDTO {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MediaRapidDTO) return false
        return uri == other.uri
    }

    override fun hashCode(): Int {
        return uri.hashCode()
    }
}

// used part of product dto
data class MediaInfo (
    val sourceUri: String,
    val filename: String?=null,
    val uri:    String,
    val priority: Int = -1,
    val type: MediaType = MediaType.IMAGE,
    val text:   String?=null,
    val source: MediaSourceType = MediaSourceType.HMDB,
    val updated: LocalDateTime = LocalDateTime.now(),
) {
    override fun hashCode(): Int {
        return uri.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MediaInfo) return false
        return uri == other.uri
    }

}
