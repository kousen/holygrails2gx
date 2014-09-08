package s12gx

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(Task)
class TaskSpec extends Specification {
    Task t = new Task(name:'name', quest:new Quest(name:'n'))

    def setup() {
        mockForConstraintsTests(Task)
    }

    void "default duration when start and end are equal is 1"() {
        expect: t.duration == 1
    }

    void 'duration based on start and end dates'() {
        when:
        t.endDate = t.startDate + 1

        then:
        t.duration == 2
    }

    void 'sample task is valid'() {
        expect: t.validate()
    }

    void 'blank name not allowed'() {
        when:
        t.name = '  '

        then:
        !t.validate()
        'blank' == t.errors['name']
    }

    void 'too low priority is not valid'() {
        when:
        t.priority = Task.MIN_PRIORITY - 1

        then:
        !t.validate()
        'range' == t.errors.priority
    }

    void 'too high priority not valid'() {
        when:
        t.priority = Task.MAX_PRIORITY + 1

        then:
        !t.validate()
        'range' == t.errors.priority
    }

    @Unroll
    void 'task with priority #p is valid'() {
        expect:
        new Task(name:'n', quest:new Quest(name:'n'), priority:p).validate()

        where:
        p << (Task.MIN_PRIORITY..Task.MAX_PRIORITY)
    }

    void 'endDate before startDate fails'() {
        when:
        t.endDate = t.startDate - 1

        then:
        !t.validate()
        'validator' == t.errors.endDate
    }
}
