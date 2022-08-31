public class Person {
    private String name;
    private String surName;
    private Integer age;
    private Sex sex;
    private Education education;

    public Person(String name, String surName, int age, Sex sex, Education education) {
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.sex = sex;
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public Integer getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public Education getEducation() {
        return education;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", family='" + surName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", education=" + education +
                '}';
    }
}
