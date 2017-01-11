package Parser;

import java.util.Objects;

/**
 * Created by Valesco on 1/11/2017.
 */
public class BiographyParser extends Parser {
    private static BiographyParser instance;
    private boolean started = false;
    private String actorInfo = "";
    private StringBuilder reason;

    static BiographyParser getInstance() {
        if(instance == null) instance = new BiographyParser();
        return instance;
    }

    private BiographyParser() {
        filename = "biographies";
        pw = createPrintWriter();
    }

    @Override
    void process(String line) {
        if(started) {
            if(line.equals("") || line.length() < 2) return;

            if(Objects.equals(line.substring(0, 2), "RN")) {
                actorInfo = line.substring(4);
                //System.out.println(actorInfo);
                reason = new StringBuilder();
            }
            else if (Objects.equals(line.substring(0, 2), "DB") && actorInfo != "") {
                reason.append(line.substring(4));
            }
            else if(reason != null){
                System.out.println(actorInfo + " " + reason.toString());
                //writeToStream(actorInfo + formatAsCSV(reason.toString()));
                reason = null;
            }
        }
        else if(line.equals("==============")) {
            started = true;
        }
    }
    /*
    private void createNewActor(String line) {

        if (Objects.equals(line.substring(0, 2), "RN")) {
            System.out.println(line.substring(4));
        }

        if (Objects.equals(line.substring(0, 2), "DB")) {
            System.out.println(line.substring(4));
        }

    }
    */
}
