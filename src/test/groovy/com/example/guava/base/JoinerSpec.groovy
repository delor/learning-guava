package com.example.guava.base

import com.google.common.base.Joiner
import com.google.common.collect.Maps
import spock.lang.Shared
import spock.lang.Specification

class JoinerSpec extends Specification {
    @Shared Joiner joiner = Joiner.on('|')

    String[] array
    String[] arrayWithNulls

    def setup() {
        array = ["foo", "bar", "baz"]
        arrayWithNulls = ["foo", null, "bar", null, "baz"]
    }

    def "join values in string array"() {
        expect:
        joiner.join(array) == "foo|bar|baz"
    }

    def "throw NPE when null in array"() {
        when:
        joiner.join(arrayWithNulls)

        then:
        thrown(NullPointerException)
    }

    def "skipping nulls when joining"() {
        expect:
        joiner.skipNulls().join(arrayWithNulls) == "foo|bar|baz"
    }

    def "replace nulls with string"() {
        expect:
        joiner.useForNull("null").join(arrayWithNulls) == "foo|null|bar|null|baz"
    }

    def "join key and value from map"() {
        String expectedString = "Washington D.C=Redskins|New York City=Giants|Philadelphia=Eagles|Dallas=Cowboys"

        // Using LinkedHashMap so that original order is preserved
        Map<String, String> testMap = Maps.newLinkedHashMap()
        testMap.put("Washington D.C", "Redskins")
        testMap.put("New York City", "Giants")
        testMap.put("Philadelphia", "Eagles")
        testMap.put("Dallas", "Cowboys")

        when:
        String returnedString = joiner.withKeyValueSeparator("=").join(testMap)

        then:
        returnedString == expectedString
    }
}
