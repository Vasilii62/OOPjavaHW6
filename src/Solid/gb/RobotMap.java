package Solid.gb;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RobotMap {

    private final int n;
    private final int m;

    private final Map<UUID, Robot> robots;

    public RobotMap(int n, int m) {
        if (n < 0 || m < 0) {
            throw new IllegalArgumentException("Недопустимые значения размера карты!");
        }
        this.n = n;
        this.m = m;
        this.robots = new HashMap<>();
    }

    public Robot findRobotById(UUID id) {
        return robots.get(id);
    }

    public Robot createRobot(Point position) throws PositionException {
        checkPosition(position);

        Robot robot = new Robot(position);
        robots.put(robot.id, robot);
        return robot;
    }

    private void checkPosition(Point position) throws PositionException {
        if (position.getX() < 0 || position.getY() < 0 || position.getX() > n || position.getY() > m) {
            throw new PositionException("Некорректное значение точки: " + position);
        }
        if (!isFree(position)) {
            throw new PositionException("Точка " + position + " занята!");
        }
    }

    private boolean isFree(Point position) {
        return robots.values().stream()  // Robot
                .map(Robot::getPosition) // Point
                .noneMatch(position::equals);
    }

    public class Robot {

        private final UUID id;
        private Point position;
        private Direction direction;

        public Robot(Point position) {
            this.id = UUID.randomUUID();
            this.position = position;
            this.direction = Direction.TOP;
        }

        public UUID getId() {
            return id;
        }

        public Point getPosition() {
            return position;
        }

        public void move() throws PositionException {

            final Point newPoint;
            if (Direction.TOP == direction) {
                newPoint = new Point(position.getX() - 1, position.getY());
            } else if (Direction.RIGHT == direction) {
                newPoint = new Point(position.getX(), position.getY() + 1);
            } else if (Direction.BOTTOM == direction) {
                newPoint = new Point(position.getX() + 1, position.getY());
            } else /* (Direction.LEFT == direction) */ {
                newPoint = new Point(position.getX(), position.getY() - 1);
            }
            ;

            Point newPosition = newPoint;

            checkPosition(newPosition);

            position = newPosition;
        }

        public void changeDirection(Direction direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", id.toString(), position.toString());
        }
    }

    public enum Direction {
        TOP, RIGHT, BOTTOM, LEFT
    }

}
