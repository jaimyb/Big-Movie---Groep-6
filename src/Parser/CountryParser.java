package Parser;

/**
 * Created by jorn on 1/9/17.
 */
public class CountryParser extends Parser {
    private static CountryParser instance;
    private boolean started = false;

    static CountryParser getInstance() {
        if(instance == null) instance = new CountryParser();
        return instance;
    }

    private CountryParser() {
        filename = "countries";
        pw = createPrintWriter();
        pw.println("title,firstYear,seriesTitle,seriesSeason,seriesEpisode,country");

    }

    @Override
    void process(String line) {
        if(started) {
            if(line.equals("")) return;

            String[] splitTabs = line.split("\t");

            // Parse movies
            String movieInfo = MoviesParser.getInstance().parseMovieString(splitTabs[0]);

            // Can't be parsed
            if(movieInfo.equals("")) {
                return;
            }

            writeToStream(movieInfo + formatAsCSV(splitTabs[splitTabs.length - 1]));

        } else if(line.equals("==============")) {
            started = true;
        }
    }
}
