package management;

import model.City;
import model.Connection;
import model.Net;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raed on 14.03.17.
 */
public class GraphManagement {
    private Net net;
    private ArrayList<String> cityNamesArrayList;
    private int[][] weightMatrix;
    private ArrayList<String>[] weightList;

    public GraphManagement(String filePath) {
        try {

            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Net.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            this.net = (Net) jaxbUnmarshaller.unmarshal(file);

            //liste des villes
            buildCityNamesArrayList();
            //matrice des poids
            buildWeightMatrix();
            //liste des poids
            buildWeightList();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void buildCityNamesArrayList() {
        cityNamesArrayList = new ArrayList<>();
        for (City c: net.getCityList()) {
            cityNamesArrayList.add(c.getName());
        }
    }

    public void displayCityNamesArrayList() {
        for (int i = 0; i < cityNamesArrayList.size(); i++) {
            System.out.print("[" + i + ":" + cityNamesArrayList.get(i) + "] ");
        }
        System.out.println();
    }

    public void buildWeightMatrix() {
        weightMatrix = new int[cityNamesArrayList.size()][cityNamesArrayList.size()];
        for (int i = 0; i < cityNamesArrayList.size(); i++) {
            for (int j = 0; j < cityNamesArrayList.size(); j++) {
                if (i == j)
                    weightMatrix[i][j] = 0;
                else
                    weightMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
        for (Connection c : net.getConnectionList()) {
            int i = cityNamesArrayList.indexOf(c.getVil_1());
            int j = cityNamesArrayList.indexOf(c.getVil_2());
            weightMatrix[i][j] = c.getDuratin();
            int x = i;
            i = j;
            j = x;
            weightMatrix[i][j] = c.getDuratin();
        }
    }

    public void displayWeightMatrix() {
        for (int i = 0; i < cityNamesArrayList.size(); i++) {
            for (int j = 0; j < cityNamesArrayList.size(); j++) {
                if (weightMatrix[i][j] == Integer.MAX_VALUE)
                    System.out.print("inf ");
                else
                    System.out.print(weightMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void buildWeightList() {
        weightList = new ArrayList[net.getCityList().size()];
        for (int i = 0; i < weightList.length; i++) {
            weightList[i] = new ArrayList<>();
            String startCity = net.getCityList().get(i).getName();
            weightList[i].add(startCity);
            for (Connection connection : net.getConnectionList()) {
                if (connection.getVil_1().equals(startCity)) {
                    weightList[i].add(connection.getVil_2() + ":" + connection.getDuratin());
                }
                if (connection.getVil_2().equals(startCity)) {
                    weightList[i].add(connection.getVil_1() + ":" + connection.getDuratin());
                }
            }
        }
    }

    public void displayWeightList() {
        for (int i = 0; i < weightList.length; i++) {
            System.out.print(weightList[i].get(0) + " ");
            for (int j = 1; j < weightList[i].size(); j++) {
                System.out.print("[" + weightList[i].get(j) + "] ");
            }
            System.out.println();
        }
    }
}
