package searcher;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
class TextSearcher {
  private static final String NO_MATCHES_FOUND = "No matches found";
  private static final String TOO_MUCH_ARGUMENTS = "arguments should be no more than 10";

  String match(String pattern, char[] text) {
    String[] words = pattern.split("\\s");
    int length = words.length;
    if (length == 0) {
      return NO_MATCHES_FOUND;
    }
    if (length > 10) {
      return TOO_MUCH_ARGUMENTS;
    }

    long matches = Arrays.stream(words).map(word ->
        KarpRabin.match(word.toCharArray(), text))
        .filter(match -> match)
        .count();
    if (matches == 0) {
      return matches + "%";
    }
    return calculatePercentageMatch(length, matches);
  }

  private String calculatePercentageMatch(int length, long matches) {
    return Math.round(100.0 / length * matches) + "%";
  }

}
