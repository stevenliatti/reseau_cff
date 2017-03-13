import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Created by raed on 13.03.17.
 */
public class JDOM2 {
    static Document document;
    static Element racine;

    public static void main(String[] args) {
        //On crée une instance de SAXBuilder
        SAXBuilder sxb = new SAXBuilder();
        try
        {
            //On crée un nouveau document JDOM avec en argument le fichier XML
            //Le parsing est terminé ;)
            document = sxb.build(new File("villes.xml"));
        }
        catch(Exception e){}

        //On initialise un nouvel élément racine avec l'élément racine du document.
        racine = document.getRootElement();

        //Méthode définie dans la partie 3.2. de cet article
        afficheALL();
    }

    //Ajouter cette méthodes à la classe JDOM2
    static void afficheALL()
    {
        //On crée une List contenant tous les noeuds "etudiant" de l'Element racine
        List listEtudiants = racine.getChildren("ville");

        //On crée un Iterator sur notre liste
        Iterator i = listEtudiants.iterator();
        while(i.hasNext())
        {
            //On recrée l'Element courant à chaque tour de boucle afin de
            //pouvoir utiliser les méthodes propres aux Element comme :
            //sélectionner un nœud fils, modifier du texte, etc...
            Element courant = (Element)i.next();
            //On affiche le nom de l’élément courant
            System.out.println(courant.getChild("nom").getText());
        }
    }
}
