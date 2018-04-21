package grails3.authentication

import com.djamblog.auth.Authority
import com.djamblog.auth.User

class BootStrap {

    def init = { servletContext ->
        def role = Authority.findByAuthority("ROLE_USER")?:new Authority(authority:"ROLE_USER").save(flush:true)
        def user = User.findByUsername("graeme2")?:new User(username:"graeme2",password:"q1w2e3r4").save(flush:true)

        if (!user.authorities) {
            user.authorities = [role]
            user.save flush:true
        }
        print "hi hk";

        def maxLineNumbers =  grailsApplication.config.getProperty('max.line.numbers')

    }
    def destroy = {
    }
}
