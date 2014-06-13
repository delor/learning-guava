package com.example.guava.base

import spock.lang.Specification

class PreconditionsSpec extends Specification {

    def "check for null when creating object"() {
        when:
        new PreconditionsExample(null)

        then:
        def ex = thrown(NullPointerException)
        ex.message == "Input value can't be null"
    }

    def "check argument when creating object"() {
        when:
        new PreconditionsExample("1,")

        then:
        def ex = thrown(IllegalArgumentException)
        ex.message == "Input must be numbers separated by comma"
    }

    def "check index"() {
        def example = new PreconditionsExample("1,2")

        when:
        example.updateCurrentIndexValue(2, 1)

        then:
        def ex = thrown(IndexOutOfBoundsException)
        ex.message == "Index out of bounds for values (2) must be less than size (2)"
    }

    def "check argument when invoking method"() {
        def example = new PreconditionsExample("1,2")

        when:
        example.updateCurrentIndexValue(0, 101)

        then:
        def ex = thrown(IllegalArgumentException)
        ex.message == "Value can't be bigger than 100"
    }

    def "check state when invoking method"() {
        def example = new PreconditionsExample("0,2")

        when:
        example.sum()

        then:
        def ex = thrown(IllegalStateException)
        ex.message == "Can't perform operation, all parameters has to be positive"
    }
}
