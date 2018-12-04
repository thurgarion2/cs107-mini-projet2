package ch.epfl.cs107.play.signal.logic;

import java.util.ArrayList;
import java.util.List;

public class MultipleAnd extends LogicSignal{
    private List<Logic> signals;

    public MultipleAnd(Logic ... logics){
        signals = new ArrayList();
        for(int i =0; i < logics.length ; ++i){
            signals.add(logics[i]);
        }
    }

    @Override
    public boolean isOn() {
        boolean resultat = true;
        for(int i = 0; i < signals.size(); ++i){
            if(!signals.get(i).isOn() && signals.get(i) != null){
                resultat = false;
            }
        }
        return resultat;
    }
}
