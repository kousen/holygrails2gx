package s12gx

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Quest)
class QuestSpec extends Specification {
    Quest q = new Quest(name:'name')

    def setup() {
        mockForConstraintsTests(Quest)
    }

    void "sample is valid"() {
        expect: q.validate()
    }

    void 'blank name is not valid'() {
        when:
        q.name = '  '

        then:
        !q.validate()
        'blank' == q.errors.name
    }
}
