package model;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by raed on 23.03.17.
 */
public class MapPointsArray extends ArrayList<Point> {
    public MapPointsArray(String filePath) throws IOException, NumberFormatException{
        LineNumberReader lineReader = null;
        lineReader = new LineNumberReader(new FileReader(filePath));
        String line;

        while ((line = lineReader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            this.add(new Point(x * 3 - 1400, -(y * 3) + 900));
        }
    }
}
