package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {

    private static Random rand = new Random();

    //V1
    public static <T> T extraire(List<T> liste) {
        int index = rand.nextInt(liste.size());
        return liste.remove(index);
    }

    //V2
    public static <T> T extraireV2(List<T> liste) {
        int index = rand.nextInt(liste.size()); 
        ListIterator<T> it = liste.listIterator(); 
        T element = null;
        
        for (int i = 0; i <= index; i++) {
            element = it.next();
        }
        it.remove();
        return element;
    }

    public static <T> List<T> melanger(List<T> liste) {
        List<T> listeMelangee = new ArrayList<>();
        while (!liste.isEmpty()) {
            listeMelangee.add(extraire(liste));
        }
        return listeMelangee;
    }

    public static <T> boolean verifierMelange(List<T> l1, List<T> l2) {
        if (l1.size() != l2.size()) {
            return false;
        }
        for (T element : l1) {
            if (Collections.frequency(l1, element) != Collections.frequency(l2, element)) {
                return false;
            }
        }
        return true;
    }

    public static <T> List<T> rassembler(List<T> liste) {
        List<T> result = new ArrayList<>();
        while (!liste.isEmpty()) {
            T element = liste.get(0);
            while (liste.contains(element)) {
                result.add(element);
                liste.remove(element);
            }
        }
        return result; 
    }

    public static <T> boolean verifierRassemblement(List<T> liste) {
        if (liste.isEmpty()) return true;

        ListIterator<T> it1 = liste.listIterator();
        while (it1.hasNext()) {
            T valeurActuelle = it1.next();

            if (it1.hasNext()) {
                T valeurSuivante = it1.next();
                it1.previous();
                
                if (!valeurActuelle.equals(valeurSuivante)) {
                    ListIterator<T> it2 = liste.listIterator(it1.nextIndex());
                    while (it2.hasNext()) {
                        if (it2.next().equals(valeurActuelle)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}