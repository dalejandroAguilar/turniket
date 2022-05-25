package com.rodion.turniket.kernel;

public class Node {
    private int x;
    private int y;
    private static Node dummyNode = new Node();

    public Node() {
        x = 0;
        y = 0;
    }

    public Node(Node n) {
        if (n != null) {
            x = n.getX();
            y = n.getY();
        }
    }

    public Node(int x, int y) {
        setPosition(x, y);
    }


    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(Node node) {
        this.x = node.x;
        this.y = node.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        Node node = (Node) o;
        if (x == node.x && y == node.y)
            return true;
        return false;
    }

    public static Node dummy(int x, int y) {
        dummyNode.setPosition(x, y);
        return dummyNode;
    }

    public static Node getDummyNode() {
        return dummyNode;
    }
}
