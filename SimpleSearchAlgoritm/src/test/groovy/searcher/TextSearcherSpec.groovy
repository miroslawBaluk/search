package searcher

import searcher.TextSearcher
import spock.lang.Specification

class TextSearcherSpec extends Specification {

  private TextSearcher textSearcher = new TextSearcher()

  def "should calculate match relevance"() {
    when:
    String result = textSearcher.match(pattern, text.toCharArray())

    then:
    expectedResult == result

    where:
    expectedResult                        || pattern                   | text
    "33%"                                 || "text notmatch sddsadsa"  | "sdadsaText text"
    "50%"                                 || "text sddsadsa"           | "sdadsaText text"
    "100%"                                || "text"                    | "sdadsaText text"
    "0%"                                  || "notmatch"                | "sdadsaText text"
    "0%"                                  || ""                        | "sdadsaText text"
    "0%"                                  || "s"                       | ""
    "arguments should be no more than 10" || "1 2 3 4 5 6 7 8 9 10 11" | "sdadsaText text"
  }


}