package no.nav.hm.grunndata.rapid.dto


import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import java.time.LocalDateTime
import java.util.*

data class ProductDTO(
    override val id: UUID,
    val supplier: SupplierDTO,
    val title: String,
    val articleName: String,
    val attributes: Map<AttributeNames, Any>,
    val status: ProductStatus = ProductStatus.ACTIVE,
    val hmsArtNr: String?=null,
    val identifier: String,
    val supplierRef: String,
    val isoCategory: String,
    val accessory: Boolean = false,
    val sparePart: Boolean = false,
    val seriesId: String? = id.toString(),
    val techData: List<TechData> = emptyList(),
    val media: List<MediaDTO> = emptyList(),
    val created: LocalDateTime = LocalDateTime.now(),
    val updated: LocalDateTime = LocalDateTime.now(),
    val published: LocalDateTime = LocalDateTime.now(),
    val expired: LocalDateTime = updated.plusYears(20),
    val agreementInfo: AgreementInfo?=null,
    val hasAgreement: Boolean = (agreementInfo!=null),
    val createdBy: String,
    val updatedBy: String
) : RapidDTO

data class TechData (
    val key:    String,
    val value:  String,
    val unit:   String
)


data class AgreementInfo (
    val id: UUID ?= null,
    val identifier: String?=null,
    val rank: Int,
    val postNr: Int,
    val postIdentifier: String?=null,
    val reference: String,
    val expired: LocalDateTime?=null,
)

enum class ProductStatus {
    ACTIVE, INACTIVE
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

enum class AttributeNames(private val type: AttributeType) {

    manufacturer(AttributeType.STRING),
    @Deprecated("moved to articleName")
    articlename(AttributeType.STRING),
    compatible(AttributeType.LIST),
    series(AttributeType.STRING),
    keywords(AttributeType.LIST),
    shortdescription(AttributeType.HTML),
    text(AttributeType.HTML),
    url(AttributeType.URL),
    tags(AttributeType.LIST),
    bestillingsordning(AttributeType.BOOLEAN)

}

enum class AttributeType {
    STRING, HTML, URL, LIST, JSON, BOOLEAN
}
