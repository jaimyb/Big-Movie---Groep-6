package SQL;

import java.util.Objects;

/**
 * Created by Valesco on 1/16/2017.
 */
public class ActorSQL extends SQL {
    private static ActorSQL instance;
    private boolean started = false;

    static ActorSQL getInstance() {
        if (instance == null) instance = new ActorSQL();
        return instance;
    }

    private ActorSQL() {
        filename = "actors";
    }

}
