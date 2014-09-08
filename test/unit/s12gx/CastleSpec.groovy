package s12gx

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(Castle)
class CastleSpec extends Specification {

    def setup() {
        mockForConstraintsTests(Castle)
    }

    @Unroll
    void "#name, #city, #state, #lat, #lng is #result"(String name, String city, String state,
                                                       double lat, double lng) {
        expect:
        new Castle(name: name, city: city, state: state,
                latitude: lat, longitude: lng).validate() == valid

        where:
        [name, city, state, lat, lng] <<
                [['name',' '],['city', ' '],['state',' '],
                 [-95, -90, 0, 90, 91],[10]].combinations()

        valid = name.trim() && city.trim() && state.trim() && lat >= -90 && lat <= 90
        result = valid ? 'valid' : 'NOT valid'
    }
}
