import javax.swing.*;
import java.util.*;

class Move {
    public static void moveRobot(Robot robot, int toX, int toY) {
        while( !(robot.getX() == toX && robot.getY() == toY) ) {
            if( robot.getX() == toX ) {
                turnDirection(robot, toY, false);
                goForward(robot, Math.abs(toY-robot.getY()));
            } else if( robot.getY() == toY ) {
                turnDirection(robot, toX, true);
                goForward(robot, Math.abs(toX-robot.getX()));
            } else {
                if(robot.getDirection().dy() == 0) {
                    moveRobot(robot, toX, robot.getY());
                    moveRobot(robot, toX, toY);
                } else {
                    moveRobot(robot,robot.getX(), toX);
                    moveRobot(robot,toX,toY);
                }
            }
        }
    }

    private static void goForward( Robot robot, int numSteps ) {
        int count = 0;
        while( count < numSteps ) {
            System.out.println("robot.stepForward()");
            robot.stepForward();
            count++;
        }
    }

    private static void turnDirection( Robot robot, int toCoord, boolean isX ) {
        int coordDifference = isX ? toCoord - robot.getX() : toCoord - robot.getY();
        int directionMagnitude = robot.getDirection().dx() == 0 ? robot.getDirection().dy() : robot.getDirection().dx();
        boolean signsMatch = (coordDifference>0 && directionMagnitude>0) || (coordDifference<0 && directionMagnitude<0);

        if( (isX && robot.getDirection().dx() == 0) || (!isX && robot.getDirection().dy() == 0) ) {
            if( ((isX && signsMatch) || (!isX && !signsMatch)) ) {
                System.out.println("robot.turnRight()");
                robot.turnRight();
            }
            else {
                System.out.println("robot.turnLeft()");
                robot.turnLeft();
            }
        } else {
            if (!signsMatch) {
                int count = 0;
                while (count < 2) {
                    System.out.println("robot.turnRight()");
                    robot.turnRight();
                    count++;
                }
            }
        }
    }

    /*public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        int toX=3, toY=0;
        int robotX=0, robotY=0;
        Direction robotDirection = Direction.UP;
        Robot robot = new Robot(robotX,robotY, robotDirection);
        System.out.println("Originally at X="+robot.getX()+", Y="+robot.getY()+", Direction="+robot.getDirection());
        System.out.println("Moving to X="+toX+", Y="+toY);
        moveRobot(robot, toX, toY);
        System.out.println("SUCCESS:Final position X="+robot.getX()+", Y="+robot.getY()+", Direction = "+robot.getDirection() );

        robot = new Robot(1,1, Direction.RIGHT);
        System.out.println("Originally at X="+robot.getX()+", Y="+robot.getY()+", Direction="+robot.getDirection());
        System.out.println("Moving to X="+toX+", Y="+toY);
        moveRobot(robot, 0,-1);
        System.out.println("SUCCESS:Final position X="+robot.getX()+", Y="+robot.getY()+", Direction = "+robot.getDirection() );
    }*/
}

//Don't change code below

enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            default:
                throw new IllegalStateException();
        }
    }

    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            default:
                throw new IllegalStateException();
        }
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }
}

class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void stepForward() {
        x += direction.dx();
        y += direction.dy();
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}