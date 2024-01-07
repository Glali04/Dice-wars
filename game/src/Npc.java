public class Npc {
    int numbOfPlayers;
    String[] npcID;

    public Npc(int numbOfPlayers){
        this.numbOfPlayers = numbOfPlayers;
    }

    private String[] givingIDsForNpcs(){
        for (int i = 0; i < this.numbOfPlayers; i++) {
            npcID = new String[numbOfPlayers];
            npcID[i] = "ID0" + i + 1;
        }
        return npcID;
    }
}
