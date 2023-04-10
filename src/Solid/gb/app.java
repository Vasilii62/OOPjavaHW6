package Solid.gb;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class app {

    static Scanner sc = new Scanner(System.in);
    public Integer N = null;
    public Integer M = null;

    public static void main(String[] args) {

        System.out.println("Введите команду <create-map X Y > для создания карты:");
        RobotMap map = null;
        while (true) {
            String command = sc.nextLine();
            if (command.startsWith("create-map")) {
                String[] split = command.split(" "); // [create-map 3 5]
                String[] arguments = Arrays.copyOfRange(split, 1, split.length); // [3 5]

                try {
                    map = new RobotMap(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]));
                    System.out.println("Карта создана!");
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("При создании карты возникло исключение: " + e.getMessage() + "." +
                            " Попробуйте еще раз");
                }
            } else {
                System.out.println("Команда не найдена. Попробуйте еще раз");
            }
        }

        List<CommandHandler> handlers = List.of(
                new ChangeDirectionCommandHandler(),
                new CreateRobotCommandHandler(),
                new MoveRobotCommandHandler()
        );
        CommandManager commandManager = new CommandManager(map, handlers);

        System.out.println("ИГРАЕМ...");
        System.out.println("Список допустимых команд: ...\n\tcreate-robot X Y ,\n\tmove-robot ID,\n\tchange-direction ID DIRECTION");

        while (true) {
            String command = sc.nextLine();
            System.out.println("\tВведена команда : " + command);
            commandManager.handleCommand(command);
        }

    }

}