package SQL;

/**
 * Created by Valesco on 1/16/2017.
 */
public class Main {
    private static SQL[] sql_scripts = new SQL[] {
            ActorSQL.getInstance()
    };

    public static void main(String[] args) {
        long tStart = System.currentTimeMillis();
        System.out.println("lol");

        for (SQL p : sql_scripts) {
            long tJobStart = System.currentTimeMillis();
            p.parseFile();
            System.out.println("Took " + (System.currentTimeMillis() - tJobStart) / 1000 + " seconds");
        }

        System.out.println("Finished parsing files. Time elapsed: " + (System.currentTimeMillis() - tStart) / 1000);
    }

}
