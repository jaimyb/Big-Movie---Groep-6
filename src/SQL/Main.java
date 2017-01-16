package SQL;

/**
 * Created by Valesco on 1/16/2017.
 */
public class Main {
    private static BaseActorSQL[] baseActorSql_scripts = new BaseActorSQL[] {
            ActorBaseActorSQL.getInstance(),
            ActressBaseActorSQL.getInstance()
    };

    public static void main(String[] args) {
        long tStart = System.currentTimeMillis();

        for (BaseActorSQL p : baseActorSql_scripts) {
            long tJobStart = System.currentTimeMillis();
            p.parseFile();
            System.out.println("Took " + (System.currentTimeMillis() - tJobStart) / 1000 + " seconds");
        }

        System.out.println("Finished parsing files. Time elapsed: " + (System.currentTimeMillis() - tStart) / 1000);
    }

}
