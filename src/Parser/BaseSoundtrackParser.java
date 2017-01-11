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
    private String movieParsed;
    private String songParsed;
    
    
    @Override
    void process(String line)
    { 
        if(line != null)
        {   
            if(line.equals("")) return;
            
            if("#".equals(line.substring(0,1)) && line.length() > 0)
            {
                String movie = line.substring(2);
                movieParsed = MoviesParser.getInstance().parseMovieString(movie);
            }
                  
            if("-".equals(line.substring(0,1)) && line.length() >  0 && !line.equals("-----------------------------------------------------------------------------") && line.contains("\""))
            {
                newSong(line);
            }
             
            writeToStream(movieParsed + songParsed);
        }
    }
        
    
    void newSong(String line)
    {
        System.out.println(line);
//        String songTitle = line.substring(line.indexOf('"')+1,line.lastIndexOf('"'));
        String songTitle = partBetween(line, "\"");
        
        songParsed = formatAsCSV(songTitle);
    }
    
}
