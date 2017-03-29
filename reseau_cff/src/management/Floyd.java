package management;

import model.Connection;
import model.Net;

/**
 * Created by stevenliatti on 29.03.17.
 */
public class Floyd {
    private Net net;
    private int[][] initialWeightMatrix;
    private int[][] weightMatrixFloyd;
    private int[][] precMatrixFloyd;

    public Floyd(Net net) {
        this.net = net;
        buildInitialWeightMatrix();
        buildMatrixFloyd();
    }

    public void update() {
        buildInitialWeightMatrix();
        buildMatrixFloyd();
    }

    public int[][] getInitialWeightMatrix() {
        return initialWeightMatrix;
    }

    public int[][] getWeightMatrixFloyd() {
        return weightMatrixFloyd;
    }

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
            int i = GraphManagement.cityNamesArrayList.indexOf(c.getVil_1());
            int j = GraphManagement.cityNamesArrayList.indexOf(c.getVil_2());
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
