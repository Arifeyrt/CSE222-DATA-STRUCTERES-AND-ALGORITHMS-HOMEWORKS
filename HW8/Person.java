import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Represents a person in the social network.
 */
public class Person {
    private String name;
    private int age;
    private List<String> hobbies;
    private Date timestamp;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructs a new Person with the specified name, age, and hobbies.
     *
     * @param name    the name of the person
     * @param age     the age of the person
     * @param hobbies the hobbies of the person
     */
    public Person(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);
        this.timestamp = new Date();
    }

    /**
     * Returns the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the person.
     *
     * @return the age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the hobbies of the person.
     *
     * @return the hobbies of the person
     */
    public List<String> getHobbies() {
        return hobbies;
    }

    /**
     * Returns the timestamp of when the person was created.
     *
     * @return the timestamp of the person
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the formatted timestamp of the person.
     *
     * @return the formatted timestamp as a String
     */
    public String getFormattedTimestamp() {
        return dateFormat.format(timestamp);
    }

    /**
     * Returns a string representation of the person.
     *
     * @return a string representation of the person
     */
    @Override
    public String toString() {
        return name + " (Age: " + age + ", Hobbies: " + hobbies + ", Timestamp: " + getFormattedTimestamp() + ")";
    }
}
