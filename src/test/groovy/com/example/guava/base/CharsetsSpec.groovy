package com.example.guava.base

import com.google.common.base.Charsets
import spock.lang.Requires
import spock.lang.Shared
import spock.lang.Specification

import java.nio.charset.StandardCharsets

class CharsetsSpec extends Specification {
    @Shared
    String string = "asd"

    def "getting bytes"() {
        when:
        try {
            string.getBytes("UTF-8")
        } catch (UnsupportedEncodingException e) {
            // since UTF-8 must be supported by every Java platform
            // this exception will never happen
            throw new RuntimeException(e)
        }

        then:
        noExceptionThrown()
    }

    @Requires({ jvm.java6Compatible })
    def "getting bytes with Charset help"() {
        when:
        // java.lang.String.getBytes(java.nio.charset.Charset)
        // doesn't throw checked exception
        string.getBytes(Charsets.UTF_8)

        then:
        noExceptionThrown()
    }

    @Requires({ jvm.java7Compatible })
    def "getting bytes in Java SE 7+"() {
        when:
        // StandardCharsets was added in Java 7
        // and can be used to replace Guava's Charsets
        string.getBytes(StandardCharsets.UTF_8)

        then:
        noExceptionThrown()
    }
}
