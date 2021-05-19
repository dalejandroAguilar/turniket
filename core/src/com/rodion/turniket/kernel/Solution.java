package com.rodion.turniket.kernel;


import java.io.PrintStream;
import java.util.ArrayList;

public class Solution {
    private ArrayList<Step> steps;
    private int mark;

    public Solution() {
        mark = 0;
        steps = new ArrayList<>();
    }

    public boolean goForward() {
        if (mark < steps.size() - 1) {
            mark++;
            return true;
        }
        return false;
    }

    public boolean goBackward() {
        if (mark > 0) {
            mark--;
            return true;
        }
        return false;
    }

    public void goToBegin() {
        mark = 0;
    }

    public Step getStep() {
        return steps.get(mark);
    }

    public void addStep(Step step) {
        steps.add(step);
    }

    public Step readStep() {
        return getStep();
    }

    public void writeStep(Step step) {
        int len = steps.size();
        for (int i = 0; i < len - mark - 1; i++)
            steps.remove(steps.size() - 1);
        addStep(step);
        goForward();
    }

    public void readSolution(String[] strings) {
        strings[0] = strings[0];
        String[] header = strings[0].split(" ");
        header[1] = header[1].substring(0, header[1].length() - 1);
        System.out.println(header[1]);

        int n = Integer.parseInt(header[1].replace("\n", ""));
        System.out.println("header " + n);
        String[] strsteps = strings[1].split(" ");
        for (int i = 0; i < n; i++) {
            System.out.println(strsteps[i]);
            addStep(Step.string2Step(strsteps[i]));
        }
    }

    public void readSolution(Solution solution) {
        steps.clear();
        for (Step step : solution.steps) {
            addStep(new Step(step.getStart(), step.getEnd()));
        }
        mark = 0;
    }

    public void clear() {
        steps.clear();
        mark = 0;
    }

    public void print(PrintStream stream) {
        stream.println("Solution: " + steps.size());
        for (Step step : steps) {
            step.print(stream);
            stream.print(" ");
        }
        stream.println();
    }

    public int getMark() {
        return mark;
    }
}