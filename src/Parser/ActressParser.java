package Parser;

/**
 * Created by jorn on 12/22/16.
 */
public class ActressParser extends BaseActorParser {
    private static ActressParser instance;

    static ActressParser getInstance() {
        if (instance == null) instance = new ActressParser();
        return instance;
    }

    private ActressParser() {
        filename = "actresses";
        pw = createPrintWriter();
    }
}
