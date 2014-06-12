package com.example.guava.base

import com.google.common.base.CharMatcher
import spock.lang.Specification

class CharMatcherSpec extends Specification {
    def "remove linebreaks"() {
        def stringWithLinebreaks = "This is an example\n" +
                "of a String with linebreaks\n" +
                "we want on one line"
        def expected = "This is an example of a String with linebreaks we want on one line"

        expect:
        expected == CharMatcher.BREAKING_WHITESPACE.replaceFrom(stringWithLinebreaks, " ")
    }

    def "remove white space"() {
        def toMuchWhitespace = "String     with   spaces      "
        def expected = "String with spaces "
        char replacement = ' '

        expect:
        expected == CharMatcher.WHITESPACE.collapseFrom(toMuchWhitespace, replacement)
    }

    def "trim and remove white space"() {
        def toMuchWhitespace = "   String     with   spaces  "
        def expected = "String with spaces"
        char replacement = ' '

        expect:
        expected == CharMatcher.WHITESPACE.trimAndCollapseFrom(toMuchWhitespace, replacement)
    }

    def "retain specific characters"() {
        String lettersAndNumbers = "foo989yxbar234";
        String expected = "989234";

        expect:
        expected == CharMatcher.JAVA_DIGIT.retainFrom(lettersAndNumbers);
    }

    def "combine matchers"() {
        char startInclusive = 'A'
        char endInclusive = 'E'
        def rangeMatcher = CharMatcher.inRange(startInclusive, endInclusive)
        def string = "aABCDEFf102983"
        def expected = "ABCDE102983"


        expect:
        expected == rangeMatcher.or(CharMatcher.JAVA_DIGIT).retainFrom(string)
    }
}
