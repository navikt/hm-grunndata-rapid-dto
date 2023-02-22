package no.nav.hm.grunndata.rapid.dto

import java.util.*


data class MediaDTO (
    @Deprecated("uri will be used as id")
    val id: UUID = UUID.randomUUID(),
    @Deprecated("use priority instead") val order:  Int = 1,
    val priority: Int = 1,
    val type: MediaType = MediaType.IMAGE,
    val uri:    String,
    val text:   String?=null,
    val source: MediaSourceType = MediaSourceType.HMDB
)
