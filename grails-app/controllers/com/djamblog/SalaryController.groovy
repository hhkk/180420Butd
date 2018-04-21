package com.djamblog

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SalaryController {

    SalaryService salaryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond salaryService.list(params), model:[salaryCount: salaryService.count()]
    }

    def show(Long id) {
        respond salaryService.get(id)
    }

    def create() {
        respond new Salary(params)
    }

    def save(Salary salary) {
        if (salary == null) {
            notFound()
            return
        }

        try {
            salaryService.save(salary)
        } catch (ValidationException e) {
            respond salary.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'salary.label', default: 'Salary'), salary.id])
                redirect salary
            }
            '*' { respond salary, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond salaryService.get(id)
    }

    def update(Salary salary) {
        if (salary == null) {
            notFound()
            return
        }

        try {
            salaryService.save(salary)
        } catch (ValidationException e) {
            respond salary.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'salary.label', default: 'Salary'), salary.id])
                redirect salary
            }
            '*'{ respond salary, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        salaryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'salary.label', default: 'Salary'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'salary.label', default: 'Salary'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
