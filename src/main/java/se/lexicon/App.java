package se.lexicon;

import se.lexicon.data.impl.PeopleDaoImpl;
import se.lexicon.model.Person;

public class App {
    public static void main(String[] args) {
        PeopleDaoImpl peopleDao = new PeopleDaoImpl();
        //Person person1 = new Person("Elnaz2", "Azizi2");
        //Person createdPerson = peopleDao.create(person1);
        //System.out.println(createdPerson);
        Person foundPersonById = peopleDao.findById(2);
        System.out.println(foundPersonById);


    }
}
