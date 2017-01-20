/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

/**
 * @author JaimyBeima
 */
class SoundtrackParser extends Parser {
    private static SoundtrackParser instance;

    static SoundtrackParser getInstance() {
        if (instance == null) instance = new SoundtrackParser();
        return instance;
    }

    private SoundtrackParser() {
        filename = "soundtracks";
        pw = createPrintWriter();
        pw.println("title,firstYear,seriesTitle,seriesSeason,seriesEpisode,song");
    }

    //Variables
    private String movieParsed;
    private String song;


    @Override
    void process(String line) {
        if (line != null) {
            if (line.equals("")) return;

            if ("#".equals(line.substring(0, 1)) && line.length() > 0) {
                String movie = line.substring(2);
                movieParsed = MoviesParser.getInstance().parseMovieString(movie);
            } else if ("-".equals(line.substring(0, 1)) && line.length() > 0 &&
                    !line.equals("-----------------------------------------------------------------------------") &&
                    line.contains("\"") &&
                    !movieParsed.equals("")) {
                newSong(line);
                if(song.equals("")) {
                    return;
                }
                writeToStream(movieParsed + formatAsCSV(wrapInQuotes(song)));
            }
        }
    }


    private void newSong(String line) {
        String songTitle = partBetween(line, "\"");

        song = songTitle;
    }

}
