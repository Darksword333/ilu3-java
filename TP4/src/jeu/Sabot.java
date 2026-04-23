package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
	private Carte[] cartes;
	private Integer nbCartes;
	private int modCount = 0;
	
	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}
	
	public Boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		cartes[nbCartes++] = carte;
		modCount++;
	}
	
	public Carte piocher() {
        Iterator<Carte> it = iterator();
        if (it.hasNext()) {
            Carte c = it.next();
            it.remove();
            return c;
        }
        return null;
    }
	
	@Override
    public Iterator<Carte> iterator() {
        return new Iterateur();
    }

    private class Iterateur implements Iterator<Carte> {
        private int indiceIterateur = 0;
        private int expectedModCount = modCount;
        private boolean peutSupprimer = false;

        @Override
        public boolean hasNext() {
            return indiceIterateur < nbCartes;
        }

        @Override
        public Carte next() {
            verificationModification();
            if (!hasNext()) throw new NoSuchElementException();
            peutSupprimer = true;
            return cartes[indiceIterateur++];
        }

        @Override
        public void remove() {
            verificationModification();
            if (!peutSupprimer) throw new IllegalStateException();
            
            for (int i = indiceIterateur - 1; i < nbCartes - 1; i++) {
                cartes[i] = cartes[i + 1];
            }
            cartes[nbCartes - 1] = null;
            nbCartes--;
            indiceIterateur--;
            modCount++;
            expectedModCount++;
            peutSupprimer = false;
        }

        private void verificationModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
