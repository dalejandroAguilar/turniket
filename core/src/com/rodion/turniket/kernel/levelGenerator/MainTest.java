package com.rodion.turniket.kernel.levelGenerator;

import com.rodion.turniket.kernel.State;
import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;

public class MainTest {
//    private LevelGenerator lg;
    public static void main(String[] args){
//        System.out.println("hola");
        String[] map = {
                " 1 2",
                "1112 ",
                " 3 4 ",
                " 334 ",
                "C3 4 "
        };
        InverseGame lg = new InverseGame();
        lg.readFile(map);
        lg.setFromMap();

        lg.print(System.out);
        lg.reverseMove(TokenColor.Cyan, Direction.Up, true, Direction.Right);
        lg.getState().step.print2ln(System.out);
        lg.print(System.out);
        lg.reverseMove(TokenColor.Cyan, Direction.Right, true, Direction.Down);
        lg.getState().step.print2ln(System.out);
        lg.print(System.out);
        lg.reverseMove(TokenColor.Cyan, Direction.Down, true, Direction.Left);
        lg.getState().step.print2ln(System.out);
        lg.print(System.out);
        lg.reverseMove(TokenColor.Cyan, Direction.Left, true, Direction.Up);
        lg.getState().step.print2ln(System.out);
        lg.print(System.out);
        lg.reverseMove(TokenColor.Cyan, Direction.Up, true, Direction.Right);
        lg.getState().step.print2ln(System.out);
        lg.print(System.out);
        State state2 = new State(lg.getState());
        lg.print(System.out);

//        System.out.println(lg.getState().equals(state2));
        System.out.println("******************");
        lg.printSolution(System.out);
    }
}