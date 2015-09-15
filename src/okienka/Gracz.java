package okienka;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import static okienka.MyFrame.howManyOnStart;

public class Gracz extends JFrame {
    JLabel query = new JLabel("Podaj swoje imie: ");
    JTextField Playersname = new JTextField();
    JButton startButton = new JButton("START");
    public static boolean start = false;
    
    //------kontruktor-----//
    public Gracz()
    {
        super("Witaj");
        setVisible(true);
        setSize(500, 175);
        setLayout(null);
        setResizable(false);
        
        query.setBounds(5, 5, 175, 175);
        Playersname.setBounds(150, 25, 150, 25);
        startButton.setSize(100, 25);
        startButton.setBounds(150, 125, 100, 25);
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                start = true;
                setVisible(false);
            }
        });
        
        add(query);
        add(Playersname);
        add(startButton);
        
        setBounds(200, 200, 400, 200);
    }

    
    public String getname()
    {
        return Playersname.getText();
    }
    
}
