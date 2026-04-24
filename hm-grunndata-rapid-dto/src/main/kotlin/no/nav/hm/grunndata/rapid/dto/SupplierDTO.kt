package no.nav.hm.grunndata.rapid.dto

import java.time.LocalDateTime
import java.util.*

data class SupplierDTO(
    override val id: UUID,
    val identifier: String,
    val status : SupplierStatus = SupplierStatus.ACTIVE,
    val name: String,
    val info: SupplierInfo,
    val createdBy: String,
    val updatedBy: String,
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now()
) : RapidDTO

data class SupplierInfo (
    val address: String?=null,
    val postNr: String?=null,
    val postLocation: String?=null,
    val countryCode: String?=null,
    val email: String?=null,
    val phone: String?=null,
    val homepage: String?=null
)

enum class SupplierStatus {
    INACTIVE, ACTIVE
}
