package Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jorn on 1/9/17.
 */
public class RatingParser extends Parser {
    private static RatingParser instance;
    private boolean started = false, movieListStart = false;

    static RatingParser getInstance() {
        if(instance == null) instance = new RatingParser();
        return instance;
    }

    private RatingParser() {
        filename = "ratings";
        pw = createPrintWriter();
        pw.println("title,firstYear,seriesTitle,seriesSeason,seriesEpisode,distribution,votes,rating");
    }

    @Override
    void process(String line) {
        if(started) {

            Matcher match = Pattern.compile("([\\d.]{10})\\s+(\\d+)\\s+([\\d.]+)").matcher(line);

            if(!match.find()) {
                return;
            }

            String  distribution = match.group(1),
                    votes = match.group(2),
                    rating = match.group(3);

            String[] splitSpaces = line.split("  ");
            String movieInfo = MoviesParser.getInstance().parseMovieString(splitSpaces[splitSpaces.length - 1]);
            if(movieInfo.equals("")) {
                return;
            }


            writeToStream(movieInfo + formatAsCSV(distribution, votes, rating));

        } else if(movieListStart && line.equals("New  Distribution  Votes  Rank  Title")) {
            started = true;
        } else if(line.equals("MOVIE RATINGS REPORT")) {
            movieListStart = true;
        }
    }
}
