package no.nav.hm.grunndata.rapid.dto

import java.util.*

interface RapidDTO {
    val id: UUID
    val partitionKey: String get() = id.toString()
}

val rapidDTOVersion = RapidDTO::class.java.classLoader.getResource("version.properties")!!
.readText()
.substringAfter("=").trim().toLong()
