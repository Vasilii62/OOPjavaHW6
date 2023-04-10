package Solid.gb;

import java.util.UUID;

public class ChangeDirectionCommandHandler implements CommandHandler {

    @Override
    public String commandName() {
        return "change-direction";
    }

    @Override
    public void handleCommand(RobotMap map, String[] args) {
        UUID robotId = UUID.fromString(args[0]);
        RobotMap.Direction newDirection = RobotMap.Direction.valueOf(args[1]);
        RobotMap.Robot robotById = map.findRobotById(robotId);
        if (robotById != null) {
            robotById.changeDirection(newDirection);
            System.out.println("Робот "+robotById+"(в скобках координаты) сменил направление движения на :"+newDirection);
        } else {
            System.out.println("Робот с идентификаторо " + robotId + " не найден");
        }
    }

}
