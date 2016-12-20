package Parser;

/**
 * Created by jorn on 12/19/16.
 */
class MoviesParser extends Parser {
    private static MoviesParser instance;

    static MoviesParser getInstance() {
        if(instance == null) instance = new MoviesParser();
        return instance;
    }

    void parseFile() {
        readFile("input/movies.list").map(this::process).forEach(System.out::println);
    }

    private String process(String line) {
        if(line.length() == 0 || line.charAt(0) != '"') return "";
        String title = partBetween(line, "\"");
        String firstYear = partBetween(line, "(", ")");
        String seriesInfo = partBetween(line, "{", "}");

        String seriesTitle = allButLastWord(seriesInfo);
        String seriesNumber = partBetween(seriesInfo, "(", ")");

        String secondYear = lastWord(line);
        return String.format("%s, %s, %s, %s, %s", title, firstYear, seriesTitle, seriesNumber, secondYear);
    }
}