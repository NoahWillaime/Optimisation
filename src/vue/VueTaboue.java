package vue;

import modele.Modele;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class VueTaboue extends JPanel implements Observer {
    private Modele mod;

    public VueTaboue(Observable o){
        mod = (Modele)o;
        o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
