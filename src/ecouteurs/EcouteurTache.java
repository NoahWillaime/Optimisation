package ecouteurs;

import modele.Modele;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurTache implements ActionListener {
    private Modele mod;
    private int index;

    public EcouteurTache(Modele m, int index){
        mod = m;
        this.index = index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            int val = Integer.parseInt(JOptionPane.showInputDialog("Durée opératoire de la Tâche "+(index+1)));
            mod.setTaches(index, val);
        } catch (NumberFormatException excp) {
            JOptionPane.showMessageDialog(null, "Vous n'avez pas rentre un nombre ..", "Valeur de case ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
