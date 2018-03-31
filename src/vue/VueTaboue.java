package vue;

import ecouteurs.EcouteurTache;
import modele.Modele;
import modele.Tache;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VueTaboue extends JPanel implements Observer {
    private Modele m;
    private JPanel princip;
    private JPanel panTache;
    private JTextPane finTaboue;
    private ArrayList<JButton> taches;
    private JPanel button;
    private JButton listChoix;
    private JButton lancer;
    private JPanel info;
    private JLabel infoTache;
    private JLabel infoList;

    public VueTaboue(Observable o){
        super();
        this.setLayout(new BorderLayout());
        this.m = (Modele)o;
        o.addObserver(this);
        princip = new JPanel(new BorderLayout());
        this.add(princip, BorderLayout.CENTER);
        info = new JPanel(new GridLayout(2, 1));
        this.add(info, BorderLayout.NORTH);
        infoList = new JLabel("Taille de la liste taboue : "+m.getTailleTaboue());
        info.add(infoList);
        infoTache = new JLabel("Choix de Pi pour la Tache Ti");
        info.add(infoTache);
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
        finTaboue = new JTextPane();
        finTaboue.setPreferredSize(new Dimension(300, 300));
        finTaboue.setEditable(false);
        princip.add(finTaboue, BorderLayout.SOUTH);
        lancer = new JButton("Lancer la Recherche Taboue");
        lancer.addActionListener(event -> m.algoTaboue());
        button = new JPanel(new GridLayout(2, 0));
        this.add(button, BorderLayout.SOUTH);
        listChoix = new JButton("Choisir la taille de la liste taboue");
        listChoix.addActionListener(e -> {
            try{
                int val = Integer.parseInt(JOptionPane.showInputDialog("Taille de la liste taboue ? "));
                if (val < 0){
                    JOptionPane.showMessageDialog(null, "La taille de la liste taboue ne doit pas être inférieur à 0", "Taille Liste Taboue ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    m.setTailleTaboue(val);
                }
            } catch (NumberFormatException excp) {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas rentre un nombre ..", "Valeur de case ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
        button.add(listChoix);
        button.add(lancer);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.infoList.setText("Taille de la liste taboue : "+m.getTailleTaboue());
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
        if (m.isFinTaboue()){
            this.finTaboue.setText(m.getAffichageTaboue());
        }
    }
}
