package s12gx

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(GeocoderService)
@Mock(Castle)
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

    void 'headers() returns list of pairs'() {
        when:
        def listOfPairs = service.headers()

        then:
        listOfPairs.contains(['number','Lat'])
        listOfPairs.contains(['number','Lng'])
        listOfPairs.contains(['string','Name'])
    }

    void 'data() transforms castles into triples'() {
        given:
        def castles = [new Castle(name:'A', city:'Boston', state:'MA'),
                       new Castle(name:'B', city:'Dallas', state:'TX'),
                       new Castle(name:'C', city:'Los Angeles', state:'CA')]

        when:
        castles.each { c ->
            service.fillInLatLng(c)
            c.save()
        }
        def triples = service.data()

        then:
        triples.size() == 3
        triples.collect { it[0] }.sort() == castles*.latitude.sort()
        triples.collect { it[1] }.sort() == castles*.longitude.sort()
        triples.collect { it[2] }.sort() == castles*.toString().sort()
    }
}
