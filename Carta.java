// Clase Carta que representa una carta en el juego
public class Carta {
    private char simbolo;
    private boolean isRevealed;
    private boolean isMatched;

    // Constructor
    public Carta(char simbolo) {
        this.simbolo = simbolo;
        this.isRevealed = false;
        this.isMatched = false;
    }
    // Gets 
    public char getSimbolo() { 
        return simbolo; 
    }

    public boolean isRevealed() { 
        return isRevealed; 
    }

    public boolean isMatched() { 
        return isMatched; 
    }

    // Sets
    public void setRevealed(boolean val) { 
        isRevealed = val; 
    }

    public void setMatched(boolean val) { 
        isMatched = val; 
    }
}