package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public class Not extends LogicSignal {
    private Logic s;

    public Not(Logic s){
        this.s = s;
    }

    @Override
    /*
    * return the opposite logical value of s;
    */
    public boolean isOn() {
        if(s != null && !s.isOn() ) {
            return true;
        }
        return false;
    }
}
