package searcher;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Searcher {
  private static final String QUIT = "quit";
  private static final TextSearcher textSearcher = new TextSearcher();
  private static final FileReader fileReader = new FileReader();

  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      throw new IllegalArgumentException("No directory given to index");
    }
    final File indexableDirectory = new File(args[0]);
    Scanner keyboard = new Scanner(System.in);
    List<File> files = fileReader.readFilesFromDirectory(indexableDirectory);

    Map<String, char[]> x = new HashMap<>();
    for (File file : files) {
      x.put(file.getName(), fileReader.readFileToCharArray(file));
    }

    System.out.println(files.size() + " files read in directory " + args[0]);

    while (true) {
      System.out.println("search> ");
      final String line = keyboard.nextLine();
      if (line.equals(QUIT)) {
        break;
      }
      x.forEach((k, v) -> System.out.println("File: " + k + " " + textSearcher.match(line, v)));
    }
  }

}
