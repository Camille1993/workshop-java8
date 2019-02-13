package java8.ex02;

import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Redéfinition
 */
public class Method_02_Test {

    // tag::IDao[]
    interface IDao {
        List<Person> findAll();

        // TODO créer une méthode String format()
        // TODO la méthode retourne une chaîne de la forme [<nb_personnes> persons]
        // TODO exemple de résultat : "[14 persons]", "[30 persons]"
        
        default String format(String nbPer){
        		nbPer = "[<nb personnes> persons";
            	return nbPer;
            	}
        }
    
    // end::IDao[]

    // tag::DaoA[]
    class DaoA implements IDao {
    	
    	// TODO redéfinir la méthode String format()
        // TODO la méthode retourne une chaîne de la forme DaoA[<nb_personnes> persons]
        // TODO exemple de résultat : "DaoA[14 persons]", "DaoA[30 persons]"
        // TODO l'implémentation réutilise la méthode format() de l'interface
    	
        List<Person> people = Data.buildPersonList(20);

        public List<Person> findAll() {	
            return people;}
            
        public String format(String nbPer) {
        	int addPer = 0;
        	for (Person p : people){
            	addPer = addPer + 1;
                }
        	nbPer = "DaoA[" + addPer + " persons]";
        return nbPer;
    }

 }

	// end::DaoA[]
	
	
	@Test
	public void test_daoA_format() throws Exception {
	
	    DaoA daoA = new DaoA();
	
	    // TODO invoquer la méthode format() pour que le test soit passant
	    String result = null;
	    result = daoA.format(result);
	    
	    assertThat(result, is("DaoA[20 persons]"));
	}
}

