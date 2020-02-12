package searcher;

import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class FileReader {

  private static final String FOLDER_IS_EMPTY = "Folder is empty";

  char[] readFileToCharArray(File file) throws IOException {
    StringBuilder fileData = new StringBuilder(1000);
    BufferedReader reader = new BufferedReader(new java.io.FileReader(file));

    char[] buf = new char[10];
    int numRead;
    while ((numRead = reader.read(buf)) != -1) {
      String readData = String.valueOf(buf, 0, numRead);
      fileData.append(readData);
      buf = new char[1024];
    }
    reader.close();

    return fileData.toString().toCharArray();
  }

  List<File> readFilesFromDirectory(File folder) {
    File[] listOfFiles = folder.listFiles();
    if (listOfFiles == null || listOfFiles.length == 0) {
      throw new IllegalStateException(FOLDER_IS_EMPTY);
    }

    return Arrays.stream(listOfFiles)
        .filter(File::isFile)
        .collect(Collectors.toList());
  }

}
