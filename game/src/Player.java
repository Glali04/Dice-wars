import java.util.*;

public abstract class Player {
    Random random = new Random();
    String ID;
    int rows;
    int columns;

    abstract void fight(ArrayList<Field> fieldsThatPlayerOwn, ArrayList<ArrayList<Field>> allFields, ArrayList<Player> players);

    protected ArrayList<Field> opponentsWeCanAttack(Field field, ArrayList<ArrayList<Field>> allFields) { //the fields we can attack
        int row_index = field.getCoordinate().get(0);
        int column_index = field.getCoordinate().get(1);
        ArrayList<Field> possibleFieldsToAttack = new ArrayList<>();;
        if (column_index == this.columns-1) { //the attacker is on the right side
            if (row_index == 0) { // the attacker is on the top right side
                for (int i = 0; i < row_index + 2; i++) { //the attacker is on the top right side
                    for (int j = column_index - 2; j < columns; j++) {
                        if((!field.getPlayerID().equals(allFields.get(i).get(j).getPlayerID())) && (!field.getCoordinate().equals(tempCoordinates(i,j)))) {
                            //we will add that element if we do not have that filed and it does not on the same coordinate
                            possibleFieldsToAttack.add(allFields.get(i).get(j));
                        }
                    }
                }
                return possibleFieldsToAttack;
            } else if (row_index == this.rows-1) { //the attacker is on the bottom right side
                for (int i = rows-2; i < rows; i++) {
                    for (int j = column_index - 2; j < columns; j++) {
                        if((!field.getPlayerID().equals(allFields.get(i).get(j).getPlayerID())) && (!field.getCoordinate().equals(tempCoordinates(i,j)))) {
                            //we will add that element if we do not have that filed and it does not on the same coordinate
                            possibleFieldsToAttack.add(allFields.get(i).get(j));
                        }
                    }
                }
                return possibleFieldsToAttack;
            }else{ //the attacker is between bottom and top somewhere(right)
                for (int i = row_index-1; i < row_index+2; i++) {
                    for (int j = column_index - 2; j < columns; j++){
                        if((!field.getPlayerID().equals(allFields.get(i).get(j).getPlayerID())) && (!field.getCoordinate().equals(tempCoordinates(i,j)))) {
                            //we will add that element if we do not have that filed and it does not on the same coordinate
                            possibleFieldsToAttack.add(allFields.get(i).get(j));
                        }
                    }
                }
                return possibleFieldsToAttack;
            }
        }else if (column_index == 0) { //the attacker is on the left side
            if(row_index == 0){ //the attacker is on the top left side
                for (int i = 0; i < row_index + 2; i++ ){
                    for (int j = 0; j < column_index + 2; j++) {
                        if((!field.getPlayerID().equals(allFields.get(i).get(j).getPlayerID())) && (!field.getCoordinate().equals(tempCoordinates(i,j)))) {
                            //we will add that element if we do not have that filed and it does not on the same coordinate
                            possibleFieldsToAttack.add(allFields.get(i).get(j));
                        }
                    }
                }
                return possibleFieldsToAttack;
            }else if(row_index == this.rows-1){//the attacker is on the bottom left side
                for (int i = row_index-2; i < row_index; i++) {
                    for (int j = 0; j < column_index + 2; j++) {
                        if((!field.getPlayerID().equals(allFields.get(i).get(j).getPlayerID())) && (!field.getCoordinate().equals(tempCoordinates(i,j)))) {
                            //we will add that element if we do not have that filed and it does not on the same coordinate
                            possibleFieldsToAttack.add(allFields.get(i).get(j));
                        }
                    }
                }
                return possibleFieldsToAttack;
            }else{//the attacker is between bottom and top somewhere(left)
                for (int i = row_index-1; i < row_index + 2; i++) {
                    for (int j = 0; j < column_index + 2; j++) {
                        if((!field.getPlayerID().equals(allFields.get(i).get(j).getPlayerID())) && (!field.getCoordinate().equals(tempCoordinates(i,j)))) {
                            //we will add that element if we do not have that filed and it does not on the same coordinate
                            possibleFieldsToAttack.add(allFields.get(i).get(j));
                        }
                    }
                }
                return possibleFieldsToAttack;
            }
        }else if (row_index == 0){ //the attacker is up somewhere
            for (int i = 0; i < row_index + 2; i++) {
                for (int j = column_index - 1; j < column_index + 2 ; j++) {
                    if((!field.getPlayerID().equals(allFields.get(i).get(j).getPlayerID())) && (!field.getCoordinate().equals(tempCoordinates(i,j)))) {
                        //we will add that element if we do not have that filed and it does not on the same coordinate
                        possibleFieldsToAttack.add(allFields.get(i).get(j));
                    }
                }
            }
            return possibleFieldsToAttack;
        } else if (row_index == rows-1) {
            for (int i = row_index - 1; i < rows; i++) {
                for (int j = column_index - 1; j < column_index + 2 ; j++) {
                    if((!field.getPlayerID().equals(allFields.get(i).get(j).getPlayerID())) && (!field.getCoordinate().equals(tempCoordinates(i,j)))) {
                        //we will add that element if we do not have that filed and it does not on the same coordinate
                        possibleFieldsToAttack.add(allFields.get(i).get(j));
                    }
                }
            }
            return possibleFieldsToAttack;
        }
        for (int i = row_index-1; i < row_index + 2; i++) {
            for (int j = column_index-1; j < column_index + 2; j++) {
                if((!field.getPlayerID().equals(allFields.get(i).get(j).getPlayerID())) && (!field.getCoordinate().equals(tempCoordinates(i,j)))) {
                    //we will add that element if we do not have that filed and it does not on the same coordinate
                    possibleFieldsToAttack.add(allFields.get(i).get(j));
                }
            }
        }
        return possibleFieldsToAttack;
    }

    protected void distributindDicesAfterRound(ArrayList<Field> fieldsThatPlayerOwn){
        int numberOfFieldsThatPlayerOwn = fieldsThatPlayerOwn.size();
        int dicesToDistribute = numberOfFieldsThatPlayerOwn / 2; //after each round player will get dices
        int randomField;
        int tempDicesInThatField;
        int dice;
        Field tempField;
        while (dicesToDistribute != 0){
            randomField = random.nextInt(numberOfFieldsThatPlayerOwn);
            tempField = fieldsThatPlayerOwn.get(randomField);
            tempDicesInThatField = tempField.getDices();
            dice = random.nextInt(2); // with this we have more chances to more field get some dices
            if ((tempDicesInThatField + dice <= 8) && (dicesToDistribute-dice >= 0)){
                tempField.setDices(dice);
                dicesToDistribute -= dice;
            }
            Collections.sort(fieldsThatPlayerOwn, Comparator.comparingInt(Field::getDices));
            if (fieldsThatPlayerOwn.get(0).getDices() == 8){ //if the smallest element has 8 dices too, we brake out because we do not have fields who can get dices
                break;
            }
        }
    }
    protected void battle(ArrayList<Field> fieldsThatAttackerHas, Field attacker, Field defender){
        int attackerScore = 0;
        int defenderScore = 0;
        int attackerDices = attacker.getDices();
        int defenderDices = defender.getDices();
        int diceRoll;
        int diceRolling;
        if(attackerDices > defenderDices){
            diceRoll = attackerDices;
        }else{
            diceRoll = defenderDices;
        }
        for (int j = diceRoll; j > 0; j--) {
            if(attackerDices != 0){
                diceRolling = random.nextInt(6) + 1;
                attackerDices -= 1;
                attackerScore += diceRolling;
            }
            if(defenderDices != 0) {
                diceRolling = random.nextInt(6) + 1;
                defenderDices -= 1;
                defenderScore += diceRolling;
            }
        }
        if (attackerScore > defenderScore){
            System.out.println("Attacker: " + attacker.getPlayerID() + " take the field from " + defender.getPlayerID());
            defender.setPlayerIndex(attacker.getPlayerIndex());
            defender.setPlayerID(attacker.getPlayerID());
            defender.setDicesAfterFight(attacker.getDices()-1);
            fieldsThatAttackerHas.add(defender);
        }
        attacker.setDicesAfterFight(1);
    }
    private ArrayList<Integer> tempCoordinates(int x, int y){ //we will check coordinates with
        ArrayList<Integer> coordinates = new ArrayList<Integer>();
        coordinates.add(x);
        coordinates.add(y);
        return coordinates;
    }
}