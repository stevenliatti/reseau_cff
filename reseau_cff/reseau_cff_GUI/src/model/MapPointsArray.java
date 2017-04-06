package model;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MapPointsArray extends ArrayList<Point> {
    public MapPointsArray(String filePath) throws IOException, NumberFormatException{
        LineNumberReader lineReader;
        InputStream file = getClass().getResourceAsStream(filePath);
        lineReader = new LineNumberReader(new InputStreamReader(file));
        String line;

        while ((line = lineReader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            this.add(new Point(x, y, true));
        }
    }
}
