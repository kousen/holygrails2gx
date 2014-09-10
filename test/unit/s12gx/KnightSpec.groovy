package s12gx

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(Knight)
class KnightSpec extends Specification {

    def setup() {
        mockForConstraintsTests(Knight)
    }

    @Unroll
    void "#knight.toString() is #result"() {
        expect:
        knight.validate() == valid

        where:
                        knight                      |  title   |  name    | valid
        new Knight(title: 'Sir',    name: 'Robin')  | 'Sir'    | 'Robin'  | true
        new Knight(title: 'Lord',   name: 'Stark')  | 'Lord'   | 'Stark'  | true
        new Knight(title: 'King',   name: 'Arthur') | 'King'   | 'Arthur' | true
        new Knight(title: 'Squire', name: 'John')   | 'Squire' | 'John'   | true
        new Knight(title: 'Pope',   name: 'Paul')   | 'Pope'   | 'Paul'   | false
        new Knight(title: 'Sir',    name: ' ')      | 'Sir'    | ' '      | false

        result = valid ? 'valid' : 'NOT valid'
    }
}
