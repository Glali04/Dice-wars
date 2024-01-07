import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playerName = gettingThePlayerName(scanner);
        PlayableCharacter playableCharacter = new PlayableCharacter(playerName);
        System.out.println(numbOfNpc(scanner, playerName));
    }

    private static String gettingThePlayerName(Scanner scanner) {
        String name;
        do {
            System.out.println("Please tell me what is your name: ");
            name = scanner.nextLine();
        } while (name.isEmpty());
        return name;
    }

    private static int numbOfNpc(Scanner scanner, String name) {
        System.out.printf("Hello %s. Now please tell me, versus how many players you want to play? (1-4) \n", name);
        int numberOfBots = 0;
        boolean gotIntoCatchBlok;
        while (1 > numberOfBots || numberOfBots > 4) {
            try {
                gotIntoCatchBlok = false;
                numberOfBots = scanner.nextInt();
            } catch (InputMismatchException e1) {
                System.out.println("Type in an integer!!");
                scanner.nextLine();
                gotIntoCatchBlok = true;
            }
            /*with catch block we will know did the user give us an integer, if yes in the if statement we will check
            did that number inside the boundaries.*/
            if (!gotIntoCatchBlok && (1 > numberOfBots || numberOfBots > 4)){
                System.out.println("Give me a number within specified boundaries");
                gotIntoCatchBlok = false;
            }
        }
        return numberOfBots;
    }
}