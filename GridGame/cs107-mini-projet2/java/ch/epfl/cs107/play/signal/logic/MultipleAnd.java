package ch.epfl.cs107.play.signal.logic;

import java.util.ArrayList;
import java.util.List;

public class MultipleAnd extends LogicSignal{
    private List<Logic> signals;

    /*
    * @param : array of Logics
    */

    public MultipleAnd(Logic ... logics){
        signals = new ArrayList();
        for(int i =0; i < logics.length ; ++i){
            signals.add(logics[i]);
        }
    }


    @Override
    /*
    * @return true if all Logics in the array are true otherwise false
    */
    public boolean isOn() {
        for(int i = 0; i < signals.size(); ++i){
            if(signals.get(i) == null || !signals.get(i).isOn()){
                return false;
            }
        }
        return true;
    }
}
