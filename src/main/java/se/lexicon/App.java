package se.lexicon;

import se.lexicon.data.impl.PeopleDaoImpl;
import se.lexicon.model.Person;

public class App
{
    public static void main( String[] args )
    {
        Person person1 = new Person("Elnaz2", "Azizi2");

        PeopleDaoImpl peopleDao = new PeopleDaoImpl();

        Person createdPerson = peopleDao.create(person1);
        System.out.println(createdPerson);

    }
}
