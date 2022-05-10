package com.rodion.turniket.kernel.levelGenerator;


public class MainTest {
//    private LevelGenerator lg;
    public static void main(String[] args){
        System.out.println("hola");
        String[] map = {
                " 1 2",
                "1112 ",
                " 3 4 ",
                " 3 4 ",
                "w3 4 "
        };
        LevelGenerator lg = new LevelGenerator();
        lg.readFile(map);
        lg.setFromMap();
//        lg.generate();
        lg.print(System.out);
//        lg.getState().print();
//
    }
}