package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public interface Logic extends Signal {

    /*
     *binary signal, true if on, else false
     */
    boolean isOn();

    @Override
    default float getIntensity(float t){
        return getIntensity();
    }

    default float getIntensity(){
        if(isOn()){
            return 1.0f;
        }
        return 0.0f;
    }

    Logic TRUE = new Logic() {
        @Override
        public boolean isOn() {
            return true;
        }
    };

    Logic FALSE = new Logic() {
        @Override
        public boolean isOn() {
            return false;
        }
    };


}
