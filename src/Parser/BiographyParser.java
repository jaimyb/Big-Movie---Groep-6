package Parser;

import java.util.Objects;

/**
 * Created by Valesco on 1/11/2017.
 */
public class BiographyParser extends Parser {
    private static BiographyParser instance;
    private boolean started = false,
            newActor = true;
    private String actorInfo = "";

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
            if(line.equals("")) return;

            if(line.equals("-------------------------------------------------------------------------------") && newActor) {
                System.out.println("started actor");
                newActor = false;
                return;
            }

            if (!newActor) createNewActor(line);

            if(line.equals("-------------------------------------------------------------------------------") && !newActor) {
                System.out.println("ended actor");
                newActor = true;
                return;
            }

        } else if(line.equals("==============")) {
            started = true;
        }
    }

    private void createNewActor(String line) {

        if (Objects.equals(line.substring(0, 2), "RN")) {
            System.out.println(line.substring(4));
        }

        if (Objects.equals(line.substring(0, 2), "DB")) {
            System.out.println(line.substring(4));
        }

    }

}
