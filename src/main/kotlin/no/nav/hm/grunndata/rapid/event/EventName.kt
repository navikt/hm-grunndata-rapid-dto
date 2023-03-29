package no.nav.hm.grunndata.rapid.event

/*
    Events that floats in the rapid, this make it easier to know what to listen for
    Eventname always start with app name (the source) for the event.
 */
class EventName {

    companion object {
        const val hmdbagreementsyncV1 = "${RapidApp.grunndata_db}-hmdb-agreement-sync-v1" // send by db when sync from HMDB
        const val hmdbsuppliersyncV1 = "${RapidApp.grunndata_db}-hmdb-supplier-sync-v1" // send by db when sync from HMDB
        const val hmdbproductsyncV1 = "${RapidApp.grunndata_db}-hmdb-product-sync-v1" // send by db when sync from HMDB
        const val syncedRegisterProductV1  = "${RapidApp.grunndata_db}-register-product-synced-v1" // send by GDB when synced with register
        const val registeredProductV1 = "${RapidApp.grunndata_register}-registered-product-v1" // send by REGISTER when product change in register
        const val importedProductV1 = "${RapidApp.grunndata_import}-imported-product-v1" // send by IMPORT when product got imported
    }

}
