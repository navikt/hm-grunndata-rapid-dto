package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime

data class IsoTranslationsDTO(
    val titleEn: String?=null,
    val textEn: String?=null,
)

data class IsoCategoryDTO(
    val isoCode: String,
    val isoTitle: String,
    val isoText: String,
    val isoTextShort: String?=null,
    val isoTranslations: IsoTranslationsDTO?=null,
    val isoLevel: Int,
    val isActive: Boolean = true,
    val showTech: Boolean = true,
    val allowMulti: Boolean = true,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now()
)
