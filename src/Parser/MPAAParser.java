package Parser;

/**
 * Created by jorn on 1/9/17.
 */
public class MPAAParser extends Parser {
    private static MPAAParser instance;
    private boolean started = false;
    private String movieInfo;
    private StringBuilder reason;

    static MPAAParser getInstance() {
        if(instance == null) instance = new MPAAParser();
        return instance;
    }

    private MPAAParser() {
        filename = "mpaa-ratings-reasons";
        pw = createPrintWriter();
    }

    @Override
    void process(String line) {
        if(started) {
            if(line.startsWith("MV: ")) {
                movieInfo = MoviesParser.getInstance().parseMovieString(line.substring(4));
                reason = new StringBuilder();
            }
            else if (line.startsWith("RE: ")) {
                reason.append(line.substring(4));
            }
            else if(reason != null){
                String[] splitFor = reason.toString().split(" for ");
                if(splitFor.length != 2) return;
                String rating;
                if(splitFor[0].contains("Rated ")) {
                    rating = splitFor[0].substring(6);
                }
                else {
                    rating = splitFor[0];
                }
                String description = splitFor[1].substring(0, splitFor[1].length() - 1);
                writeToStream(movieInfo + formatAsCSV(rating, wrapInQuotes(description)));
                reason = null;
            }
        }
        else if(line.equals("=========================")) {
            started = true;
        }
    }
}
