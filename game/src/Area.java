import java.util.ArrayList;
import java.util.Random;

public class Area {
    Random random = new Random();
    ArrayList<Player> players;
    ArrayList<Integer> counterFieldPerPlayer;
    ArrayList<ArrayList<Field>> fields;
    ArrayList<Integer> dicesCounter;
    int playerArraySize;
    int numberOfNpcs;
    int rows = 5;
    int columns = 6;
    String playerName;


    public Area(int numberOfNpcs, String playerName) {
        this.numberOfNpcs = numberOfNpcs;
        this.playerName = playerName;

        arrayOfPlayers();
        setFields();
        distributingDices();
        battle();
    }

    private void arrayOfPlayers() {
        players = new ArrayList<Player>();
        counterFieldPerPlayer = new ArrayList<>();
        dicesCounter = new ArrayList<>();
        Player playableCharacter = new PlayableCharacter(playerName, rows, columns);
        for (int i = 0; i < numberOfNpcs + 1; i++) {
            counterFieldPerPlayer.add(0); //because in every place we must have 1 dice.(line 56)
            dicesCounter.add(20); //we need 20more dices.
            if (i == 0) {
                players.add(playableCharacter);
            } else {
                Player npc = new Npc("ID0" + i, rows, columns); //we need rows and columns to attack.
                players.add(npc);
            }
        }
    }

    private void setFields() {
        int areaSize = 30;
        playerArraySize = players.size();
        int fieldPerPlayer = areaSize / playerArraySize;
        fields = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Field> innerList = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                int tempPlayerIndex;
                do {
                    tempPlayerIndex = random.nextInt(playerArraySize) + 0;
                } while (counterFieldPerPlayer.get(tempPlayerIndex) == fieldPerPlayer);
                int currentfield = counterFieldPerPlayer.get(tempPlayerIndex);
                counterFieldPerPlayer.set(tempPlayerIndex, currentfield + 1);
                int playerIndex = tempPlayerIndex;
                Field field = new Field(players.get(playerIndex), playerIndex, i, j, 1);//each field has 1 dice
                innerList.add(field);
            }
            fields.add(innerList);
        }
    }

    private void distributingDices() {
        int playerId;
        int dicesToBeAdded;
        int currentDicesinField;
        int currentDicesNeedToDevidePerPlayer;
        boolean doAgain;
        do {
            doAgain = false;
            int diceleft = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    playerId = fields.get(i).get(j).getPlayerIndex();
                    dicesToBeAdded = random.nextInt(3) + 0; //with this has more chance to get more fields who has coordinates at the very back
                    currentDicesinField = fields.get(i).get(j).getDices();
                    currentDicesNeedToDevidePerPlayer = dicesCounter.get(playerId);
                    if ((currentDicesNeedToDevidePerPlayer - dicesToBeAdded) >= 0 && (currentDicesinField + dicesToBeAdded) < 8) {
                        dicesCounter.set(playerId, currentDicesNeedToDevidePerPlayer - dicesToBeAdded);
                        fields.get(i).get(j).setDices(dicesToBeAdded);
                    }
                }
            }
            for (int i = 0; i < dicesCounter.size(); i++) {
                diceleft += dicesCounter.get(i);
            }
            if (diceleft != 0) {
                doAgain = true;
            }
        } while (doAgain);
    }

    private void battle() {
        Player currentPlayer;
        int round = 0;
        while (players.size() != 1) {
            round++;
            System.out.println("Round: " + round);
            for (int i = 0; i < players.size(); i++) {
                currentPlayer = players.get(i);
                if (currentPlayer instanceof Npc npc) {
                    npc.fight(fieldThatPlayerOwn(i), fields, players);
                }
                if (currentPlayer instanceof  PlayableCharacter playableCharacter){
                    playableCharacter.fight(fieldThatPlayerOwn(i), fields, players);
                }
            }
        }
        System.out.println("Congratulation for " + players.get(0).ID + " to winning the game.");
        System.out.println(fields);
    }

    private ArrayList<Field> fieldThatPlayerOwn(int index){
        ArrayList<Field> fields1 = new ArrayList<>();
        fields.forEach((innerList) -> innerList.forEach((field) -> {if(players.get(index).ID.equals(field.getPlayerID())){fields1.add(field);}}));
        return fields1;
    }
}