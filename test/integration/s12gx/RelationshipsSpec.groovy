package s12gx

import spock.lang.*

class RelationshipsSpec extends Specification {

    void "can delete the quest after removing knights"() {
        given:
        Quest q = Quest.findByName('Seek the grail')

        when:
        Knight.list().each { k -> k.quest = null }
        q.delete()

        then:
        Task.count() == 0
        !Quest.findByName('Seek the grail')
        Castle.count() == old(Castle.count())
    }
}
