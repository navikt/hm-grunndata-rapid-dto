package no.nav.hm.grunndata.rapid.version

import no.nav.hm.grunndata.rapid.dto.RapidDTO


val rapidDTOVersion = RapidDTO::class.java.classLoader.getResource("version.properties")!!
    .readText()
    .substringAfter("=").trim().toLong()