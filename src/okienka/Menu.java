package okienka;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import static okienka.MyFrame.buttons;

public class Menu extends JFrame {
    
    public Menu()
    {

        JMenuBar menubar = new JMenuBar();
JMenu file = new JMenu("File");
file.setMnemonic(KeyEvent.VK_F);
        //JMenu imp = new JMenu("Load");
        //imp.setMnemonic(KeyEvent.VK_M);
        JMenuItem fileNew = new JMenuItem("New");
        fileNew.setMnemonic(KeyEvent.VK_N);
            JMenuItem fileOpen = new JMenuItem("Open");
            fileNew.setMnemonic(KeyEvent.VK_O);
        JMenuItem fileSave = new JMenuItem("Save");
        fileSave.setMnemonic(KeyEvent.VK_S);

        JMenuItem fileExit = new JMenuItem("Exit");
        fileExit.setMnemonic(KeyEvent.VK_C);
        fileExit.setToolTipText("Exit application");
        fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
            ActionEvent.CTRL_MASK));

        fileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        file.add(fileNew);      // file
        file.add(fileOpen);     // open
        file.add(fileSave);     // save
        file.addSeparator();    // ____
        file.addSeparator();    // ____
        file.add(fileExit);     // exit

        menubar.add(file);

        super.setJMenuBar(menubar);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        
    }
}