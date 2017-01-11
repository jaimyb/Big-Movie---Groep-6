/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

/**
 *
 * @author JaimyBeima
 */
public abstract class BaseSoundtrackParser extends Parser
{
    //Variables
    private boolean started;
    private String movieParsed;
    private String songParsed;
    
    
    @Override
    void process(String line)
    {
        if(started && line != null)
        {   
            if(line.charAt(0) == '#' && line.length() > 0)
            {
                String movie = line.substring(2);
                movieParsed = MoviesParser.getInstance().parseMovieString(movie);
            }
                  
            if(line.charAt(0) == '-' && line.length() >  0 && !line.equals("-----------------------------------------------------------------------------"))
            {
                newSong(line);
            }
            
            writeToStream(movieParsed + songParsed);
        }
    }
        
    
    
    void newSong(String line)
    {
        String songTitle = line.substring(line.indexOf('"')+1,line.lastIndexOf('"'));
        
        songParsed = formatAsCSV(songTitle);
    }
    
}
