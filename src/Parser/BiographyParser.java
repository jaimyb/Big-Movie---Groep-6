package Parser;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Valesco on 1/11/2017.
 */
public class BiographyParser extends Parser {
    private static BiographyParser instance;
    private boolean started = false;
    private String actorInfo = "";

    static BiographyParser getInstance() {
        if (instance == null) instance = new BiographyParser();
        return instance;
    }

    private BiographyParser() {
        filename = "biographies";
        pw = createPrintWriter();
        pw.println("actorInfo,birthDate,birthCountry");
    }

    @Override
    void process(String line) {
        if (started) {
            if (line.length() < 2) return;

            if (Objects.equals(line.substring(0, 2), "RN")) {
                actorInfo = line.substring(4);
            } else if (Objects.equals(line.substring(0, 2), "DB") && !actorInfo.equals("")) {
                String birthInfo = line.substring(4);

                Matcher matcher = Pattern.compile("[^(](.*?\\d{4}), ([^\\[(\\n]*)").matcher(birthInfo);
                if (!matcher.find()) {
                    return;
                }

                String birthDate = matcher.group(1);
                if (birthDate.length() == 4) {
                    birthDate = "1 January " + birthDate;
                }

                String birthLocation = matcher.group(2);

                String[] splitLoc = birthLocation.split(",");


                writeToStream(formatAsCSV(wrapInQuotes(escapeQuotes(actorInfo)), birthDate, wrapInQuotes(escapeQuotes(splitLoc[splitLoc.length - 1].trim()))));
                actorInfo = "";
            }
        } else if (line.equals("==============")) {
            started = true;
        }
    }
}
