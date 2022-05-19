package com.rodion.turniket.kernel.levelGenerator;


import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;

public class MainTest {
//    private LevelGenerator lg;
    public static void main(String[] args){
//        System.out.println("hola");
        String[] map = {
                " 1 2",
                "1112 ",
                " 3R4 ",
                " 334 ",
                "C3 4 "
        };
        LevelGenerator lg = new LevelGenerator();
        lg.readFile(map);
        lg.setFromMap();
        lg.print(System.out);
        lg.reverseMove(TokenColor.Cyan, Direction.Up, true, Direction.Right);
        lg.print(System.out);
        lg.reverseMove(TokenColor.Cyan, Direction.Right, true, Direction.Up);
        lg.print(System.out);
        lg.reverseMove(TokenColor.Cyan, Direction.Up, true, Direction.Left);
        lg.print(System.out);
        lg.reverseMove(TokenColor.Cyan, Direction.Right, false, Direction.Left);
        lg.print(System.out);
    }
}