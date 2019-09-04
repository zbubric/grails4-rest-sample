package demo

class Publisher {

    String name

    static hasMany = [books:Book]    

    static constraints = {
    }
}
