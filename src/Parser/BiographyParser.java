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
    private String actorInfo;

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

            if (Objects.equals(line.substring(0, 2), "NM")) {
                String name = line.substring(4);
                String[] actorSplit = name.split(", ");
                String lastName = actorSplit[0], firstName = "", idNumber = "";
                if(actorSplit.length > 1) {
                    if(actorSplit[1].contains("(")) {
                        firstName = partBefore(actorSplit[1], "(").trim();
                        idNumber = partBetween(actorSplit[1], "(", ")");
                    }
                    else {
                        firstName = actorSplit[1];
                    }
                }
                actorInfo = formatAsCSV(idNumber, wrapInQuotes(escapeQuotes(lastName)), wrapInQuotes(escapeQuotes(firstName)));
                actorInfo = actorInfo.substring(0, actorInfo.length() - 1);

            } else if (Objects.equals(line.substring(0, 2), "DB") && !actorInfo.equals("")) {
                String birthInfo = line.substring(4);

                Matcher matcher = Pattern.compile("(\\d{4}), ([^\\[(\\n]*)").matcher(birthInfo);
                if (!matcher.find()) {
                    return;
                }

                String birthDate = matcher.group(1);

                String birthLocation = matcher.group(2);

                String[] splitLoc = birthLocation.split(",");


                writeToStream(formatAsCSV(actorInfo, birthDate, wrapInQuotes(escapeQuotes(splitLoc[splitLoc.length - 1].trim()))));
                actorInfo = "";
            }
        } else if (line.equals("==============")) {
            started = true;
        }
    }
}
