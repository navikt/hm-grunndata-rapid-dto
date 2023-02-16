package no.nav.hm.grunndata.dto

interface RapidDTO {
    val dtoVersion: String
}

val rapidDTOVersion = RapidDTO::class.java.classLoader.getResource("version.properties")!!
.readText()
.substringAfter("=")