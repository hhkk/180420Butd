package com.djamblog

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SalaryServiceSpec extends Specification {

    SalaryService salaryService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Salary(...).save(flush: true, failOnError: true)
        //new Salary(...).save(flush: true, failOnError: true)
        //Salary salary = new Salary(...).save(flush: true, failOnError: true)
        //new Salary(...).save(flush: true, failOnError: true)
        //new Salary(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //salary.id
    }

    void "test get"() {
        setupData()

        expect:
        salaryService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Salary> salaryList = salaryService.list(max: 2, offset: 2)

        then:
        salaryList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        salaryService.count() == 5
    }

    void "test delete"() {
        Long salaryId = setupData()

        expect:
        salaryService.count() == 5

        when:
        salaryService.delete(salaryId)
        sessionFactory.currentSession.flush()

        then:
        salaryService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Salary salary = new Salary()
        salaryService.save(salary)

        then:
        salary.id != null
    }
}
