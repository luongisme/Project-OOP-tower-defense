package Player;
import java.awt.*;

public class Players {
    private int money;
    private static Players instance;

    private Players(int money){
        this.money=money;
    }

    public static Players getInstance(int money){
        if (instance == null){
            instance= new Players(money);
        }
        return instance;
    }

    public static Players getInstance() {
        return instance;
    }


    public void drawMoneyAmount(Graphics g){
        int x=9*64;
        int y=25;
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString("Money:"+money,x,y);
    }
    
    public void drawMoney(Graphics g){
        drawMoneyAmount(g);
    }

    public void spendMoney(int amount){
        this.money-=amount;
    }

    public void earnMoney(int amount){
        this.money+=amount;
    }
    public int getMoney(){
        return money;
    }
    
}
