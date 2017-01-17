package Parser;

/**
 * Created by jorn on 1/9/17.
 */
public class LocationParser extends Parser {
    private static LocationParser instance;
    private boolean started = false;

    static LocationParser getInstance() {
        if(instance == null) instance = new LocationParser();
        return instance;
    }

    private LocationParser() {
        filename = "locations";
        pw = createPrintWriter();
        pw.println("title,firstYear,seriesTitle,seriesSeason,seriesEpisode,location,specification");
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

            String location, specification, lastElement = splitTabs[splitTabs.length - 1];


            // last element is specification
            if(lastElement.charAt(0) == '(' && lastElement.charAt(lastElement.length() - 1) == ')') {
                location = wrapInQuotes(splitTabs[splitTabs.length - 2]);
                specification = lastElement;
            }
            else {
                location = wrapInQuotes(lastElement);
                specification = "";
            }

            writeToStream(movieInfo + formatAsCSV(location, specification.equals("") ? "" : specification.substring(1, specification.length() - 1)));

        } else if(line.equals("==============")) {
            started = true;
        }
    }
}
