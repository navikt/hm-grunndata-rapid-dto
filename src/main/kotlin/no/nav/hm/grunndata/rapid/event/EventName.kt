package no.nav.hm.grunndata.rapid.event

class EventName {

    companion object {
        const val hmdbagreementsync = "${RapidApp.grunndata_db}-hmdb-agreement-sync"
        const val hmdbsuppliersync = "${RapidApp.grunndata_db}-hmdb-supplier-sync"
        const val hmdbproductsync = "${RapidApp.grunndata_db}-hmdb-product-sync"
    }

}