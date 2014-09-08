package s12gx

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(GeocoderService)
class GeocoderServiceSpec extends Specification {

    void "Dallas, TX latitude, longitude close enough"() {
        given:
        double dallasLat = 32.7758d
        double dallasLng = -96.7967d
        Castle dallas = new Castle(city: 'Dallas', state: 'TX')

        when:
        service.fillInLatLng(dallas)

        then:
        (dallas.latitude  - dallasLat).abs() < 0.005
        (dallas.longitude - dallasLng).abs() < 0.005
    }
}
