package tuition;

/**
 * @author Sharukh Khan, Hamad Naveed
 */

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    public Enrollment(int capacity) {
        enrollStudents = new EnrollStudent[capacity];
        size = 0;
    }

    public void add(EnrollStudent enrollStudent) {
        if (size == enrollStudents.length) {
            grow();
        }
        enrollStudents[size] = enrollStudent;
        size++;
    }

    public EnrollStudent get(EnrollStudent student) {
        for (int i = 0; i < size; i++) {
            if (enrollStudents[i].getProfile().equals(student.getProfile())) {
                return enrollStudents[i];
            }
        }
        return null;
    }

    private void grow() {
        int newCapacity = enrollStudents.length * 2;
        EnrollStudent[] newEnrollStudents = new EnrollStudent[newCapacity];
        for (int i = 0; i < size; i++) {
            newEnrollStudents[i] = enrollStudents[i];
        }
        enrollStudents = newEnrollStudents;
    }

    public void remove(EnrollStudent enrollStudent) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (enrollStudents[i].equals(enrollStudent)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        enrollStudents[index] = enrollStudents[size - 1];
        enrollStudents[size - 1] = null;
        size--;
    }

    public boolean contains(EnrollStudent enrollStudent) {
        for (int i = 0; i < size; i++) {
            if (enrollStudents[i].equals(enrollStudent)) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        if(size == 0){
            System.out.println("Enrollment is empty!");
            return;
        }
        System.out.println("** Enrollment **");
        for (int i = 0; i < size; i++) {
            System.out.println(enrollStudents[i].toString());
        }
        System.out.println("* end of enrollment *");
    }
    public EnrollStudent[] getEnrollment(){ return enrollStudents; }

    public EnrollStudent get(Profile profile) {
        for (EnrollStudent enrollment : enrollStudents) {
            if (enrollment.getProfile().equals(profile)) {
                return enrollment;
            }
        }
        return null;
    }
}