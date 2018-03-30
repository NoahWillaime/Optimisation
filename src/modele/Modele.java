package modele;

import java.util.ArrayList;
import java.util.Observable;

public class Modele extends Observable{
    private int[] taches;
    private Etat etatCourrant;
    private int initTemp;
    private int finalTemps;
    private int energieSystem;

    public Modele(){
        int[] tmp = {5, 4, 3, 6, 5 , 7};
        taches = tmp;
        ArrayList<Integer> p1;
        ArrayList<Integer> p2;
        p1 = new ArrayList<>(3);
        p2 = new ArrayList<>(3);
        p1.add(taches[0]);
        p1.add(taches[1]);
        p1.add(taches[4]);
        p2.add(taches[2]);
        p2.add(taches[3]);
        p2.add(taches[5]);
        etatCourrant = new Etat(p1, p2);
        initTemp = 0;
        finalTemps = 0;
        energieSystem = 0;
    }

    public void recuitSimule(){

    }

    public void setInitTemp(int initTemp) {
        this.initTemp = initTemp;
    }

    public int getFinalTemps() {
        return finalTemps;
    }

    public int[] getTaches() {
        return taches;
    }
}
