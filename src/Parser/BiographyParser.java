package Parser;

import java.util.Objects;

/**
 * Created by Valesco on 1/11/2017.
 */
public class BiographyParser extends Parser {
    private static BiographyParser instance;
    private boolean started = false;
    private String actorInfo = "";

    static BiographyParser getInstance() {
        if(instance == null) instance = new BiographyParser();
        return instance;
    }

    private BiographyParser() {
        filename = "biographies";
        pw = createPrintWriter();
        pw.println("actorInfo,birthInfo");
    }

    @Override
    void process(String line) {
        if(started) {
            if(line.length() < 2) return;

            if(Objects.equals(line.substring(0, 2), "RN")) {
                actorInfo = line.substring(4);
            }
            else if (Objects.equals(line.substring(0, 2), "DB") && !actorInfo.equals("")) {
                String birthInfo = line.substring(4);


                writeToStream(formatAsCSV(wrapInQuotes(escapeQuotes(actorInfo)), wrapInQuotes(escapeQuotes(birthInfo))));
                actorInfo = "";
            }
        }
        else if(line.equals("==============")) {
            started = true;
        }
    }
}
