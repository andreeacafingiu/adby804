package game;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HighScoreReader {

    private String fileName;

    public HighScoreReader(String fileName) {
        this.fileName = fileName;

    }

    public List<String> readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        List<String> scores = new ArrayList<>();
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                String name = tokens[0];
                int score = Integer.parseInt(tokens[1]);
                scores.add("Name:\t " + name + "\t\t\tScore:\t" + score);
                line = reader.readLine();
            }
            System.out.println("...done.");
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
        return scores;
    }
}