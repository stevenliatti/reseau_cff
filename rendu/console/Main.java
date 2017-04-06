import core.CffCompute;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// permet de prendre les entrées pour le menu
		// soit du clavier, d'un fichier ou de la ligne de commande
		Scanner in;
		switch(args.length) {
		case 0:
			in = new Scanner(System.in);
			break;
		case 1:
		  	in = new Scanner(new File(args[0]));
		  	break;
		default:
		  	String source = args[0];
		  	for (int i=1;i<args.length;i++) source += " " + args[i];
		  	in = new Scanner(source);
		}

		String filePath = "villes.xml";
		//lire le fichier villes.xml avec votre code
		CffCompute cffCompute = new CffCompute(filePath);
		int choix;
		do {
		   	// les impressions du menu sont envoyées sur le canal d'erreur
		   	// pour les différencier des sorties de l'application
		   	// lesquelles sont envoyées sur la sortie standard
			System.err.println("Choix  0: quitter");
			System.err.println("Choix  1: liste des villes");
			System.err.println("Choix  2: matrice des poids");
			System.err.println("Choix  3: liste des poids");
			System.err.println("Choix  4: matrice des temps de parcours (Floyd)");
			System.err.println("Choix  5: matrice des précédences (Floyd)");
			System.err.println("Choix  6: temps de parcours entre deux villes (Floyd)");
			System.err.println("Choix  7: parcours entre deux villes (Floyd)");
			System.err.println("Choix  8: tableau des temps de parcours (Dijkstra)");
			System.err.println("Choix  9: tableau des précédences (Dijkstra)");
			System.err.println("Choix 10: temps de parcours entre deux villes (Dijkstra)");
			System.err.println("Choix 11: parcours entre deux villes (Dijkstra)");
			System.err.println("Choix 12: ajout d'une ville");
			System.err.println("Choix 13: ajout d'une liaison");
			System.err.println("Choix 14: suppression d'une ville");
			System.err.println("Choix 15: suppression d'une liaison");
			System.err.println("Choix 16: graphe connexe?");
		 	System.err.println("Choix 17: sauver (format XML)");

			System.err.println("Entrez votre choix: ");
			choix = in.nextInt();
			String str1, str2, str3;
			switch(choix) {
			case 1:
				cffCompute.outCityNames();
				break;
			case 2:
				// imprimer "inf" à la place Integer.MAX_VALUE
				cffCompute.outInitialWeightMatrix();
				break;
			case 3:
				cffCompute.outWeightList();
				break;
			case 4:
			   	// imprimer "inf" à la place Integer.MAX_VALUE
				cffCompute.outWeightMatrixFloyd();
				break;
			case 5:
			   	// imprimer -1 si pas de prédécesseur
				cffCompute.outPrecMatrixFloyd();
				break;
			case 6:
				System.err.println("Ville d'origine:");
				str1 = in.next();
				System.err.println("Ville de destination:");
				str2 = in.next();
				System.err.print("Distance: ");
				cffCompute.outTimeTowCitiesFloyd(str1, str2);
				break;
			case 7:
				System.err.println("Ville d'origine:");
				str1 = in.next();
				System.err.println("Ville de destination:");
				str2 = in.next();
				System.err.print("Parcours: ");
				cffCompute.outPathTowCitiesFloyd(str1, str2);
				break;
			case 8:
			   	System.err.println("Ville d'origine:");
				str1 = in.next();
				cffCompute.outTimeArrayDijkstra(str1);
				break;
			case 9:
				System.err.println("Ville d'origine:");
				str1 = in.next();
				cffCompute.outPrecArrayDijkstra(str1);
				break;
			case 10:
				System.err.println("Ville d'origine:");
				str1 = in.next();
				System.err.println("Ville de destination:");
				str2 = in.next();
				System.err.print("Distance: ");
				// imprimer "inf" à la place Integer.MAX_VALUE
				cffCompute.outTimeTwoCitiesDijkstra(str1, str2);
				break;
			case 11:
				System.err.println("Ville d'origine:");
				str1 = in.next();
				System.err.println("Ville de destination:");
				str2 = in.next();
				System.err.print("Parcours: ");
				cffCompute.outPathTwoCitiesDijkstra(str1, str2);
				break;
			case 12:
				System.err.println("Nom de la ville:");
				str1 = in.next();
				if (!cffCompute.addCity(str1)) {
					System.out.println("Ville déjà présente dans le graphe.");
				}
				break;
			case 13:
				System.err.println("Ville d'origine:");
				str1 = in.next();
				System.err.println("Ville de destination:");
				str2 = in.next();
				System.err.println("Temps de parcours:");
				str3 = in.next();
				int result = cffCompute.addConnection(str1, str2, str3);
				if (result == -1) {
					System.out.println("Erreur");
				}
				break;
			case 14:
				System.err.println("Nom de la ville:");
				str1 = in.next();
				if (!cffCompute.removeCity(str1)) {
					System.out.println("Ville non présente dans le graphe.");
				}
				break;
			case 15:
				System.err.println("Ville d'origine:");
				str1 = in.next();
				System.err.println("Ville de destination:");
				str2 = in.next();
				result = cffCompute.removeConnection(str1, str2);
				if (result == -1) {
					System.out.println("Erreur");
				}
				break;
			case 16:
				System.out.println(cffCompute.isConnectedGraph()); // réponse true ou false
				break;
			case 17:
				System.err.println("Nom du fichier XML:");
				str1 = in.next();
				cffCompute.toXmlFile(str1);
				break;
			}
		} while (choix!=0);
	}
}
