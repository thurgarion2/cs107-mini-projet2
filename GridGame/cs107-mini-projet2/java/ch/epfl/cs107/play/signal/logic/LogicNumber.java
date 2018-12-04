package ch.epfl.cs107.play.signal.logic;

import java.util.ArrayList;
import java.util.List;

public class LogicNumber extends LogicSignal{
    private float nb;
    private List<Logic> e;
    private int[] valeurSignals;
    private float nbSignal;

    public LogicNumber(float nb, Logic ... logics){
        e = new ArrayList<>();
        for(int i =0; i < logics.length; ++i){
            e.add(logics[i]);
        }
        this.valeurSignals = valeurSignals(e);
        this.nbSignal = signalNumber(this.valeurSignals);
    }


    @Override
    public boolean isOn() {
        boolean cond = (e.size() < 13) && (nb >0) && (nb <= Math.pow(2, e.size()));
        if(nbSignal == nb && cond){
            return true;
        }
        return false;
    }


    /*
     * @param : List<Logic>
     * @return : array of number values of loigcs of List
     */
    private int [] valeurSignals(List<Logic> s){
        int[] tab = new int[s.size()];
        for(int i =0; i < tab.length; ++i){
            if(s.get(i).isOn()){
                tab[i] = 1;
            }else {
                tab[i] = 0;
            }
        }
        return tab;
    }

    private float signalNumber(int[] valeurSignals){
        float sum = 0f;
        for(int i = 0; i < valeurSignals.length; ++i){
            sum += (float) (Math.pow(2, i) * valeurSignals[i]);
        }
        return sum;
    }
}

