import java.util.ArrayList;
import java.util.Random;

public class Field {
    private int dices;
    private String playerID;
    private ArrayList<Integer> coordinate;

    private int playerIndex;

    public Field(Player player, int playerIndex, int rows, int columns, int dices){
        this.coordinate = new ArrayList<>();
        coordinate.add(rows);
        coordinate.add(columns);
        this.dices = dices;
        this.playerID = player.ID;
        this.playerIndex = playerIndex;
    }



    public void setDices(int dices){ //for setting fields
        this.dices += dices;
    }

    public void setDicesAfterFight(int dices){ //with we can set dices after fight
        this.dices = dices;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public String getPlayerID() {
        return playerID;
    }

    public ArrayList<Integer> getCoordinate() {
        return coordinate;
    }

    public int getDices() {
        return dices;
    }

    @Override
    public String toString() {
        return String.format("(%s %s {%d})",coordinate,playerID,dices);
    }
}
