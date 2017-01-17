package Parser;

import java.io.PrintWriter;

/**
 * Created by jorn on 12/19/16.
 */
class MoviesParser extends Parser {
    private static MoviesParser instance;
    private boolean started = false;

    static MoviesParser getInstance() {
        if (instance == null) instance = new MoviesParser();
        return instance;
    }

    private MoviesParser() {
        filename = "movies";
        pw = createPrintWriter();
    }

    void process(String line) {
        if(line.length() == 0) return;



        if(started)
        {
            String s = parseMovieString(line);
            if(s.equals("")) {
                return;
            }

            String secondYear = lastWord(line).trim();
            String result = s + formatAsCSV(secondYear);
            writeToStream(result);

        }
        else if(line.equals("===========")) {
            started = true;
        }
    }

    String parseMovieString(String line) {
        int firstBrace = line.indexOf('(');

        // Not a correct line
        if(firstBrace == -1) {
            return "";
        }

        String title = partBefore(line, "(").trim().replace("\"", "");

        // Not a correct line
        if(title.equals("")) {
            return "";
        }

        String firstYear = partBetween(line, "(", ")");
        String seriesInfo = partBetween(line, "{", "}");

        String seriesTitle = allButLastWord(seriesInfo);
        String seriesNumber = partBetween(seriesInfo, "(", ")");

        String seriesSeason = "";
        String seriesEpisode = "";

        // Not a movie
        if(!seriesNumber.equals(""))
        {
            // Not a series
            if(seriesNumber.charAt(0) != '#') {
                return "";
            }

            int hashTagIndex = seriesNumber.indexOf('#');
            int dotIndex = seriesNumber.indexOf('.');

            if(dotIndex == -1) {
                seriesSeason = seriesNumber.substring(hashTagIndex + 1);
            }
            else {
                seriesSeason = seriesNumber.substring(hashTagIndex + 1, dotIndex);
                seriesEpisode = seriesNumber.substring(dotIndex + 1);
            }

        }

        return formatAsCSV(wrapInQuotes(title), firstYear, wrapInQuotes(seriesTitle), seriesSeason, seriesEpisode);
    }
}