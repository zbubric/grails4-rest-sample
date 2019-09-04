package demo

class Category {

    String name

    static belongsTo = Book
    static hasMany = [books:Book]

    static constraints = {
    }
}
