package classroomgenerator;

public class Student {
    final public String lastName;
    final public String firstName;
    final private boolean isAgited;
    final private boolean isWeak;

    public Student(final String lastName, final String firstName, final boolean isAgited, final boolean isWeak) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.isAgited = isAgited;
        this.isWeak = isWeak;
    }

    public boolean isAgited() {
        return this.isAgited;
    }

    public boolean isWeak() {
        return this.isWeak;
    }

    @Override
    public String toString() {
        return "Student{" +
                " lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", isAgited=" + isAgited +
                ", isWeak=" + isWeak +
                " }";
    }
}
