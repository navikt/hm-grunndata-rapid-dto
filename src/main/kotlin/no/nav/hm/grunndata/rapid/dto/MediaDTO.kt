package no.nav.hm.grunndata.rapid.dto

import java.util.*

data class MediaRapidDTO (
    override val id: UUID,
    val oid: UUID,
    val sourceUri: String,
    val uri:    String,
    val priority: Int = -1,
    val type: MediaType = MediaType.IMAGE,
    val text:   String?=null,
    val source: MediaSourceType = MediaSourceType.HMDB
):RapidDTO

data class MediaInfo (
    val sourceUri: String,
    val uri:    String,
    val priority: Int = -1,
    val type: MediaType = MediaType.IMAGE,
    val text:   String?=null,
    val source: MediaSourceType = MediaSourceType.HMDB
)
