package ch.epfl.cs107.play.signal.logic;

import java.util.ArrayList;
import java.util.List;

public class LogicNumber extends LogicSignal{
    private float nb;
    private List<Logic> e;
    private float nbSignal;

    public LogicNumber(float nb, Logic ... logics){
        e = new ArrayList<>();
        for(int i =0; i < logics.length; ++i){
            e.add(logics[i]);
        }
        this.nbSignal = signalNumber();
    }


    @Override
    public boolean isOn() {
        boolean cond = (e.size() < 13) && (nb >0) && (nb <= Math.pow(2, e.size()));
        if(nbSignal == nb && cond){
            return true;
        }
        return false;
    }


    private float signalNumber(){
        float sum = 0f;
        for(int i = 0; i < e.size(); ++i){
            if(e.get(i)!=null && e.get(i).isOn()){
                sum += (float) Math.pow(2, i);
            }
        }
        return sum;
    }
}

