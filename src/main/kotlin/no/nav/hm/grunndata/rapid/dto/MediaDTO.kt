package no.nav.hm.grunndata.rapid.dto

import java.util.*


data class MediaDTO (
    val oid: UUID = UUID.randomUUID(),
    val sourceUri: String,
    val uri:    String,
    val priority: Int = 1,
    val type: MediaType = MediaType.IMAGE,
    val text:   String?=null,
    val source: MediaSourceType = MediaSourceType.HMDB
)

data class MediaInfo (
    val sourceUri: String,
    val uri:    String,
    val priority: Int = 1,
    val type: MediaType = MediaType.IMAGE,
    val text:   String?=null,
    val source: MediaSourceType = MediaSourceType.HMDB
)
