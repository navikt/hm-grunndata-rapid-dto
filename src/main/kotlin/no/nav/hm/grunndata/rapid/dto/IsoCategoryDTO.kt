package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime

data class IsoTranslations(
    val titleEn: String?=null,
    val textLongEn: String?=null,
    val textShortEn: String?=null
)

data class IsoCategoryDTO(
    val isoCode: String,
    val isoTitle: String,
    val isoText: String,
    val isoTextShort: String,
    val isoTextLong: String,
    val isoTranslations: IsoTranslations = IsoTranslations(),
    val isoLevel: Int,
    val isActive: Boolean = true,
    val showTech: Boolean = true,
    val allowMulti: Boolean = true,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now()
)
