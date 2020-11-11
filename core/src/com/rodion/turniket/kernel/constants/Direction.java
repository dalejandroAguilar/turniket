package com.rodion.turniket.kernel.constants;


public enum Direction {
    Up(0, -1), Down(0, 1), Left(-1, 0), Right(1, 0);
    public final int x;
    public final int y;

    private Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction get(int x, int y) {
        if (x == 0 && y == -1)
            return Direction.Up;
        if (x == 0 && y == 1)
            return Direction.Down;
        if (x == -1 && y == 0)
            return Direction.Left;
        if (x == 1 && y == 0)
            return Direction.Right;
        return null;
    }

    public Direction rotate(Spin spin) {
        switch (spin) {
            case Clokwise:
                switch (this) {
                    case Up:
                        return Right;
                    case Down:
                        return Left;
                    case Left:
                        return Up;
                    case Right:
                        return Down;
                }
            case CounterClokwise:
                switch (this) {
                    case Up:
                        return Left;
                    case Down:
                        return Right;
                    case Left:
                        return Down;
                    case Right:
                        return Up;
                }
        }
        return null;
    }

    public Spin spinValue(Direction direction) {
        switch (this) {
            case Up:
                switch (direction) {
                    case Right:
                        return Spin.Clokwise;
                    case Left:
                        return Spin.CounterClokwise;
                }
            case Down:
                switch (direction) {
                    case Right:
                        return Spin.CounterClokwise;
                    case Left:
                        return Spin.Clokwise;
                }
            case Right:
                switch (direction) {
                    case Up:
                        return Spin.CounterClokwise;
                    case Down:
                        return Spin.Clokwise;
                }
            case Left:
                switch (direction) {
                    case Down:
                        return Spin.CounterClokwise;
                    case Up:
                        return Spin.Clokwise;
                }
        }
        return null;
    }

    public float getAngle(){
        switch (this) {
            case Up:
                return 180;
            case Down:
                return 0;
            case Right:
                return 90;
            case Left:
                return 270;
        }
        return 0;
    }

}
