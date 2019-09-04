package demo

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class CategoryController {

    CategoryService categoryService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond categoryService.list(params), model:[categoryCount: categoryService.count()]
    }

    def show(Long id) {
        respond categoryService.get(id)
    }

    @Transactional
    def save(Category category) {
        if (category == null) {
            render status: NOT_FOUND
            return
        }
        if (category.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond category.errors
            return
        }

        try {
            categoryService.save(category)
        } catch (ValidationException e) {
            respond category.errors
            return
        }

        respond category, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Category category) {
        if (category == null) {
            render status: NOT_FOUND
            return
        }
        if (category.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond category.errors
            return
        }

        try {
            categoryService.save(category)
        } catch (ValidationException e) {
            respond category.errors
            return
        }

        respond category, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        categoryService.delete(id)

        render status: NO_CONTENT
    }
}
