import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static final int EMANCIPATION_AGE = 18;
    static final int ADULT_HOOD = 27;
    static final int WOMAN_RETIREMENT_AGE = 60;
    static final int MAN_RETIREMENT_AGE = 60;

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> persons = new ArrayList<>(10_000_000);
        Random random = new Random();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(random.nextInt(names.size())),
                    families.get(random.nextInt(families.size())),
                    random.nextInt(100),
                    Sex.values()[random.nextInt(Sex.values().length)],
                    Education.values()[random.nextInt(Education.values().length)])
            );
        }
        long numberOfUnderage =  // Счетчик малолеток
                persons.stream().filter(person -> person.getAge() < EMANCIPATION_AGE).count();

        List<String> cannonFodder = // Таргет военкома
                persons.stream().filter(person -> person.getAge() >= EMANCIPATION_AGE &&
                                person.getAge() <= ADULT_HOOD && person.getSex() == Sex.MAN).
                        map(Person::getSurName).toList();

        List<Person> laborReserves = persons.stream().  // Трудовые резервы
                filter(person -> person.getAge() >= EMANCIPATION_AGE && person.getEducation() == Education.HIGHER &&
                        ((person.getSex() == Sex.MAN && person.getAge() <= MAN_RETIREMENT_AGE) ||
                                (person.getSex() == Sex.WOMAN && person.getAge() <= WOMAN_RETIREMENT_AGE))).
                sorted((Comparator.comparing(Person::getSurName))).toList();
    }
}
