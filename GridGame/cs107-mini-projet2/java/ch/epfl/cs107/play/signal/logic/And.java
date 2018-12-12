package ch.epfl.cs107.play.signal.logic;

public class And extends LogicSignal {
    private Logic s1;
    private Logic s2;

    /*
    * @param two logics
    */
    public And(Logic s1, Logic s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    /*
     * comput & return the logical value of logics s1 AND s2
     */
    public boolean isOn() {
        if(s1 != null && s2 != null && s1.isOn() && s2.isOn()){
            return true;
        }
        return false;
    }
}
