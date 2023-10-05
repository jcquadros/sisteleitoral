package util;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private String path;

    public CSVReader(String path) {
        this.path = path;
    }

    public List<String[]> getLines() {
        List<String[]> lines = new ArrayList<String[]>();
        try {
            FileInputStream f = new FileInputStream(this.path);
            Scanner scanner = new Scanner(f, "ISO-8859-1");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(";");
                lines.add(columns);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo CSV: " + e.getMessage());
        }

        return lines;
    }
}
