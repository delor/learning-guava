package com.example.guava.base
import com.google.common.base.Strings
import spock.lang.Specification

class StringsSpec extends Specification {
    def "append filler to string up to 10 chars"() {
        char filler = '!'

        expect:
        Strings.padEnd("boom", 10, filler) == "boom!!!!!!"
    }

    def "prepend filler to string up to 10 chars"() {
        char filler = '0'

        expect:
        Strings.padStart("1", 10, filler) == "0000000001"
    }

    def "change null to empty or leave unchanged"() {
        expect:
        Strings.nullToEmpty("foo") == "foo"
        Strings.nullToEmpty(null) == ""
    }

    def "change empty to null or leave unchanged"() {
        expect:
        Strings.emptyToNull("foo") == "foo"
        Strings.emptyToNull("") == null
        Strings.emptyToNull(" ") == " "
    }

    def "check if value is null or empty string"() {
        expect:
        Strings.isNullOrEmpty("")
        Strings.isNullOrEmpty(null)
        !Strings.isNullOrEmpty("foo")
    }
}
