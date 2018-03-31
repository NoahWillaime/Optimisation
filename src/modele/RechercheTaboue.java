package modele;


import java.util.ArrayList;
import java.util.Iterator;

public class RechercheTaboue {
    private Modele m;
    private ArrayList<Etat> listtaboue;
    private Etat etatCourrant;
    private Etat etatPrecedent;
    private int energieSystem;
    private int tailleTaboue;

    public RechercheTaboue(Modele m){
        this.m = m;
        this.listtaboue = new ArrayList<Etat>();
        this.etatCourrant = m.getEtatCourrant();
        this.energieSystem = etatCourrant.getEnergie();
        this.tailleTaboue = m.getTailleTaboue();
        this.etatPrecedent = etatCourrant;
    }


    public void swap(){
        etatCourrant = etatPrecedent;
    }

    public boolean isTaboue(Etat e){
        boolean check = false;
        for (Etat test : listtaboue){
            if (test.equals(e))
                check = true;
        }
        return check;
    }


    public Etat meilleurVoisin(ArrayList<Etat> voisin){
        boolean check = false;
        Etat best = new Etat(m.copyProcesseur(etatCourrant.getProcesseurs()));
        for (Etat e : voisin){
            if (!isTaboue(e)){
                if (e.getEnergie() < best.getEnergie()){
                    best = new Etat(m.copyProcesseur(e.getProcesseurs()));
                    check = true;
                }
            }
        }
        if (check)
            return best;
        else
            return null;
    }

    public boolean boucleTaboue(){
        System.out.println(m.getEtatCourrant().toString());
        ArrayList<Etat> voisin = voisins(etatCourrant);
        Etat best = meilleurVoisin(voisin);
        if (best != null){
            etatPrecedent = etatCourrant;
            etatCourrant = best;
            energieSystem = best.getEnergie();
            if (listtaboue.size() >= tailleTaboue){
                listtaboue.remove(0);
            }
            listtaboue.add(best);
            return true;
        }
        return false;
    }

    public ArrayList<Etat> voisins(Etat e){
        System.out.println(e.toString());
        ArrayList<Processeurs> newpro = m.copyProcesseur(e.getProcesseurs());
        ArrayList<Processeurs> proc;
        ArrayList<Processeurs> sauvegarde;
        ArrayList<Processeurs> copy;
        ArrayList<Etat> voisin = new ArrayList<>();
        Tache t;
        for (int i = 0; i < newpro.size(); i++){
            proc = m.copyProcesseur(newpro);
            Processeurs p = proc.get(i);
            if (p.nbTaches() > 0) {
                t = p.getTaches(p.nbTaches() - 1);
                p.removeTache(t);
                sauvegarde = m.copyProcesseur(proc);
                for (Processeurs p2 : sauvegarde){
                    if (p2.getIndex() != p.getIndex()) {
                        Processeurs newP = new Processeurs(p2);
                        newP.addTache(t);
                        copy = m.copyProcesseur(sauvegarde);
                        Iterator<Processeurs> it = copy.iterator();
                        boolean b = true;
                        while (b && it.hasNext()){
                            Processeurs p3 = it.next();
                            if (p3.getIndex() == p2.getIndex()){
                                copy.remove(p3);
                                copy.add(newP);
                                b = false;
                            }
                        }
                        voisin.add(new Etat(copy));
                    }
                }
            }
        }
        return voisin;
    }

}
