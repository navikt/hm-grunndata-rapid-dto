package no.nav.hm.grunndata.rapid.event

/*
    Events that floats in the rapid, this make it easier to know what to listen for
    Eventname always start with app name (the source) for the event.
 */
class EventName {

    companion object {
        const val hmdbagreementsync = "${RapidApp.grunndata_db}-hmdb-agreement-sync" // send by db when sync from HMDB
        const val hmdbsuppliersync = "${RapidApp.grunndata_db}-hmdb-supplier-sync" // send by db when sync from HMDB
        const val hmdbproductsync = "${RapidApp.grunndata_db}-hmdb-product-sync" // send by db when sync from HMDB
        const val syncedRegisterProduct  = "${RapidApp.grunndata_db}-register-product-synced" // send by GDB when synced with register
        const val registeredProduct = "${RapidApp.grunndata_register}-registered-product" // send by REGISTER when product change in register
        const val importedProduct = "${RapidApp.grunndata_import}-imported-product" // send by IMPORT when product got imported
    }

}
