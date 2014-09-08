package s12gx

class Quest {
    String name

    String toString() { name }

    static hasMany = [tasks:Task]

    static constraints = {
        name blank: false
    }
}
