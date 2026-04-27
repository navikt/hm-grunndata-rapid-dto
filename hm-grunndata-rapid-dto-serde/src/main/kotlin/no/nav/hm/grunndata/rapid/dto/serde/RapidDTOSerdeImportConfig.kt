package no.nav.hm.grunndata.rapid.dto.serde

import io.micronaut.serde.annotation.SerdeImport
import no.nav.hm.grunndata.rapid.dto.AdminStatus
import no.nav.hm.grunndata.rapid.dto.AgreementAttachment
import no.nav.hm.grunndata.rapid.dto.AgreementDTO
import no.nav.hm.grunndata.rapid.dto.AgreementInfo
import no.nav.hm.grunndata.rapid.dto.AgreementPost
import no.nav.hm.grunndata.rapid.dto.AgreementRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.AgreementStatus
import no.nav.hm.grunndata.rapid.dto.AlternativeFor
import no.nav.hm.grunndata.rapid.dto.Attributes
import no.nav.hm.grunndata.rapid.dto.BestillingsordningRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.BestillingsordningStatus
import no.nav.hm.grunndata.rapid.dto.CatalogFileRapidDTO
import no.nav.hm.grunndata.rapid.dto.CatalogFileStatus
import no.nav.hm.grunndata.rapid.dto.CompatibleWith
import no.nav.hm.grunndata.rapid.dto.DelkontraktType
import no.nav.hm.grunndata.rapid.dto.DigitalSoknadSortimentRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.DigitalSoknadSortimentStatus
import no.nav.hm.grunndata.rapid.dto.DocumentUrl
import no.nav.hm.grunndata.rapid.dto.DraftStatus
import no.nav.hm.grunndata.rapid.dto.IsoCategoryDTO
import no.nav.hm.grunndata.rapid.dto.IsoTranslationsDTO
import no.nav.hm.grunndata.rapid.dto.MediaInfo
import no.nav.hm.grunndata.rapid.dto.MediaRapidDTO
import no.nav.hm.grunndata.rapid.dto.MediaSourceType
import no.nav.hm.grunndata.rapid.dto.MediaType
import no.nav.hm.grunndata.rapid.dto.NewsDTO
import no.nav.hm.grunndata.rapid.dto.NewsRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.NewsStatus
import no.nav.hm.grunndata.rapid.dto.PaakrevdGodkjenningskursRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.PaakrevdGodkjenningskursStatus
import no.nav.hm.grunndata.rapid.dto.PakrevdGodkjenningskurs
import no.nav.hm.grunndata.rapid.dto.ProductAgreementRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.ProductAgreementStatus
import no.nav.hm.grunndata.rapid.dto.ProductImportRapidDTO
import no.nav.hm.grunndata.rapid.dto.ProductRapidDTO
import no.nav.hm.grunndata.rapid.dto.ProductRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.ProductStateDTO
import no.nav.hm.grunndata.rapid.dto.ProductStatus
import no.nav.hm.grunndata.rapid.dto.Produkttype
import no.nav.hm.grunndata.rapid.dto.ProdukttypeRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.ProdukttypeStatus
import no.nav.hm.grunndata.rapid.dto.RapidDTO
import no.nav.hm.grunndata.rapid.dto.RegistrationStatus
import no.nav.hm.grunndata.rapid.dto.SeriesAttributes
import no.nav.hm.grunndata.rapid.dto.SeriesData
import no.nav.hm.grunndata.rapid.dto.SeriesImportRapidDTO
import no.nav.hm.grunndata.rapid.dto.SeriesRapidDTO
import no.nav.hm.grunndata.rapid.dto.SeriesRegistrationRapidDTO
import no.nav.hm.grunndata.rapid.dto.SeriesStatus
import no.nav.hm.grunndata.rapid.dto.ServiceAgreementInfo
import no.nav.hm.grunndata.rapid.dto.ServiceAgreementStatus
import no.nav.hm.grunndata.rapid.dto.ServiceAttributes
import no.nav.hm.grunndata.rapid.dto.ServiceFor
import no.nav.hm.grunndata.rapid.dto.ServiceJobRapidDTO
import no.nav.hm.grunndata.rapid.dto.ServiceStatus
import no.nav.hm.grunndata.rapid.dto.SupplierDTO
import no.nav.hm.grunndata.rapid.dto.SupplierInfo
import no.nav.hm.grunndata.rapid.dto.SupplierStatus
import no.nav.hm.grunndata.rapid.dto.TechData
import no.nav.hm.grunndata.rapid.dto.WorksWith

// the only way to do it now.
@SerdeImport(AdminStatus::class)
@SerdeImport(AgreementAttachment::class)
@SerdeImport(AgreementDTO::class)
@SerdeImport(AgreementInfo::class)
@SerdeImport(AgreementPost::class)
@SerdeImport(AgreementRegistrationRapidDTO::class)
@SerdeImport(AgreementStatus::class)
@SerdeImport(AlternativeFor::class)
@SerdeImport(Attributes::class)
@SerdeImport(BestillingsordningRegistrationRapidDTO::class)
@SerdeImport(BestillingsordningStatus::class)
@SerdeImport(CatalogFileRapidDTO::class)
@SerdeImport(CatalogFileStatus::class)
@SerdeImport(CompatibleWith::class)
@SerdeImport(DelkontraktType::class)
@SerdeImport(DigitalSoknadSortimentRegistrationRapidDTO::class)
@SerdeImport(DigitalSoknadSortimentStatus::class)
@SerdeImport(DocumentUrl::class)
@SerdeImport(DraftStatus::class)
@SerdeImport(IsoCategoryDTO::class)
@SerdeImport(IsoTranslationsDTO::class)
@SerdeImport(MediaInfo::class)
@SerdeImport(MediaRapidDTO::class)
@SerdeImport(MediaSourceType::class)
@SerdeImport(MediaType::class)
@SerdeImport(NewsDTO::class)
@SerdeImport(NewsRegistrationRapidDTO::class)
@SerdeImport(NewsStatus::class)
@SerdeImport(PaakrevdGodkjenningskursRegistrationRapidDTO::class)
@SerdeImport(PaakrevdGodkjenningskursStatus::class)
@SerdeImport(PakrevdGodkjenningskurs::class)
@SerdeImport(ProductAgreementRegistrationRapidDTO::class)
@SerdeImport(ProductAgreementStatus::class)
@SerdeImport(ProductImportRapidDTO::class)
@SerdeImport(ProductRapidDTO::class)
@SerdeImport(ProductRegistrationRapidDTO::class)
@SerdeImport(ProductStateDTO::class)
@SerdeImport(ProductStatus::class)
@SerdeImport(Produkttype::class)
@SerdeImport(ProdukttypeRegistrationRapidDTO::class)
@SerdeImport(ProdukttypeStatus::class)
@SerdeImport(RapidDTO::class)
@SerdeImport(RegistrationStatus::class)
@SerdeImport(SeriesAttributes::class)
@SerdeImport(SeriesData::class)
@SerdeImport(SeriesImportRapidDTO::class)
@SerdeImport(SeriesRapidDTO::class)
@SerdeImport(SeriesRegistrationRapidDTO::class)
@SerdeImport(SeriesStatus::class)
@SerdeImport(ServiceAgreementInfo::class)
@SerdeImport(ServiceAgreementStatus::class)
@SerdeImport(ServiceAttributes::class)
@SerdeImport(ServiceFor::class)
@SerdeImport(ServiceJobRapidDTO::class)
@SerdeImport(ServiceStatus::class)
@SerdeImport(SupplierDTO::class)
@SerdeImport(SupplierInfo::class)
@SerdeImport(SupplierStatus::class)
@SerdeImport(TechData::class)
@SerdeImport(WorksWith::class)
class RapidDTOSerdeImportConfig() {

}