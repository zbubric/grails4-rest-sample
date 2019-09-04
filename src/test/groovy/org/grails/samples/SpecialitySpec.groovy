package org.grails.samples

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class SpecialitySpec extends Specification implements DomainUnitTest<Speciality> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
