package demo

class Book {

    String title
    Publisher publisher    

    static hasMany = [
            categories: Category
        ]

    static constraints = {
    }
}
