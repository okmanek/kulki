package okienka;

import javax.swing.JButton;

// nie ma przypadku usuniecia 6 kulek, nie ma skosow

public class Test {
    
    public static JButton buttonz[][] = MyFrame.buttons;
    public static int score = 0;
    
    static int gameOverCounter;
    static boolean gameOver = false;
    
    //-----------------poziom-----------//
    public static void testuj()
    {
        
        for(int i = 0; i< 9; i++)
            for(int j = 0; j < 5 ; j++)
            {
                if(buttonz[i][j].getText().equals(buttonz[i][j+1].getText())
                    &&
                buttonz[i][j+1].getText().equals(buttonz[i][j+2].getText())
                    &&
                buttonz[i][j+2].getText().equals(buttonz[i][j+3].getText())
                    &&
                buttonz[i][j+3].getText().equals(buttonz[i][j+4].getText())
                    &&
                buttonz[i][j+4].getText() != " ")
                {
                    score += 5;
                    System.out.println(score + " punktow");
                    //--------------------//
                    buttonz[i][j].setText(" ");
                    buttonz[i][j+1].setText(" ");
                    buttonz[i][j+2].setText(" ");
                    buttonz[i][j+3].setText(" ");
                    buttonz[i][j+4].setText(" ");                
                }
                
                
            }
        
        //------------pion--------//
        for (int i = 0; i <5 ; i++)
            for (int j = 0; j < 9; j++)
            {
                if(buttonz[i][j].getText().equals(buttonz[i+1][j].getText())
                    &&
                buttonz[i+1][j].getText().equals(buttonz[i+2][j].getText())
                    &&
                buttonz[i+2][j].getText().equals(buttonz[i+3][j].getText())
                    &&
                buttonz[i+3][j].getText().equals(buttonz[i+4][j].getText())
                    &&
                buttonz[i+4][j].getText() != " ")
                {
                    score += 5;
                    System.out.println(score);
                    //----------------//
                    buttonz[i][j].setText(" ");
                    buttonz[i+1][j].setText(" ");
                    buttonz[i+2][j].setText(" ");
                    buttonz[i+3][j].setText(" ");
                    buttonz[i+4][j].setText(" ");     
                }
                
            }
        isLost();
    }
    
    static void isLost()
    {
        // mozna poprawic
        gameOverCounter = 0;
        
        for(int i=0; i<9; i++)
            for(int j=0; j<9; j++)
                if(buttonz[i][j].getText() == "")
                    gameOverCounter++;
        if(gameOverCounter >4 )
            gameOver = true;
    }
    
}