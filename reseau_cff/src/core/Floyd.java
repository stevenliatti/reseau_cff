package core;

import model.Connection;
import model.Net;

/**
 * Classe implémentant la construction de la matrice des poids dans un graphe et l'algorithme de Floyd.
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
public class Floyd {
    private Net net;
    private int[][] initialWeightMatrix;
    private int[][] weightMatrixFloyd;
    private int[][] precMatrixFloyd;

    /**
     * Construit un objet Floyd à partir d'un réseau de villes {@link Net}.
     * @param net Un réseau de villes
     */
    public Floyd(Net net) {
        this.net = net;
        buildInitialWeightMatrix();
        buildMatrixFloyd();
    }

    /**
     * Recalcule la matrice des poids et la matrice de Floyd après un changement dans le réseau.
     */
    public void update() {
        buildInitialWeightMatrix();
        buildMatrixFloyd();
    }

    /**
     * Retourne la matrice des poids initiale.
     * @return La matrice des poids initiale
     */
    public int[][] getInitialWeightMatrix() {
        return initialWeightMatrix;
    }

    /**
     * Retourne la matrice des poids calculée.
     * @return La matrice des poids calculée
     */
    public int[][] getWeightMatrixFloyd() {
        return weightMatrixFloyd;
    }

    /**
     * Retourne la matrice des précédences de Floyd
     * @return La matrice des précédences de Floyd
     */
    public int[][] getPrecMatrixFloyd() {
        return precMatrixFloyd;
    }

    private void buildInitialWeightMatrix() {
        int n = net.getCityList().size();
        initialWeightMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    initialWeightMatrix[i][j] = 0;
                else
                    initialWeightMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
        for (Connection c : net.getConnectionList()) {
            int i = CffCompute.cityNames.indexOf(c.getVil_1());
            int j = CffCompute.cityNames.indexOf(c.getVil_2());
            initialWeightMatrix[i][j] = c.getDuratin();
            int x = i;
            i = j;
            j = x;
            initialWeightMatrix[i][j] = c.getDuratin();
        }
    }

    private void initMatrixFloyd() {
        int n = this.net.getCityList().size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.weightMatrixFloyd[i][j] = this.initialWeightMatrix[i][j];
                if (i == j || this.initialWeightMatrix[i][j] == Integer.MAX_VALUE)
                    this.precMatrixFloyd[i][j] = -1;
                else
                    this.precMatrixFloyd[i][j] = i;
            }
        }
    }

    private void buildMatrixFloyd() {
        int n = this.net.getCityList().size();
        this.weightMatrixFloyd = new int[n][n];
        this.precMatrixFloyd = new int[n][n];
        initMatrixFloyd();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (i != k && this.weightMatrixFloyd[i][k] != Integer.MAX_VALUE) {
                    for (int j = 0; j < n; j++) {
                        if (i != j && j != k && this.weightMatrixFloyd[k][j] != Integer.MAX_VALUE) {
                            if (this.weightMatrixFloyd[i][k] + this.weightMatrixFloyd[k][j] < this.weightMatrixFloyd[i][j]) {
                                this.weightMatrixFloyd[i][j] = this.weightMatrixFloyd[i][k] + this.weightMatrixFloyd[k][j];
                                this.precMatrixFloyd[i][j] = this.precMatrixFloyd[k][j];
                            }
                        }
                    }
                }
            }
        }
    }
}
