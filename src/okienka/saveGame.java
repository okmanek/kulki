/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package okienka;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static okienka.MyFrame.buttons;

public class saveGame {
    
    
    //----------------save game-------------//
    public static void saveGame() throws FileNotFoundException
    {
        // wyjatek, jesli nie znajdzie pliku:
        try
        {
            PrintWriter save = new PrintWriter("save.txt");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("nie ma pliku");
        }
        
        // jesli znajdzie plik:
        PrintWriter save = new PrintWriter("save.txt");
        
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                String tmp = buttons[i][j].getText();
                save.print(tmp);
            }
            save.print("\n");
        }
        //save.print(counter);      // !!!
        save.close();
            
            
    }
    
}
