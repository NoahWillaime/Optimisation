import modele.Modele;
import vue.VueAlgo;

import javax.swing.*;
import java.awt.*;

public class Optimisation extends JFrame {
    public Optimisation(){
        super("Optimisation");
        Modele mod = new Modele();
        VueAlgo vueAlgo = new VueAlgo(mod);
        add(vueAlgo, BorderLayout.CENTER);
        setPreferredSize(new Dimension(800, 800));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new Optimisation();
    }
}
