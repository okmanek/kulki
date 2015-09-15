package okienka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class Baza {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:biblioteka.db";
 
    private static Connection conn;
    private static Statement stat;
 
    public Baza() {
        try {
            Class.forName(Baza.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
 
        createTables();
    }
    
    public boolean createTables()  {
        String create = "CREATE TABLE IF NOT EXISTS ranking (idnick INTEGER PRIMARY KEY AUTOINCREMENT, nick varchar(255), points int)";
        try {
            stat.execute(create);
   
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean insert(String nick, String points) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into ranking values (NULL, ?, ?);");
            prepStmt.setString(1, nick);
            prepStmt.setString(2, points);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu czytelnika");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static List<Wyniki> select() {
        List<Wyniki> wyniki2 = new LinkedList<Wyniki>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM ranking");
            int idnick;
            String nick, points;
            while(result.next()) {
                idnick = result.getInt("idnick");
                nick = result.getString("nick");
                points = result.getString("points");
                wyniki2.add(new Wyniki(idnick, nick, points));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return wyniki2;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }
    
}
