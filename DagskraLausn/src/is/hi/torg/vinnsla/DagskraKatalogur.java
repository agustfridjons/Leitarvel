/*
 * Ebba Þóra Hvannberg, Háskóli Íslands
 */
package is.hi.torg.vinnsla;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Klasi sem les inn sjónvarpsdagskrá úr XML skrá og getur birt hana á console 
 * @author Ebba Þóra Hvannberg, Háskóli Íslands, ebba@hi.is
 */
public class DagskraKatalogur {
    private Root dagskra;                     // dagskráin
    private ArrayList<Root.Results> dagskrarLidir;  // listi af dagskrárliðum - haft hér til 
                                      // þæginda

    // Fastar
     private static final String SJONVARPSDAGSKRAXML = "Sjonvarpsdagskra.xml";
     private static final String VILLA_Í_LESTRI_Á_XML_SKRÁ = "Villa í lestri á XML skrá";
     private static final String SKRÁ_MEÐ_XML_GÖGNUM_FANNST_EKKI = "Skrá með XML gögnum fannst ekki";
  
    /**
     * Getters og setters fyrir tilviksbreytur 
     * @return 
     */
    public Root getDagskra() {
        return dagskra;
    }

    public void setDagskra(Root dagskra) {
        this.dagskra = dagskra;
    }

    public List<Root.Results> getDagskrarLidir() {
        return dagskrarLidir;
    }

    public void setDagskrarLidir(ArrayList<Root.Results> dagskrarLidir) {
        this.dagskrarLidir = dagskrarLidir;
    }
    
    /**
     * Smiður sem les inn XML skrána Sjonvarpsdagskrá
     * Birtir villu ef XML skrá fannst ekki 
     */
    public DagskraKatalogur() {
        File XMLfile = null;
        InputStream inputStream = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            inputStream = DagskraKatalogur.class.getResourceAsStream(SJONVARPSDAGSKRAXML);

            dagskra = (Root) jaxbUnmarshaller.unmarshal(inputStream);
            dagskrarLidir = (ArrayList<Root.Results>) dagskra.getResults();

        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println(VILLA_Í_LESTRI_Á_XML_SKRÁ);
        }

    }
   
    
     /**
     * Prentar út titil dagskrárliðanna á console. 
     * Getur verið gagnlegt til að sjá hvort gögnin hafa komist inn 
    */
    public void birtaDagskrarlid() {
         System.out.println ("lengdin á dagskránni er "+ dagskrarLidir.size());
         for (Root.Results d:dagskrarLidir) {
            System.out.println(d.getTitle() + "  byrjar kl. "+d.getStartTime());
        }
    }
    
}
