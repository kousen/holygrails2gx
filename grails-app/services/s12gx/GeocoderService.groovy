package s12gx

import grails.transaction.Transactional

@Transactional
class GeocoderService {

    void fillInLatLng(Castle c) {
        String BASE = 'https://maps.googleapis.com/maps/api/geocode/xml?'
        String encoded = [c.city, c.state].collect {
            URLEncoder.encode(it, 'UTF-8')
        }.join(',')
        String url = "${BASE}address=$encoded"
        def root = new XmlSlurper().parse(url)
        def loc = root.result[0].geometry.location
        c.latitude  = loc.lat.toDouble()
        c.longitude = loc.lng.toDouble()
    }

    def headers() {
        [['number','Lat'],['number','Lng'],['string','Name']]
    }

    def data() {
        Castle.list().collect { c ->
            [c.latitude, c.longitude, c.toString()]
        }
    }
}
