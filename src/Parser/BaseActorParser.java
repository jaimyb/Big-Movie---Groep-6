package Parser;

/**
 * Created by jorn on 12/21/16.
 */
abstract class BaseActorParser extends Parser {
    private boolean started = false,
                    newActor = true;
    private String actorInfo = "";

    @Override
    void process(String line) {
        if(started)
        {
            // Check for end of list
            if(line.equals("-----------------------------------------------------------------------------")) {
                started = false;
                return;
            }

            if(line.length() == 0) {
                finishActor();
                return;
            }


            if(newActor) {
                createNewActor(line);
            }


            String[] splitTabs = line.split("\t");

            // Parse movies
            String movieInfo = MoviesParser.getInstance().parseMovieString(splitTabs[splitTabs.length - 1]);

            // Can't be parsed
            if(movieInfo.equals("")) {
                return;
            }

            writeToStream(actorInfo + movieInfo);
        }
        else if(line.equals("----\t\t\t------")) {
            started = true;
            newActor = true;
        }

    }

    private void finishActor() {
        newActor = true;
    }

    private void createNewActor(String line) {
        String fullName = line.split("\t")[0];

        String[] nameComponents = fullName.split(", ");
        String firstName = nameComponents[0];

        String lastName = "",
                idNumber = "";
        if(nameComponents.length > 1) {
            if(!nameComponents[1].contains("(")) {
                lastName = nameComponents[1];
            }
            else {
                lastName = partBefore(nameComponents[1], "(").trim();
                idNumber = partBetween(nameComponents[1], "(", ")");
            }
        }


        actorInfo = formatAsCSV(idNumber, firstName, lastName);

//        currentLine = fullName;
        newActor = false;
    }
}
