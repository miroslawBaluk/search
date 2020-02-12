package searcher;

public class KarpRabin {
  private static final int SHIFT = 31;
  private static final int BITS = 64;
  private final long patternHash;
  private final int size;
  private final int endPosition;
  private final int tailLeftShift, tailRightShift;
  private long textHash;
  private int pos = 0;

  private KarpRabin(char[] patternChars, char[] textChars) {
    size = patternChars.length;
    endPosition = textChars.length - size;
    tailLeftShift = ((size - 1) * SHIFT) % BITS;
    tailRightShift = BITS - tailLeftShift;
    long ph = 0L;
    for (char c : patternChars) {
      ph = addHash(ph, c);
    }
    patternHash = ph;
    textHash = 0L;
    for (int i = 0; i < size; i++) {
      textHash = addHash(textHash, textChars[i]);
    }
    if (textHash != patternHash || !confirmed(patternChars, textChars)) {
      advance(patternChars, textChars);
    }
  }

  private long addHash(long base, char c) {
    return (base << SHIFT | base >>> (BITS - SHIFT)) ^ c;
  }

  private long removeHash(long base, char c) {
    long ch = c;
    ch = ch << tailLeftShift | ch >>> tailRightShift;
    return base ^ ch;
  }

  private int next(char[] patternChars, char[] textChars) {
    if (pos > endPosition) {
      return -1;
    }
    int ret = pos;
    advance(patternChars, textChars);
    return ret;
  }

  private void advance(char[] patternChars, char[] textChars) {
    while (++pos <= endPosition) {
      textHash = removeHash(textHash, textChars[pos - 1]);
      textHash = addHash(textHash, textChars[pos + size - 1]);
      if (textHash == patternHash && confirmed(patternChars, textChars)) {
        return;
      }
    }
  }

  private boolean confirmed(char[] patternChars, char[] textChars) {
    for (int i = 0; i < size; i++) {
      if (patternChars[i] != textChars[pos + i]) {
        return false;
      }
    }
    return true;
  }

  public static boolean match(char[] pattern, char[] text) {
    if (pattern == null || text == null || pattern.length > text.length || pattern.length == 0) {
      return false;
    }

    KarpRabin kr = new KarpRabin(pattern, text);
    return kr.next(pattern, text) >= 0;
  }

}

