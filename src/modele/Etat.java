package modele;

import java.util.ArrayList;

public class Etat {
    private ArrayList<Processeurs> processeurs;
    private int energie;

    public Etat(ArrayList<Processeurs> procs){
        processeurs = procs;
    }

    public void calculEnergie(){
        int cPtemp = 0;

        for (Processeurs p : processeurs)
            cPtemp = Math.max(cPtemp, p.getCout());
        energie = cPtemp;
    }

    public int getEnergie() {
        return energie;
    }
}
