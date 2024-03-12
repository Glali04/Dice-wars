import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Npc extends Player {
    public Npc(String ID, int rows, int columns) {
        this.ID = ID;
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    void fight(ArrayList<Field> fieldsThatPlayerOwn, ArrayList<ArrayList<Field>> allFields, ArrayList<Player> players){
        Random random = new Random();
        int numberOfFieldsThatOwn;
        while(true){
            numberOfFieldsThatOwn = fieldsThatPlayerOwn.size();
            if (numberOfFieldsThatOwn == 0){//clearly if we do not have fields we can not fight
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).ID.equals(ID)){
                        players.remove(i);
                    }
                }
                break;
            }
            Collections.sort(fieldsThatPlayerOwn, Comparator.comparingInt(Field::getDices).reversed());
            if(fieldsThatPlayerOwn.get(0).getDices() < 4){ //if the biggest "field" is less than 4 in this round we will skip fight
                break;
            }
            Field tempAttackingField = null;
            ArrayList<Field> oppsWeCanAttack = null;
            for (int i = 0; i < numberOfFieldsThatOwn; i++) {
                tempAttackingField = fieldsThatPlayerOwn.get(i);
                oppsWeCanAttack = opponentsWeCanAttack(tempAttackingField, allFields);
                if(!oppsWeCanAttack.isEmpty()){
                    break;
                }
            }
            Collections.sort(oppsWeCanAttack, Comparator.comparingInt(Field::getDices));
            Field tempDefendingOpponent = oppsWeCanAttack.get(0);
            if (tempAttackingField.getDices() < tempDefendingOpponent.getDices()) {
                break;
            }
            battle(fieldsThatPlayerOwn,tempAttackingField,tempDefendingOpponent);
        }
        distributindDicesAfterRound(fieldsThatPlayerOwn);
    }
}
