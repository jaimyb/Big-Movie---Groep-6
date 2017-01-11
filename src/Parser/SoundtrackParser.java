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
public class SoundtrackParser extends BaseSoundtrackParser
{
    private static SoundtrackParser instance;
    
    static SoundtrackParser getInstance()
    {
        if (instance == null) instance = new SoundtrackParser();
        return instance;
    }
    
    private SoundtrackParser() {
        filename = "soundtracks";
        pw = createPrintWriter();
    }
    
}
