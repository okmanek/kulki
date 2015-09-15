package okienka;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import static okienka.MyFrame.buttons;
import static okienka.Sound.clickSound;
//import static okienka.saveGame.saveGame;

/*
TODO:
-podstawy animacji: przed zniknieciem pole sie zaswieca czy cos
-delay
*/
 
public final class MyFrame extends JFrame implements ActionListener {
    JPanel jPanelButtons    =   new JPanel();
    JPanel jPanelTop        =   new JPanel();
    JPanel jPanelBottom     =   new JPanel();
    public static JButton[][] buttons = new JButton[9][9];      // !!!
    
    public boolean notFinished = true;

    public JButton[] nextColors = new JButton[3];
 
    public String textOfSelectedButton;
    public int turn              =   0;
    public int score             =   0;
    public static int howManyColors     =   7;
    public static int howManyOnStart    =   5;
    public final static int howManyEachTurn   =   3;
    
    public static Font myFont = new Font("Serif", Font.BOLD, 33);
    public static Font mySmallerFont = new Font("Serif", Font.BOLD, 18);
    
    public boolean isSelected = false;
    
    static Gracz newPlayer = new Gracz();
    
    public JLabel statusBottom  =   new JLabel("Wybierz kulkę");                // !!!
    public JLabel statusTop     =   new JLabel("Wybierz kulkę");
    
    public JButton x = new JButton("restart");

    
    //------------------GLOWNE OKIENKO------------//
    public MyFrame() {
        super("Kulki v 1.0");    // Jestes zalogowany jako: "+newPlayer.getname()
        //tekst.append(licznik + ". ruch wykonuje O  (" + nowy.getname2() +")\n");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(666, 755);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        
        createUI();
        newColors();
        
        //Menu menu = new Menu();
        //menu.setVisible(true);
    }
    
    public void createUI()
    {
        //------menu------//
        JMenuBar menubar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            fileMenu.setMnemonic(KeyEvent.VK_F);
        //JMenu imp = new JMenu("Load");
        //imp.setMnemonic(KeyEvent.VK_M);
        JMenuItem fileNew = new JMenuItem("New");
        fileNew.setMnemonic(KeyEvent.VK_N);
            JMenuItem fileSave = new JMenuItem("Save");
            fileNew.setMnemonic(KeyEvent.VK_O);
        JMenuItem fileLoad = new JMenuItem("Load");
        fileSave.setMnemonic(KeyEvent.VK_S);

        JMenuItem fileExit = new JMenuItem("Exit");
        fileExit.setMnemonic(KeyEvent.VK_C);
        fileExit.setToolTipText("Exit application");
        fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
            ActionEvent.CTRL_MASK));
        
        //---new---//
        fileNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                newGame(howManyOnStart);
            }
            
        });
        
        //---save---//
        fileSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    saveGame();
                    statusBottom.setText("zapisano stan gry");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //---load---//
        fileLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    loadGame();
                    statusBottom.setText("wczytano stan gry");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //---exit---//
        fileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        
        fileMenu.add(fileNew);      // file
        fileMenu.add(fileLoad);     // open
        fileMenu.add(fileSave);     // save
        fileMenu.addSeparator();    // ____
        fileMenu.addSeparator();    // ____
        fileMenu.add(fileExit);     // exit

        menubar.add(fileMenu);

        setJMenuBar(menubar);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //------tworzenie paneli------//
        jPanelTop.setBounds(0, 0, 660, 40);
        add(jPanelTop, BorderLayout.CENTER);
         
        //jPanelTop.add(statusTop);
        jPanelTop.add(statusTop);
        
        //------tworzenie buttonow sluzacych do wyswietlania nastepnych kolorow
        for(int i=0; i<3; i++)
        {
            nextColors[i] = new JButton("");
            jPanelTop.add(nextColors[i]);
        }

        /*
        JButton colorButton1 = new JButton("");
        JButton colorButton2 = new JButton("");
        JButton colorButton3 = new JButton("");
        
        jPanelTop.add(colorButton1);
        jPanelTop.add(colorButton2);
        jPanelTop.add(colorButton3);
        */
        
        //--------------------tworzy panel srodkowy i 81 buttonow--------//
        jPanelButtons.setBounds(30, 45, 600, 600);
        jPanelButtons.setLayout(new GridLayout(0, 9));
        add(jPanelButtons, BorderLayout.CENTER);
        
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                buttons[i][j]   =   new JButton(" ");
                buttons[i][j].setFont(mySmallerFont);
                buttons[i][j].addActionListener(this);      // !!!
                jPanelButtons.add(buttons[i][j]);
            }
                    
            jPanelButtons.revalidate();
            jPanelButtons.repaint();
        }
        
        //------tworzy status bar (na dole)-------//
        jPanelBottom.setBounds(0, 680, 660, 40);
        add(jPanelBottom, BorderLayout.CENTER);
        jPanelBottom.add(statusBottom);
        statusBottom.setText("Wybierz nowa gre by zagrac");

    }
    
    //-------czysci tlo wszystkich przyciskow------//
    void clearBackground()
    {
        for (int i=0; i<9 ; i++)
            for (int j=0; j<9 ; j++)
                buttons[i][j].setBackground(null);
    }
    
    //-------------nowa gra-----------//
    public void newGame(int value)
    {
        newTurn(value);
        statusBottom.setText("Wybierz kulkę");
        statusTop.setText("punkty: " + score + ", tura: " + turn + "||| Następne 3 kulki:");
    }
    
    //-------wypisz plansze, w celach testowych------//
    public void showMatrix()
    {
        for (int i=0; i<9 ; i++)
        {
            for (int j=0; j<9 ; j++)
                System.out.println(buttons[i][j].getText());
            //System.out.println("\n");
        }
            
    }

    //----------co sie dzieje podczas nowej tury-------//
    public void newTurn(int howMany)
    {                        
        //showMatrix();
        ////////////
        turn++;
        statusTop.setText("punkty: " + score + ", tura: " + turn + "||| Następne 3 kulki:");
        //jPanelTop.
                //add(new JLabel("punkty: " + score + ", tura: " + turn + "||| Następne 3 kulki:"));
        //Sound.clickSound.play();
        
        Random generator = new Random();
        int tmpX = 0, tmpY = 0;
        String tmp;
        int tmpNumber;
        
        for(int i=0; i<3; i++)
        {
            do          // szukamy wolnego pola w macierzy
            {
                tmpX = generator.nextInt(9);        // 0-8
                tmpY = generator.nextInt(9);        // 0-8
            } while(buttons[tmpX][tmpY].getText() != " ");
            
            tmp = nextColors[i].getText();        // ponoc error
            buttons[tmpX][tmpY].setText(tmp);
            buttons[tmpX][tmpY].setFont(myFont);
            //(Integer.parseInt(nextColors[i].getText())
            tmpNumber = Integer.parseInt(nextColors[i].getText());
            buttons[tmpX][tmpY].setForeground(IntToColor(tmpNumber));   // ustawianie odpowiedniego koloru
            
            
            /*
            int tmpNumber = generator.nextInt(7);               // losujemy liczbe i dodajemy do tablicy
            String tmpString = Integer.toString(tmpNumber);
            buttons[tmpX][tmpY].setText(tmpString);
            buttons[tmpX][tmpY].setFont(myFont);
            buttons[tmpX][tmpY].setForeground(IntToColor(tmpNumber));   // ustawianie odpowiedniego koloru
            //Thread.sleep(4000);*/
        }
    }
    
    //--------losuje 3 nowe kolory ktore za ture zostana dodane, pokazuje je na buttonach na gorze i zapisuje w tablicy--------//
    void newColors()
    {
        Random generator = new Random();
        int tmpX = 0, tmpY = 0;
        int tmp;
        
        for(int i=0; i<3; i++)
        {
            tmp = generator.nextInt(7);   //0-6
            nextColors[i].setText(Integer.toString(tmp));
            nextColors[i].setForeground(IntToColor(tmp));
            nextColors[i].setText(Integer.toString(tmp));
        }
    }

        //---------ustawia kolor przycisku na podstawie wylosowanej wart.-----------//
    void setColor(int x, int y, int value)
    {
        switch(value)
        {
            case 0: buttons[x][y].setForeground(Color.red);
                    break;
            case 1: buttons[x][y].setForeground(Color.blue);
                    break;
            case 2: buttons[x][y].setForeground(Color.LIGHT_GRAY);
                    break;
            case 3: buttons[x][y].setForeground(Color.CYAN);
                    break;
            case 4: buttons[x][y].setForeground(Color.yellow);
                    break;
            case 5: buttons[x][y].setForeground(Color.black);
                    break;
            case 6: buttons[x][y].setForeground(Color.green);
                    break;
        }   
    }
    
    /*
    public static final Color colors[] = { Color.red, Color.blue, Color.yellow, 
                                       Color.cyan, Color.magenta, Color.blue,
				       Color.green };
    */
    
    //----zamienia liczbe na przypisany mu kolor----//
    Color IntToColor(int value)
    {
        switch(value)
        {
            case 0: return Color.red;
            case 1: return Color.blue;
            case 2: return Color.LIGHT_GRAY;
            case 3: return Color.CYAN;
            case 4: return Color.yellow;
            case 5: return Color.black;
            case 6: return Color.green;
        }
        return Color.red;
    }
    
    

    //-------save------//
    public void saveGame() throws FileNotFoundException
    {
        PrintWriter save = new PrintWriter("save.txt");
        String tmp;
        
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                tmp = buttons[i][j].getText();
                System.out.println(tmp);
                save.print(tmp);
            }
            save.print("\n");
        }
        
        save.close();
        System.out.println("zapisano");     // diagnostyka
    }
    
    //-------load------//
    public void loadGame() throws FileNotFoundException
    {
        File load = new File("save.txt");
        Scanner w = new Scanner(load);
        
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                int b = w.nextInt();
                //buttons[i][j].setText();
                //setColor(i, j, b);
            }
        }
        
    }
    
    //---------tu dzieje sie magia... akcje po kliknieciu na przyciski-----------//
    @Override
    public void actionPerformed(ActionEvent ae) {
        //-------------dodac pozostale 2 akcje: odznaczanie, i jesli nie klikniesz w nic
        if(Test.gameOver == false)
        {
            String text = ((JButton)ae.getSource()).getText();
            if(!isSelected)
            {
                if(text != " ")
                {
                    textOfSelectedButton = text;
                    isSelected = true;
                    statusBottom.setText("wybrales kulke, gdzie mam ja przeniesc?");
                    ((JButton)ae.getSource()).setBackground(Color.pink);    // zaznaczenie
                    ((JButton)ae.getSource()).setText(" ");
                }
            }
            else
                if(text == " ")
                    {
                        ((JButton)ae.getSource()).setText(textOfSelectedButton);
                        isSelected = false;
                        statusBottom.setText("wybierz kolejna kulke...");
                        ((JButton)ae.getSource()).setFont(myFont);
                    
                        //---ustawienie koloru buttona
                        int value = Integer.parseInt(textOfSelectedButton);
                        ((JButton)ae.getSource()).setForeground(IntToColor(value));
                        
                        //---test czy nalezy skasowac
                        
                        //Test.isLost();
                        
                        newTurn(howManyEachTurn);
                        newColors();    // zmiana na panelu na gorze pokazujacy nadchodzace kolory
                        clearBackground();
                        Test.testuj();
                    }
        }    
        else
        {
            statusBottom.setText("przegrales");
            // ...
        }
    }


}