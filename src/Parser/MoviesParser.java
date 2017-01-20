package Parser;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        pw.println("title,firstYear,seriesTitle,seriesSeason,seriesEpisode,secondYear");
    }

    void process(String line) {
        if (line.length() == 0) return;


        if (started) {
            String s = parseMovieString(line);
            if (s.equals("")) {
                return;
            }

            String secondYear = lastWord(line);

            secondYear = processYear(secondYear);

            String result = s + formatAsCSV(secondYear);
            writeToStream(result);

        } else if (line.equals("===========")) {
            started = true;
        }
    }

    String parseMovieString(String line) {
        Matcher matcher = Pattern.compile("(.*?) \\((\\d{4})\\)(?: \\{(.*)})?").matcher(line);

//        String title = partBefore(line, "(").trim().replace("\"", "");
//
//        // Not a correct line
//        if (title.equals("")) {
//            return "";
//        }
//
//        String firstYear = partBetween(line, "(", ")");
//
//        firstYear = processYear(firstYear);

        if(!matcher.find()) {
            return "";
        }

        String title = matcher.group(1);
        String firstYear = matcher.group(2);
        if(firstYear.equals("")) {
            return "";
        }
        String seriesInfo = matcher.group(3);

        String seriesTitle = "", seriesSeason = "", seriesEpisode = "";
        if(seriesInfo != null) {


            seriesTitle = allButLastWord(seriesInfo);

            int openIndex = seriesInfo.lastIndexOf('('),
            closeIndex = seriesInfo.lastIndexOf(')');

            String seriesNumber = "";
            if(openIndex > -1 && closeIndex > -1 && closeIndex > openIndex) {
                seriesNumber = seriesInfo.substring(openIndex + 1, closeIndex);
            }


            seriesSeason = "";
            seriesEpisode = "";

            // Not a movie
            if (!seriesNumber.equals("")) {
                // Not a series
                if (seriesNumber.charAt(0) != '#') {
                    return "";
                }
                int dotIndex = seriesNumber.lastIndexOf('.');

                Pattern digitPattern = Pattern.compile("^\\d+$");

                if (dotIndex == -1) {
                    seriesSeason = seriesNumber.substring(1);
                    if(!digitPattern.matcher(seriesSeason).find()) {
                        seriesSeason = "";
                    }
                } else {
                    seriesSeason = seriesNumber.substring(1, dotIndex);
                    if(!digitPattern.matcher(seriesSeason).find()) {
                        seriesSeason = "";
                    }
                    seriesEpisode = seriesNumber.substring(dotIndex + 1);
                    if(!digitPattern.matcher(seriesEpisode).find()) {
                        seriesEpisode = "";
                    }
                }

            }
        }

        String titleWrap = title.charAt(0) == '"' ? title : wrapInQuotes(escapeQuotes(title));

        return formatAsCSV(titleWrap, firstYear, wrapInQuotes(escapeQuotes(seriesTitle)), seriesSeason, seriesEpisode);
    }

    private String processYear(String year) {
        year = year.trim();

        if(year.length() < 4) {
            return "";
        }

        String sub = year.substring(0, 4);


        return sub.equals("????") ? "" : sub;
    }
}