package com.example.guava.base

import com.google.common.base.CharMatcher
import com.google.common.base.Splitter
import com.google.common.collect.Maps
import spock.lang.Shared
import spock.lang.Specification

import java.util.regex.Pattern

class SplitterSpec extends Specification {
    @Shared
    Splitter splitter = Splitter.on('|')

    def "split on character"() {
        expect:
        splitter.split("foo|bar|baz").asList() == ["foo", "bar", "baz"]
    }

    def "split on character keep missing"() {
        expect:
        splitter.split("foo|bar|||baz").asList() == ["foo", "bar", "", "", "baz"]
    }

    def "split on character omit missing"() {
        expect:
        splitter.omitEmptyStrings().split("foo|bar|||baz").asList() == ["foo", "bar", "baz"]
    }

    def "trim leading and trailing whitespace"() {
        expect:
        splitter.trimResults().split("  foo   | bar|baz   ").toList() == ["foo", "bar", "baz"]
    }

    def "trim numbers with custom matcher"() {
        expect:
        splitter.trimResults(CharMatcher.JAVA_DIGIT).split("foo1|2bar3|4baz").toList() == ["foo", "bar", "baz"]
    }

    def "split on string pattern"() {
        expect:
        Splitter.onPattern("\\d+").split("foo123bar45678baz").toList() == ["foo", "bar", "baz"]
    }

    def "split on pattern"() {
        expect:
        Splitter.on(Pattern.compile("\\d+")).split("foo123bar45678baz").toList() == ["foo", "bar", "baz"]
    }

    def "split map"() {
        def s = "Washington D.C=Redskins|New York City=Giants|Philadelphia=Eagles|Dallas=Cowboys"

        Map<String, String> expected = Maps.newLinkedHashMap()
        expected.put("Washington D.C", "Redskins")
        expected.put("New York City", "Giants")
        expected.put("Philadelphia", "Eagles")
        expected.put("Dallas", "Cowboys")

        expect:
        splitter.withKeyValueSeparator("=").split(s) == expected
    }
}
