package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class 뱀 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int appleN = Integer.parseInt(reader.readLine());
        List<Apple> applePositions = new ArrayList<>();

        for (int i = 0; i < appleN; i++) {
            String[] appleInput = reader.readLine().split(" ");
            int appleX = Integer.parseInt(appleInput[0]);
            int appleY = Integer.parseInt(appleInput[1]);
            applePositions.add(new Apple(appleX, appleY));
        }

        int degreeN = Integer.parseInt(reader.readLine());
        PriorityQueue<Move90Degree> move90Degrees = new PriorityQueue<>();
        for (int i = 0; i < degreeN; i++) {
            String[] moveInput = reader.readLine().split(" ");
            int time = Integer.parseInt(moveInput[0]);
            String direction = moveInput[1];  // D or L
            move90Degrees.offer(new Move90Degree(time, direction));
        }

        // 모든 입력이 정상적으로 처리되었는지 확인
        Position startPosition = new Position(1, 1, Direction.east, BodyPart.HEAD);
        LinkedList<Position> positions = new LinkedList<>();
        positions.addFirst(startPosition);

        int answer = 0;
        while (
                !positions.isEmpty() &&
                        (positions.peek().getX() > 0 && positions.peek().getX() <= N &&
                                positions.peek().getY() > 0 && positions.peek().getY() <= N)
        ) {
            System.out.println("round: " + answer);
            for (Position position : positions) {
                if (positions.getFirst().getBodyPart().equals(BodyPart.HEAD)) {
                    System.out.println("[HEAD] = " + position);
                }

                if (positions.getFirst().getBodyPart().equals(BodyPart.TAIL)) {
                    System.out.println("[TAIL] = " + position);
                }
            }
            System.out.println();

            Position headPosition = positions.getFirst();
            Position afterTurnPosition = headPosition.changeDirection(move90Degrees, headPosition, answer);
            Position afterMovePosition = Position.movePosition(afterTurnPosition);

            positions.removeFirst();
            positions.addFirst(afterMovePosition);

            // if(사과를 만나면)
            for (Apple applePosition : applePositions) {
                System.out.println("사과 위치: (" + applePosition.getX() + ", " + applePosition.getY() + ")");
                System.out.println("머리 위치: (" + afterMovePosition.getX() + ", " + afterMovePosition.getY() + ")");

                if (applePosition.getY() == afterMovePosition.getX()
                        && applePosition.getX() == afterMovePosition.getY()) {
                    System.out.println("사과 만남!");
                    applePositions.remove(applePosition);
                    Position tail = new Position(positions.getLast().getX(), positions.getLast().getY(), positions.getLast().getDirection(), BodyPart.TAIL);
                    positions.addLast(tail);
                    break;
                }
            }

            System.out.println("----  사과 작업 후~~!  ----");
            positions.forEach(System.out::println);
            System.out.println("----  ----------  ----");

            // 꼬리가 있다면
            if (positions.stream().anyMatch(position -> position.getBodyPart().equals(BodyPart.TAIL))) {
                // 머리의 뒤로 따라감
                if (headPosition.getBodyPart().equals(BodyPart.HEAD)) {
                    // 새로운 리스트 생성
                    List<Position> updatedPositions = new ArrayList<>();

                    Position previous = headPosition;
                    for (Position tail : positions) {
                        // 머리가 꼬리를 만나면
                        if (headPosition.getX() == tail.getX() && headPosition.getY() == tail.getY()) {
                            System.out.println(answer + 1);
                            System.out.println("몸통부");
                            return;
                        }

                        Position tailPosition = new Position(previous.getX(), previous.getY(), tail.getDirection(),
                                BodyPart.TAIL);
                        updatedPositions.add(tailPosition);

                        // 꼬리가 다음 꼬리의 이전 위치가 됨
                        previous = tailPosition;
                    }
                    positions.addAll(updatedPositions);ㅠ
                }
            }
            answer += 1;
        }

        System.out.println(answer);
    }

    enum Direction {
        east,   // 동
        west,   // 서
        south,  // 남
        north,  // 북
        ;
    }

    enum BodyPart {
        HEAD,
        TAIL,
        ;
    }

    static class Position {
        private final BodyPart bodyPart;
        private final int x;
        private final int y;
        private Direction direction;

        public Position(final int x, final int y, final Direction direction, final BodyPart bodyPart) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.bodyPart = bodyPart;
        }

        public static Position movePosition(Position position) {
            if (position.direction.equals(Direction.east)) {
                return new Position(position.getX() + 1, position.getY(), Direction.east, position.getBodyPart());
            }

            if (position.direction.equals(Direction.west)) {
                return new Position(position.getX() - 1, position.getY(), Direction.west, position.getBodyPart());
            }

            if (position.direction.equals(Direction.north)) {
                return new Position(position.getX(), position.getY() - 1, Direction.north, position.getBodyPart());
            }

            if (position.direction.equals(Direction.south)) {
                return new Position(position.getX(), position.getY() + 1, Direction.south, position.getBodyPart());
            }

            throw new RuntimeException();
        }

        public static Position changeBodyType(final Position position) {
            if (position.bodyPart.equals(BodyPart.HEAD)) {
                return new Position(position.getX(), position.getY(), position.getDirection(), BodyPart.TAIL);
            }

            if (position.bodyPart.equals(BodyPart.TAIL)) {
                return new Position(position.getX(), position.getY(), position.getDirection(), BodyPart.HEAD);
            }

            throw new RuntimeException();
        }

        public Position changeDirection(final PriorityQueue<Move90Degree> move90Degrees, Position currentPosition,
                                        final int answer) {
            if (!move90Degrees.isEmpty() && move90Degrees.peek().getTime() == answer) {
                String moveDirection = move90Degrees.poll().getMoveDirection();
                if (moveDirection.equals("D")) {
                    currentPosition = setRight90(currentPosition);
                }

                if (moveDirection.equals("L")) {
                    currentPosition = setLeft90(currentPosition);
                }
            }
            return currentPosition;
        }

        private Position setRight90(Position position) {
            if (position.getDirection().equals(Direction.east)) {
                return new Position(position.getX(), position.getY(), Direction.south, position.getBodyPart());
            }

            if (position.getDirection().equals(Direction.west)) {
                return new Position(position.getX(), position.getY(), Direction.north, position.getBodyPart());
            }

            if (position.getDirection().equals(Direction.south)) {
                return new Position(position.getX(), position.getY(), Direction.west, position.getBodyPart());
            }

            if (position.getDirection().equals(Direction.north)) {
                return new Position(position.getX(), position.getY(), Direction.east, position.getBodyPart());
            }

            throw new RuntimeException();
        }

        private Position setLeft90(Position position) {
            if (position.getDirection().equals(Direction.east)) {
                return new Position(position.getX(), position.getY(), Direction.north, position.getBodyPart());
            }

            if (position.getDirection().equals(Direction.west)) {
                return new Position(position.getX(), position.getY(), Direction.south, position.getBodyPart());
            }

            if (position.getDirection().equals(Direction.south)) {
                return new Position(position.getX(), position.getY(), Direction.east, position.getBodyPart());
            }

            if (position.getDirection().equals(Direction.north)) {
                return new Position(position.getX(), position.getY(), Direction.west, position.getBodyPart());
            }

            throw new RuntimeException();
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public BodyPart getBodyPart() {
            return bodyPart;
        }

        public Direction getDirection() {
            return direction;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "bodyPart=" + bodyPart +
                    ", x=" + x +
                    ", y=" + y +
                    ", direction=" + direction +
                    '}';
        }
    }

    static class Apple {
        private final int x;
        private final int y;

        public Apple(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }

}

class Move90Degree implements Comparable<Move90Degree> {
    private int time;
    private String moveDirection;

    public Move90Degree(final int time, final String moveDirection) {
        this.time = time;
        this.moveDirection = moveDirection;
    }

    public int getTime() {
        return time;
    }

    public String getMoveDirection() {
        return moveDirection;
    }


    @Override
    public int compareTo(final Move90Degree o) {
        return this.getTime() - o.getTime();
    }

}