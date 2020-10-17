package com.rodion.turniket.kernel.constants;

public enum Spin {
    Clokwise(-1), CounterClokwise(1);
    public final int value;
    private Spin(int value){
        this.value = value;
    }
}
