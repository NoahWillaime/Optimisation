package vue;

import ecouteurs.EcouteurTache;
import javafx.beans.Observable;
import modele.Modele;
import modele.Tache;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observer;

public class VueRecuit extends JPanel implements Observer {
    private Modele m;
    private JPanel princip;
    private JPanel panTache;
    private JTextPane finRecuit;
    private ArrayList<JButton> taches;
    private JButton temperature;
    private JPanel info;
    private JLabel tachesInfo;
    private JLabel affichageTemp;
    private JButton lancer;
    private JPanel button;

    public VueRecuit(java.util.Observable o) {
        super();
        this.setLayout(new BorderLayout());
        this.m = (Modele)o;
        o.addObserver(this);
        princip = new JPanel(new BorderLayout());
        this.add(princip, BorderLayout.CENTER);
        panTache = new JPanel();
        panTache.setLayout(new GridLayout(1, 6));
        princip.add(panTache, BorderLayout.CENTER);
        taches = new ArrayList<>(6);
        int j = 0;
        for (Tache t : m.getTaches()) {
            JButton tmp = new JButton(t.getValue()+"");
            tmp.addActionListener(new EcouteurTache(m, j));
            panTache.add(tmp);
            taches.add(tmp);
            j++;
        }
        finRecuit = new JTextPane();
        finRecuit.setPreferredSize(new Dimension(300, 300));
        finRecuit.setEditable(false);
        princip.add(finRecuit, BorderLayout.SOUTH);
        temperature = new JButton("Choisir la Température initiale");
        temperature.addActionListener(e -> {
            try{
                int val = Integer.parseInt(JOptionPane.showInputDialog("Température initial ? "));
                if (val <= 0){
                    JOptionPane.showMessageDialog(null, "La température ne peut être inférieur (ou égal) à 0", "Temperature ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    m.setInitTemp(val);
                }
            } catch (NumberFormatException excp) {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas rentre un nombre ..", "Valeur de case ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
        button = new JPanel(new BorderLayout());
        this.add(button, BorderLayout.SOUTH);
        button.add(temperature, BorderLayout.NORTH);
        info = new JPanel();
        info.setLayout(new GridLayout(2, 1));
        affichageTemp = new JLabel("Température initial : "+m.getSaveTemp());
        info.add(affichageTemp);
        this.add(info, BorderLayout.NORTH);
        tachesInfo = new JLabel("Choix de Pi pour la Tache Ti");
        info.add(tachesInfo);
        lancer = new JButton("Lancer le Recuit Simulé");
        lancer.addActionListener(event -> m.algoRecuit());
        button.add(lancer, BorderLayout.SOUTH);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        affichageTemp.setText("Température initial : "+m.getSaveTemp());
        if (taches.size() != m.getTachesize() || m.isChangeVal()){
            panTache.removeAll();
            taches.clear();
            taches = new ArrayList<>(m.getTachesize());
            int j = 0;
            for (Tache t : m.getTaches()) {
                JButton tmp = new JButton(t.getValue()+"");
                tmp.addActionListener(new EcouteurTache(m, j));
                panTache.add(tmp);
                taches.add(tmp);
                j++;
            }
            this.panTache.revalidate();
            this.panTache.repaint();
        }
        if (m.isFinRecuit()){
            finRecuit.setText(m.getAffichageRecuit());
        }
    }
}
