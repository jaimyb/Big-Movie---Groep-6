package SQL;

/**
 * Created by Valesco on 1/16/2017.
 */
public class ActorBaseActorSQL extends BaseActorSQL {
    private static ActorBaseActorSQL instance;

    static ActorBaseActorSQL getInstance() {
        if (instance == null) instance = new ActorBaseActorSQL();
        return instance;
    }

    private ActorBaseActorSQL() {
        filename = "actors";
    }

}
