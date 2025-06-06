package Player;
import java.awt.*;

public class Players {
    private int money;

    public Players(int money){
        this.money=money;
    }

    //public boolean checkSurvive(){}
    // when one enemy go past, you die
    public void drawMoneyAmount(Graphics g){
        int x=10;
        int y=25;
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial",Font.BOLD,18));
        g.drawString("Money:"+money,x,y);
    }
    
    public void drawMoney(Graphics g){
        drawMoney(g);
    }

    public void spendMoney(int amount){
        this.money-=amount;
    }

    public void earnMoney(int amount){
        this.money+=amount;
    }
}
