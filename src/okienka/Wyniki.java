package okienka;


public class Wyniki {
    private int     idnick;
    private String  nick;
    private String  points;
 
    //___gettery___//
            
    public int getId() {
        return idnick;
    }
    
    public String getNick() {
        return nick;
    }
    
    public String getPoints() {
        return points;
    }
    
    //___settery___//
    
    public void setId(int idnick) {
        this.idnick = idnick;
    }

    public void setNick(String nick) {
        this.nick=nick;
    }
    
    public void setPoints(String points) {
        this.points = points;
    }
    
    //___konstruktor___//
 
    public Wyniki() { }
    public Wyniki(int idnick, String nick, String points) {
        this.idnick = idnick;
        this.nick = nick;
        this.points = points;
    }
 
    @Override
    public String toString() {
        return "["+idnick+"] - "+nick+" - "+points;
    }
}