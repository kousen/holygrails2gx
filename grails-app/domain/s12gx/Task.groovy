package s12gx

class Task {
    public static final int MIN_PRIORITY = 1
    public static final int MAX_PRIORITY = 5

    String name
    int priority = 3
    Date startDate = new Date()
    Date endDate = new Date()
    boolean completed

    int getDuration() { endDate - startDate + 1 }

    static transients = ['duration']

    static constraints = {
        name blank: false
        priority range: MIN_PRIORITY..MAX_PRIORITY
        startDate()
        endDate validator: { val, t ->
            val >= t.startDate
        }
        completed()
    }
}
