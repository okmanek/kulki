package okienka;

import java.io.*;
import java.applet.*;
import java.net.*;

public class Sound {
    public static AudioClip clickSound;
    
    static
    {
        try
        {
            clickSound = Applet.newAudioClip(new File("src/sounds/sonar.wav").toURI().toURL());
        }
        catch(MalformedURLException mue) {}   
    }   
}