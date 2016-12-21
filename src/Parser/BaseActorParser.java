package Parser;

/**
 * Created by jorn on 12/21/16.
 */
abstract class BaseActorParser extends Parser {
    private boolean started = false,
                    newActor = true;
    private String currentLine = "";

    @Override
    void process(String line) {
        if(started)
        {
            if(newActor) {
                createNewActor(line);
            }
            // Parse movies

            if(line.length() == 0) {
                finishActor();
            }
        }
        else if(line.equals("----\t\t\t------")) {
            started = true;
            newActor = true;
        }

    }

    private void finishActor() {
        pw.println(currentLine);
        newActor = true;
    }

    private void createNewActor(String line) {
        String fullName = line.split("\t")[0];
        System.out.println(fullName);
        currentLine = fullName;
        newActor = false;
    }
}
