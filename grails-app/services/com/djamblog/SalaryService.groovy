package com.djamblog

import grails.gorm.services.Service

@Service(Salary)
interface SalaryService {

    Salary get(Serializable id)

    List<Salary> list(Map args)

    Long count()

    void delete(Serializable id)

    Salary save(Salary salary)

}