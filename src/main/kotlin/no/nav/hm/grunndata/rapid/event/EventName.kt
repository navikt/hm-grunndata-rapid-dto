package no.nav.hm.grunndata.rapid.event

class EventName {

    companion object {
        const val hmdbagreementsync = "${RapidApp.grunndata_db}-hmdb-agreement-sync" // send when sync from HMDB
        const val hmdbsuppliersync = "${RapidApp.grunndata_db}-hmdb-supplier-sync"
        const val hmdbproductsync = "${RapidApp.grunndata_db}-hmdb-product-sync"
        const val productRegistration = "${RapidApp.grunndata_register}-product-registration" //send when product change in register
        const val registerProductSync = "${RapidApp.grunndata_db}-product-registration-sync" // send from GDB when synced with register
    }

}