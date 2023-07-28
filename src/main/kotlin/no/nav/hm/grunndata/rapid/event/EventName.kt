package no.nav.hm.grunndata.rapid.event

/*
    Events that floats in the rapid, this make it easier to know what to listen for
    Eventname always start with app name (the source) for the event.
 */
class EventName {

    companion object {
        const val hmdbagreementsyncV1 = "${RapidApp.grunndata_db}-hmdb-agreement-sync-v1" // send by GDB when sync from HMDB
        const val hmdbsuppliersyncV1 = "${RapidApp.grunndata_db}-hmdb-supplier-sync-v1" // send by GDB when sync from HMDB
        const val hmdbproductsyncV1 = "${RapidApp.grunndata_db}-hmdb-product-sync-v1" // send by GDB when sync from HMDB
        const val syncedRegisterProductV1  = "${RapidApp.grunndata_db}-register-product-synced-v1" // send by GDB when synced product with register
        const val syncedRegisterAgreementV1  = "${RapidApp.grunndata_db}-register-agreement-synced-v1" // send by GDB when synced agreement with register
        const val syncedRegisterSupplierV1 = "${RapidApp.grunndata_db}-register-supplier-synced-v1" // send by GDB when synced supplier with register
        const val registeredProductV1 = "${RapidApp.grunndata_register}-product-registered-v1" // send by REGISTER when product change in register
        const val registeredAgreementV1 = "${RapidApp.grunndata_register}-agreement-registered-v1" // send by REGISTER when agreement change in register
        const val registeredSupplierV1 = "${RapidApp.grunndata_register}-supplier-registered-v1" // send by REGISTER when supplier change in register
        const val importedProductV1 = "${RapidApp.grunndata_import}-product-imported-v1" // send by IMPORT when product got imported
        const val expiredAgreementV1 = "${RapidApp.grunndata_db}-agreement-expired-v1" // send by GDB when agreement has expired
        const val expiredProductAgreementV1 = "${RapidApp.grunndata_db}-product-agreement-expired-v1" // // send by GDB when product has an agreement that has expired
    }

}
