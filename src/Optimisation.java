import modele.Modele;
import vue.VueMenu;
import vue.VuePrincipal;
import vue.VueOutils;

import javax.swing.*;
import java.awt.*;

public class Optimisation extends JFrame {
    public Optimisation(){
        super("Optimisation");
        Modele mod = new Modele();
        VueMenu vm = new VueMenu(mod);
        add(vm, BorderLayout.NORTH);
        VuePrincipal vuePrincipal = new VuePrincipal(mod);
        add(vuePrincipal, BorderLayout.CENTER);
        VueOutils vueOutils = new VueOutils(mod);
        add(vueOutils, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(800, 800));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new Optimisation();
    }
}
