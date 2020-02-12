package searcher

import searcher.KarpRabin
import spock.lang.Specification

class KarpRabinSpec extends Specification {


  def "should math pattern properly"() {
    when:
    boolean result = KarpRabin.match(pattern.toCharArray(), text.toCharArray())

    then:
    expectedResult == result

    where:
    expectedResult || pattern           | text
    true           || "text"            | "sdadsaText text"
    false          || "master"          | "sdadsaText text"
    false          || ""                | "sds"
    false          || "sasa"            | ""
    true           || "some"            | "some text"
    true           || "a"               | "sdadsaText text"
    false          || "aaaaaaaaaaaaaaa" | "sdadsaText text"
  }

  def "should return false for null arguments"() {
    when:
    boolean result = KarpRabin.match(pattern, text)

    then:
    expectedResult == result

    where:
    expectedResult || pattern                | text
    false          || null                   | "sdadsaText text".toCharArray()
    false          || "master".toCharArray() | null
    false          || null                   | null
  }


}