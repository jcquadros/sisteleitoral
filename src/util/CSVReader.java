package util;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private String path;

    public List<List<String>> getLines() {
        List<List<String>> lines = new ArrayList<>();
        try {
            FileInputStream f = new FileInputStream(this.path);
            Scanner scanner = new Scanner(f, "ISO-8859-1");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(";");

                List<String> columns = new ArrayList<String>();
                while (lineScanner.hasNext()) {
                    String column = lineScanner.next();
                    column = column.replace("\"", "");
                    columns.add(column);
                }
                lines.add(columns);
                lineScanner.close();
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo CSV: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return lines;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
