package rest.api

import demo.Author
import demo.Book
import demo.Category
import demo.Publisher


class BootStrap {

    def init = { servletContext ->
        Author.withTransaction {
        println 'Hello from bootstrap'

        // Stand-alone category
        def science = new Category(name: 'science').save(failOnError: true)

        // publishers
        def manning = new Publisher(name: 'manning').save(failOnError: true)
        def amazon =   new Publisher(name: 'amazon').save(failOnError: true)

        
        // Create single author
        def johnDoe = new Author(name: 'John Doe').save(failOnError: true)

        // Create-cascade from Author-Book-Category with explicid 'new' and 'save' Book
        def jackDanields = new Author(name: 'Jack Daniels')
            .addToBooks(new Book(title: 'Hate book', publisher: manning).addToCategories(name: 'love').save())
            .addToBooks(new Book(title: 'Fiction book', publisher: manning).addToCategories(name: 'fiction').save())
            .save(failOnError: true)


        // Create-cascade from Author-Book without explicit save of book
        def zoran = new Author(name: 'Zoran')
            .addToBooks(title: 'First book', publisher: manning)
            .addToBooks(title: 'Second book', publisher: manning)
            .addToBooks(title: 'Third book', publisher: manning)
            .save(failOnError: true)
        }
    }

    def destroy = {
        println 'Goodbye from bootstrap'
    }
}
