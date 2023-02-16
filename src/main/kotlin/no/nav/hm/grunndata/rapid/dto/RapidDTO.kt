package no.nav.hm.grunndata.rapid.dto

import java.util.*

interface RapidDTO {
    val id: UUID
    val dtoVersion: String
}

val rapidDTOVersion = RapidDTO::class.java.classLoader.getResource("version.properties")!!
.readText()
.substringAfter("=")