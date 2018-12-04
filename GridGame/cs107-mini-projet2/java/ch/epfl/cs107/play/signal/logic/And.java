package ch.epfl.cs107.play.signal.logic;

public class And extends LogicSignal {
    private Logic s1;
    private Logic s2;

    public And(Logic s1, Logic s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public boolean isOn() {
        boolean cond = s1.isOn() && s2.isOn() && s1 != null && s2 != null;
        if(cond){
            return true;
        }
        return false;
    }
}
