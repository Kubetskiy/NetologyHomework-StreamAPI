import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static final int EMANCIPATION_AGE = 18;
    static final int ADULT_HOOD = 27;
    static final int WOMAN_RETIREMENT_AGE = 60;
    static final int MAN_RETIREMENT_AGE = 60;
    static final int POPULATION_SIZE = 20_000_000;

    static Collection<Person> generatePersons() {
        Random random = new Random();
        Collection<Person> persons = new ArrayList<>(POPULATION_SIZE);
        for (int i = 0; i < POPULATION_SIZE; i++) {
            persons.add(generatePerson(random));
        }
        return persons;
    }

/*
    static Collection<Person> generatePersonsWithStream() {
        Random random = new Random();
        return IntStream.range(0, POPULATION_SIZE)
//                .parallel()
                .mapToObj(i -> generatePerson(random))
                .collect(Collectors.toList());
    }
*/

    static Person generatePerson(Random random) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> surnames = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        return new Person(names.get(random.nextInt(names.size())),
                surnames.get(random.nextInt(surnames.size())),
                random.nextInt(100),
                Sex.values()[random.nextInt(Sex.values().length)],
                Education.values()[random.nextInt(Education.values().length)]);
    }

    public static void main(String[] args) {
//        var a = System.currentTimeMillis();
        Collection<Person> persons = generatePersons();
//        var b = System.currentTimeMillis();
//        Collection<Person> personsFromStream = generatePersonsWithStream();
//        var c = System.currentTimeMillis();
//        System.out.println(b - a);
//        System.out.println(c - b);

        long numberOfUnderage =  // Счетчик малолеток
                persons.stream().filter(person -> person.getAge() < EMANCIPATION_AGE).count();

        // Таргет военкома
        List<String> cannonFodder = persons.stream()
                .filter(filterMilitaryCommissarTarget())
                .map(Person::getSurName).toList();

        // Трудовые резервы
        List<Person> laborReserves = persons.stream().
                filter(filterLaborReserves()).
                sorted((Comparator.comparing(Person::getSurName))).toList();
    }

    static Predicate<Person> filterMilitaryCommissarTarget() {
        return p -> p.getAge() >= EMANCIPATION_AGE &&
                p.getAge() <= ADULT_HOOD && p.getSex() == Sex.MAN;
    }

    static Predicate<Person> filterLaborReserves() {
        return p -> p.getAge() >= EMANCIPATION_AGE && p.getEducation() == Education.HIGHER &&
                ((p.getSex() == Sex.MAN && p.getAge() <= MAN_RETIREMENT_AGE) ||
                        (p.getSex() == Sex.WOMAN && p.getAge() <= WOMAN_RETIREMENT_AGE));
    }
}
