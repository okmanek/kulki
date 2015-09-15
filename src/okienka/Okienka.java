package okienka;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Okienka {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyFrame();
                //Baza jdbc = new Baza();
		//jdbc.insert("xxx", "123");
            }
        });
}
}