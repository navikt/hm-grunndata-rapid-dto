package no.nav.hm.grunndata.rapid.dto


import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import java.time.LocalDateTime
import java.util.*

data class ProductRapidDTO(
    override val id: UUID,
    val supplier: SupplierDTO,
    val title: String,
    val articleName: String,
    val attributes: Attributes,
    val status: ProductStatus = ProductStatus.ACTIVE,
    val hmsArtNr: String?=null,
    val identifier: String,
    val supplierRef: String,
    val isoCategory: String,
    val accessory: Boolean = false,
    val sparePart: Boolean = false,
    val seriesId: String? = id.toString(),
    val techData: List<TechData> = emptyList(),
    val media: List<MediaInfo> = emptyList(),
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val published: LocalDateTime = LocalDateTime.now(),
    val expired: LocalDateTime = updated.plusYears(20),
    val agreementInfo: AgreementInfo?=null,
    val agreements: List<AgreementInfo> = emptyList(),
    val hasAgreement: Boolean = (agreementInfo!=null || agreements.isNotEmpty()),
    val createdBy: String,
    val updatedBy: String
) : RapidDTO

data class TechData (
    val key:    String,
    val value:  String,
    val unit:   String
)


data class AgreementInfo (
    val id: UUID,
    val identifier: String?=null,
    val title: String?=null,
    val rank: Int,
    val postNr: Int,
    val postIdentifier: String?=null,
    val postTitle: String?=null,
    val reference: String,
    val expired: LocalDateTime,
)

enum class ProductStatus {
    ACTIVE,
    @JsonEnumDefaultValue
    INACTIVE,
    DELETED
}

enum class MediaSourceType {
    HMDB,
    REGISTER,
    EXTERNALURL,
    @JsonEnumDefaultValue
    UNKNOWN
}

enum class MediaType {
    PDF,
    IMAGE,
    VIDEO,
    XLS,
    @JsonEnumDefaultValue
    OTHER
}

data class Attributes(val manufacturer: String? = null,
                      @Deprecated("use articleName in dto")
                      val articlename: String? = null,
                      val compatible: List<CompatibleAttribute>? = null,
                      val keywords: List<String>? = null,
                      val series: String? = null,
                      val shortdescription: String? = null,
                      val text: String? = null,
                      val url: String? = null,
                      val bestillingsordning: Boolean? = null,
                      val tenderId: String? = null,
                      val hasTender: Boolean? = null,
)


data class CompatibleAttribute(val id: UUID?=null,
                               val supplierRef: String?=null,
                               val hmsArtNr: String?)
