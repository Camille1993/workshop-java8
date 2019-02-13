package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

    // tag::PersonToAccountMapper[]
    @SuppressWarnings("hiding")
	interface PersonToAccountMapper<Account, String> {
        Account map(Person p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    @SuppressWarnings("hiding")
    // un mapper peut avoir plusieur categories qu'il faut déclarer au préalable
	private <Account> List<Account> map(List<Person> personList, PersonToAccountMapper <Account, String> mapper) {
        // TODO implémenter la méthode
    	
    	List <Account> newAccountList  = new ArrayList <Account>();
    		for (Person p : personList) {
    				Account a = mapper.map(p);
    				newAccountList.add(a);
    		}
        return newAccountList;
       
    }
    // end::map[]


    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de comptes
        // TODO tous les objets comptes ont un solde à 100 par défaut
        
        List<Account> result = map(personList, p-> {
        	//creer un nouveau account ou l'on defini le solde et le propriétaire pour chaque personne p
        Account a = new Account();
        a.setBalance(100);
        a.setOwner(p);
        return a;	
        });

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(hasProperty("balance", is(100))));
        assertThat(result, everyItem(hasProperty("owner", notNullValue())));
    }
    // end::test_map_person_to_account[]

    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de prénoms
        
        List<String> result = map(personList, p -> p.getFirstname()); 
        	

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(instanceOf(String.class)));
        assertThat(result, everyItem(startsWith("first")));
    }
    // end::test_map_person_to_firstname[]
}
