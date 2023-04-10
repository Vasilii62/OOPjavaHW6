package Solid.gb;

import java.util.UUID;

public class MoveRobotCommandHandler implements CommandHandler {

    @Override
    public String commandName() {
        return "move-robot";
    }

    @Override
    public void handleCommand(RobotMap map, String[] args) {
        UUID robotId = UUID.fromString(args[0]);
        RobotMap.Robot robotById = map.findRobotById(robotId);
        if (robotById != null) {
            try {
                robotById.move();
            } catch (PositionException e) {
                System.out.println("");
                e.printStackTrace();
            }
            System.out.println("Робот с идентификаторо " + robotById+"( в скобках новые координаты) передвинут");
        }else {
            System.out.println("Робот с идентификаторо " + robotById+" не найден");
        }

    }

}
