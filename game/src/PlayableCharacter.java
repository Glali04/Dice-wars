import java.util.*;

public class PlayableCharacter extends Player{
    Scanner scanner = new Scanner(System.in);
    public PlayableCharacter(String name, int rows, int columns) {
        this.ID = name;
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    void fight(ArrayList<Field> fieldsThatPlayerOwn, ArrayList<ArrayList<Field>> allFields, ArrayList<Player> players) {
        System.out.println("Now it's your turn!!");
        int numberOfFieldsThatOwn;
        boolean wantToFight = true;
        while (wantToFight) {
            Field tempAttackingField = null;
            numberOfFieldsThatOwn = fieldsThatPlayerOwn.size();
            if (numberOfFieldsThatOwn == 0) {//clearly if we do not have fields we can not fight
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).ID.equals(ID)) {
                        players.remove(i);
                    }
                }
                System.out.println("Im sorry you lose :(, your place is: " + players.size() + 1);
                break;
            }
            Collections.sort(fieldsThatPlayerOwn, Comparator.comparingInt(Field::getDices).reversed());
            System.out.println(fieldsThatPlayerOwn);
            int xCoordinate;
            int yCoordinate;
            ArrayList<Field> oppsWeCanAttack = null;
            while (true) {
                ArrayList<Integer> coordinates = new ArrayList<>();
                System.out.println("First you need to give me the x coordinate for the field you want to attack others");
                xCoordinate = checkTheInputNumber(0, rows - 1); //first we check if the user add existing coordinate
                coordinates.add(xCoordinate); // we add the x coordinate to the Array
                System.out.println("Okay, now please give me the value of y coordinate");
                yCoordinate = checkTheInputNumber(0, columns - 1);
                coordinates.add(yCoordinate); // we add the y coordinate to the Array
                System.out.println("You attack with:");
                if (allFields.get(xCoordinate).get(yCoordinate).getPlayerID().equals(this.ID)) {
                    //if the field is equal with player id we can break out because we have the correct field from which he can attack.
                    tempAttackingField = allFields.get(xCoordinate).get(yCoordinate);
                    oppsWeCanAttack = opponentsWeCanAttack(tempAttackingField, allFields);
                    if (!oppsWeCanAttack.isEmpty()) {
                        break;
                    }
                    System.out.println("Please give me a new coordinate you can not attack from here, because you do not have enemy around you");
                }else {
                    System.out.println("Please try again you give as a coordinate from field which does not belongs to you");
                }
            } //it is safe, because we do not exit from inner loop, as long as the player does not input a correct inpur
            System.out.println(tempAttackingField);
            System.out.println("please tell me the index of the player you want to attack 0 - " + (oppsWeCanAttack.size()-1));
            System.out.println(oppsWeCanAttack);
            int enemeyIndex = checkTheInputNumber(0,oppsWeCanAttack.size()-1);
            Field enemy = oppsWeCanAttack.get(enemeyIndex);
            battle(fieldsThatPlayerOwn,tempAttackingField,enemy);
            System.out.println("Do you want to fight one more time? Y(es), N(o)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("N")){
                wantToFight = false;
            } else if (!(answer.equalsIgnoreCase("N")) || (answer.equalsIgnoreCase("Y"))) {
                while (true){
                    if((answer.equalsIgnoreCase("N")) || (answer.equalsIgnoreCase("Y"))){
                        break;
                    }
                    System.out.println("Please put in Y for yes or N for no");
                    answer = scanner.nextLine();
                }
            }
        }
        distributindDicesAfterRound(fieldsThatPlayerOwn);
    }
    public static int checkTheInputNumber(int lowerBound, int upperLimit) {
        Scanner scanner = new Scanner(System.in);
        int numberWeLookFor = -1;
        boolean gotIntoCatchBlok;
        while (lowerBound > numberWeLookFor || numberWeLookFor > upperLimit) {
            try {
                gotIntoCatchBlok = false;
                numberWeLookFor = scanner.nextInt();
            } catch (InputMismatchException e1) {
                System.out.println("Type in an integer!!");
                scanner.nextLine();
                gotIntoCatchBlok = true;
            }
            /*with catch block we will know did the user give us an integer, if yes in the if statement we will check
            did that number inside the boundaries.*/
            if (!gotIntoCatchBlok && (lowerBound > numberWeLookFor || numberWeLookFor > upperLimit)) {
                System.out.println("Give me a number within specified boundaries");
            }
        }
        return numberWeLookFor;
    }
}
