package SQL;

/**
 * Created by Valesco on 1/16/2017.
 */
public class ActressBaseActorSQL extends BaseActorSQL {
    private static ActressBaseActorSQL instance;

    static ActressBaseActorSQL getInstance() {
        if (instance == null) instance = new ActressBaseActorSQL();
        return instance;
    }

    private ActressBaseActorSQL() {
        filename = "actors";
    }

}
