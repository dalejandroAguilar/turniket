package com.rodion.turniket.kernel;

public interface Command {
    void undo() ;
    void redo() ;
    void restart();
}
