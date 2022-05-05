//package com.rodion.turniket.utilities;
//
//import com.rodion.turniket.kernel.Game;
//import com.rodion.turniket.kernel.State;
//import com.rodion.turniket.kernel.Token;
//import com.rodion.turniket.kernel.constants.Direction;
//import com.rodion.turniket.kernel.constants.TokenColor;
//
//import java.io.PrintStream;
//import java.util.ArrayList;
//
//public class Solution {
//    private ArrayList<Step> steps;
//    private int mark;
//
//    public Solution() {
//        mark = 0;
//        steps = new ArrayList<>();
//    }
//
//    public boolean goForward() {
//        if (mark < steps.size() - 1) {
//            mark++;
//            return true;
//        }
//        return false;
//    }
//
//    public boolean goBackward() {
//        if (mark > 0) {
//            mark--;
//            return true;
//        }
//        return false;
//    }
//
//    public void goToBegin() {
//        mark = 0;
//    }
//
//    public Step getStep() {
//        return steps.get(mark);
//    }
//
//    public void addStep(Step step) {
//        steps.add(step);
//    }
//
//    public Step readStep() {
//        return getStep();
//    }
//
//    public void writeStep(Step step) {
//        int len = steps.size();
//        for (int i = 0; i < len - mark - 1; i++)
//            steps.remove(steps.size() - 1);
//        addStep(step);
//        goForward();
//    }
//
//    public void readSolution(String[] strings) {
//        strings[0] = strings[0];
//        String[] header = strings[0].split(" ");
//        header[1] = header[1].substring(0, header[1].length() - 1);
//        System.out.println(header[1]);
//
//        int n = Integer.parseInt(header[1].replace("\n", ""));
//        System.out.println("header " + n);
//        String[] strsteps = strings[1].split(" ");
//        for (int i = 0; i < n; i++) {
//            System.out.println(strsteps[i]);
//            addStep(string2Step(strsteps[i]));
//        }
//    }
//
//    public void readSolution(String string) {
//
//        String[] strsteps = string.split(" ");
////        int n = strsteps.length;
////        for (int i = 0; i < n; i++) {
////            System.out.println(strsteps[i]);
////            addStep(string2Step(strsteps[i]));
////        }
//        for (String str : strsteps) {
//            addStep(string2Step(str));
//        }
//    }
//
//    public void readSolution(Solution solution) {
//        steps.clear();
//        for (Step step : solution.steps) {
//            addStep(new Step(step.getStart(), step.getEnd()));
//        }
//        mark = 0;
//    }
//
//    public void clear() {
//        steps.clear();
//        mark = 0;
//    }
//
//    public void print(PrintStream stream) {
//        stream.println("Solution: " + steps.size());
//        for (Step step : steps) {
//            step.print(stream);
//            stream.print(" ");
//        }
//        stream.println();
//    }
//
//    public String getString(){
//        String str ="";
//        for (Step step : steps) {
////            step
//            str += step.getString() + " ";
//        }
//        return str;
//    }
//
//    public int getMark() {
//        return mark;
//    }
//
//    public Step getStep(TokenColor tokenColor, Direction direction, Game game) {
//        int startX = game.getState().tokens[tokenColor.index].getX();
//        int startY = game.getState().tokens[tokenColor.index].getY();
//        int start = (3 * startY) / 2 + startX / 2 + 1;
//        int end = start + direction.x + direction.y * 3;
//        return new Step(start, end);
//    }
//
//    public Step string2Step(String str) {
//        int start = Integer.parseInt(str.substring(0, 1));
//        int end = Integer.parseInt(str.substring(2, 3));
//        return new Step(start, end);
//    }
//
//    public int getNSteps() {
//        return steps.size();
//    }
//
//    public static class Step {
//        private TokenColor tokenColor;
//        private Direction direction;
//        private int start;
//        private int end;
//
//        public Step(TokenColor tokenColor, Direction direction) {
//            this.tokenColor = tokenColor;
//            this.direction = direction;
//        }
//
//        public Step(int start, int end) {
//            this.start = start;
//            this.end = end;
//        }
//
//        public void print(PrintStream stream) {
//            stream.print(start + ">" + end);
//        }
//
//        public String getString(){
//            String str = start + ">" + end;
//            return str;
//        }
//
//
//        public TokenColor getTokenColor(State state) {
//            int startX = (start - 1) % 3 * 2;
//            int startY = (start - 1) / 3 * 2;
//            Token token = (Token) state.board[startY][startX];
//            return token.getColor();
//        }
//
//        public Direction getDirection() {
//            int startX = (start - 1) % 3;
//            int startY = (start - 1) / 3;
//            int endX = (end - 1) % 3;
//            int endY = (end - 1) / 3;
//            return Direction.get(endX - startX, endY - startY);
//        }
//
//        public int getStart() {
//            return start;
//        }
//
//        public int getEnd() {
//            return end;
//        }
//
//
//    }
//}