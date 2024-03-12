import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playerName = setThePlayerName(scanner);
        int numberOfNpcs = setNumberOfNpcs(playerName); // we need playerName just to write a message.
        Area area = new Area(numberOfNpcs, playerName);
    }

    private static String setThePlayerName(Scanner scanner) {
        String name;
        do {
            System.out.println("Please tell me what is your name: ");
            name = scanner.nextLine();
        } while (name.isEmpty());
        return name;
    }

    private static int setNumberOfNpcs(String name) {
        System.out.printf("Hello %s. Now please tell me, versus how many players you want to play? (1-2) \n",
                name);
        return PlayableCharacter.checkTheInputNumber(1,2);
    }

}