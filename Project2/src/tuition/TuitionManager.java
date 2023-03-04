package tuition;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This is the class we use to implement all of other class
 * this class does many functions such as add, enroll, remove, and load a file from text
 * @author Sharukh Khan, Hamad Naveed
 */

public class TuitionManager {
    public static final int COUNT_RESIDENT = 5;
    public static final int COUNT_TRISTATE = 6;
    public static final int COUNT_INTERNATIONAL_1 = 5;
    public static final int COUNT_INTERNATIONAL_2 = 6;
    public static final int COUNT_REMOVE = 3;
    public static final int COUNT_CHANGE = 4;

    private Roster roster = new Roster();
    private Enrollment enrollment = new Enrollment(4);

    /**
     * Reads through user input and performs valid commands until terminated.
     * If command is valid StringTokenizer separates the user input line into tokens for readability.
     * The appropriate method is called to perform the command.
     * While loop terminated by Q command.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Roster Manager running...");
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            StringTokenizer input = new StringTokenizer(command);
            if (input.hasMoreTokens()) {
                switch (input.nextToken()) {
                    case "LS":
                        loadRoster();
                        break;
                    case "AR":
                        addResident(roster, input);
                        break;
                    case "AN":
                        addNonResident(roster, input);
                        break;
                    case "AT":
                        addTristate(roster, input);
                        break;
                    case "AI":
                        addInternational(roster, input);
                        break;
                    case "PS":
                        roster.printByStanding();
                        break;
                    case "PC":
                        roster.printBySchoolMajor();
                        break;
                    case "L":
                        listBySchool(roster, input);
                        break;
                    case "C":
                        changeMajor(roster, input);
                        break;
                    case "R":
                        removeStudentFromRoster(roster, input);
                        break;
                    case "P":
                        roster.print();
                        break;
                    case "E":
                        enrollStudent(input);
                        break;
                    case "D":
                        dropStudent(input);
                        break;
                    case "S":
                        awardScholarship(input);
                        break;
                    case "PE":
                        enrollment.print();
                        break;
                    case "PT":
                        printTuition();
                        break;
                    case "SE":
                        semesterEnd();
                        break;
                    case "Q":
                        endRoasterManager();
                        break;
                    default:
                        System.out.println(command + " is a invalid command!");
                }
            }
        }
    }

    /**
     * This method loads student objects into the roster from a txt file
     * It will read the first token and determine which subclass to use for the constructor
     * for example if the first token is R then it will use the Resident method to create a resident object
     */
    private void loadRoster() {
        try {
            File file = new File("studentList.txt");
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                String[] tokens = command.split(",");
                String status = tokens[0];
                StringTokenizer input = new StringTokenizer(command.substring(command.indexOf(',') + 1), ",");
                switch (status) {
                    case "R":
                        addResident(this.roster, input);
                        break;
                    case "N":
                        addNonResident(this.roster, input);
                        break;
                    case "T":
                        addTristate(this.roster, input);
                        break;
                    case "I":
                        addInternational(this.roster, input);
                        break;
                    default:
                        System.out.println("Invalid student status.");
                }
            }
            scanner.close();
            System.out.println("Students loaded to the roster.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
    /**
     * This method will build the Resident constructor
     * It will ask for user input and store the inputs into a profile constructor as well as ask for additional information
     * This will also handle errors such as negative credits or check whether the integer is an integer
     *
     * @param input to process the tokens and store them into profile constructor
     */

    private Student buildResidentStudent(StringTokenizer input){
        if (input.countTokens() == COUNT_RESIDENT) {
            String fname = input.nextToken();
            String lname = input.nextToken();
            Date dob = new Date(input.nextToken());
            if (!dob.isValid()) {
                return null;
            }
            Profile profile = new Profile(lname, fname, dob);
            Major major = getMajor(input.nextToken());
            if (major == null) {
                return null;
            }
            int creditCompleted;
            String creditInput = input.nextToken();
            boolean isNegative = creditInput.startsWith("-");
            boolean isInteger = true;
            for (int i = isNegative ? 1 : 0; i < creditInput.length(); i++) {
                if (!Character.isDigit(creditInput.charAt(i))) {
                    isInteger = false;
                    break;
                }
            }
            if (!isInteger) {
                System.out.println("Credits completed invalid: not an integer!");
                return null;
            }
            creditCompleted = Integer.parseInt(creditInput);
            if (isNegative) {
                System.out.println("Credits completed invalid: cannot be negative!");
                return null;
            }
            Student student = new Resident(profile, major, creditCompleted);
            return student;
        }
        return null;
    }
    /**
     * This method will build the Non-Resident constructor
     * It will ask for user input and store the inputs into a profile constructor as well as ask for additional information
     * This will also handle errors such as negative credits or check whether the integer is an integer
     *
     * @param input to process the tokens and store them into profile constructor
     */
    private Student buildNonResidentStudent(StringTokenizer input){
        if (input.countTokens() == COUNT_RESIDENT) {
            String fname = input.nextToken();
            String lname = input.nextToken();
            Date dob = new Date(input.nextToken());
            if (!dob.isValid()) {
                return null;
            }
            Profile profile = new Profile(lname, fname, dob);
            Major major = getMajor(input.nextToken());
            if (major == null) {
                return null;
            }
            int creditCompleted;
            String creditInput = input.nextToken();
            boolean isNegative = creditInput.startsWith("-");
            boolean isInteger = true;
            for (int i = isNegative ? 1 : 0; i < creditInput.length(); i++) {
                if (!Character.isDigit(creditInput.charAt(i))) {
                    isInteger = false;
                    break;
                }
            }
            if (!isInteger) {
                System.out.println("Credits completed invalid: not an integer!");
                return null;
            }
            creditCompleted = Integer.parseInt(creditInput);
            if (isNegative) {
                System.out.println("Credits completed invalid: cannot be negative!");
                return null;
            }
            Student student = new NonResident(profile, major, creditCompleted);
            return student;
        }
        return null;
    }
    /**
     * This method will build the TriState constructor
     * It will ask for user input and store the inputs into a profile constructor as well as ask for additional information
     * This will also handle errors such as negative credits or check whether the integer is an integer
     *
     * @param input to process the tokens and store them into profile constructor
     */
    private Student buildTriStateStudent(StringTokenizer input){
        if (input.countTokens() == COUNT_TRISTATE) {
            String fname = input.nextToken();
            String lname = input.nextToken();
            Date dob = new Date(input.nextToken());
            if (!dob.isValid()) {
                return null;
            }
            Profile profile = new Profile(lname, fname, dob);
            Major major = getMajor(input.nextToken());
            if (major == null) {
                return null;
            }
            int creditCompleted;
            String creditInput = input.nextToken();
            boolean isNegative = creditInput.startsWith("-");
            boolean isInteger = true;
            for (int i = isNegative ? 1 : 0; i < creditInput.length(); i++) {
                if (!Character.isDigit(creditInput.charAt(i))) {
                    isInteger = false;
                    break;
                }
            }
            if (!isInteger) {
                System.out.println("Credits completed invalid: not an integer!");
                return null;
            }
            creditCompleted = Integer.parseInt(creditInput);
            if (isNegative) {
                System.out.println("Credits completed invalid: cannot be negative!");
                return null;
            }
            String state = input.nextToken().toUpperCase();
            if (!checkTriStateValidity(state)){
                System.out.println(state + " Invalid state code.");
                return null;
            }
            Student student = new TriState(profile, major, creditCompleted, state);
            return student;
        }
        return null;
    }
    /**
     * This method will build the International constructor
     * It will ask for user input and store the inputs into a profile constructor as well as ask for additional information
     * This will also handle errors such as negative credits or check whether the integer is an integer
     *
     * @param input to process the tokens and store them into profile constructor
     */
    private Student buildInternationalStudent(StringTokenizer input) {
        if (input.countTokens() == COUNT_INTERNATIONAL_1) {
            String fname = input.nextToken();
            String lname = input.nextToken();
            Date dob = new Date(input.nextToken());
            if (!dob.isValid()) {
                return null;
            }
            Profile profile = new Profile(lname, fname, dob);
            Major major = getMajor(input.nextToken());
            if (major == null) {
                return null;
            }
            int creditCompleted;
            String creditInput = input.nextToken();
            boolean isNegative = creditInput.startsWith("-");
            boolean isInteger = true;
            for (int i = isNegative ? 1 : 0; i < creditInput.length(); i++) {
                if (!Character.isDigit(creditInput.charAt(i))) {
                    isInteger = false;
                    break;
                }
            }
            if (!isInteger) {
                System.out.println("Credits completed invalid: not an integer!");
                return null;
            }
            creditCompleted = Integer.parseInt(creditInput);
            if (isNegative) {
                System.out.println("Credits completed invalid: cannot be negative!");
                return null;
            }
            Student student = new International(profile, major, creditCompleted);
            return student;
        }
        if (input.countTokens() == COUNT_INTERNATIONAL_2) {
            String fname = input.nextToken();
            String lname = input.nextToken();
            Date dob = new Date(input.nextToken());
            if (!dob.isValid()) {
                return null;
            }
            Profile profile = new Profile(lname, fname, dob);
            Major major = getMajor(input.nextToken());
            if (major == null) {
                return null;
            }
            int creditCompleted;
            String creditInput = input.nextToken();
            boolean isNegative = creditInput.startsWith("-");
            boolean isInteger = true;
            for (int i = isNegative ? 1 : 0; i < creditInput.length(); i++) {
                if (!Character.isDigit(creditInput.charAt(i))) {
                    isInteger = false;
                    break;
                }
            }
            if (!isInteger) {
                System.out.println("Credits completed invalid: not an integer!");
                return null;
            }
            creditCompleted = Integer.parseInt(creditInput);
            if (isNegative) {
                System.out.println("Credits completed invalid: cannot be negative!");
                return null;
            }
            boolean isStudyAbroad = Boolean.parseBoolean(input.nextToken());
            checkInternationalValidity(creditCompleted, isStudyAbroad);
            Student student = new International(profile, major, creditCompleted,isStudyAbroad);
            return student;
        }
        return null;
    }
    /**
     * This method will add the newly created Resident object to the roster
     * Asks for user input to build the constructor
     * This will also check if the student is already in the roster or data is missing in the command
     *
     * @param roster to access the roster to add the object to the roster
     * @param input to process the tokens and store them into the constructor
     */
    private void addResident(Roster roster, StringTokenizer input) {
        try {
            if (input.countTokens() < 5) {
                throw new IllegalArgumentException();
            }
            Student studentToBeAdded = buildResidentStudent(input);
            if (studentToBeAdded == null) {
            } else if (!roster.add(studentToBeAdded)) {
                System.out.println("Student " + studentToBeAdded.getProfile() + " is already in the roster.");
            } else {
                System.out.println("Student " + studentToBeAdded.getProfile() + " has been added to the roster.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Missing data in command line.");
        }
    }
    /**
     * This method will add the newly created Non-Resident object to the roster
     * Asks for user input to build the constructor
     * This will also check if the student is already in the roster or data is missing in the command
     *
     * @param roster to access the roster to add the object to the roster
     * @param input to process the tokens and store them into the constructor
     */
    private void addNonResident(Roster roster, StringTokenizer input) {
        try {
            if (input.countTokens() < 5) {
                throw new IllegalArgumentException();
            }
            Student studentToBeAdded = buildNonResidentStudent(input);
            if (studentToBeAdded == null) {
            } else if (!roster.add(studentToBeAdded)) {
                System.out.println("Student " + studentToBeAdded.getProfile() + " is already in the roster.");
            } else {
                System.out.println("Student " + studentToBeAdded.getProfile() + " has been added to the roster.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Missing data in command line.");
        }
    }
    /**
     * This method will add the newly created addTristate object to the roster
     * Asks for user input to build the constructor
     * This will also check if the student is already in the roster or data is missing in the command
     *
     * @param roster to access the roster to add the object to the roster
     * @param input to process the tokens and store them into the constructor
     */
    private void addTristate(Roster roster, StringTokenizer input) {
        if (input.countTokens() < 5) {
            System.out.println("Missing data in command line.");
        }else if(input.countTokens() < 6){
            System.out.println("Missing the state code.");
        }
        Student studentToBeAdded = buildTriStateStudent(input);
        if (studentToBeAdded == null) {
        } else if (!roster.add(studentToBeAdded)) {
            System.out.println("Student " + studentToBeAdded.getProfile() + " is already in the roster.");
        } else {
            System.out.println("Student " + studentToBeAdded.getProfile() + " has been added to the roster.");
        }
    }
    /**
     * This method will add the newly created International object to the roster
     * Asks for user input to build the constructor
     * This will also check if the student is already in the roster or data is missing in the command
     *
     * @param roster to access the roster to add the object to the roster
     * @param input to process the tokens and store them into the constructor
     */
    private void addInternational(Roster roster, StringTokenizer input) {
        try {
            if (input.countTokens() < 5) {
                throw new IllegalArgumentException();
            }
            Student studentToBeAdded = buildInternationalStudent(input);
            if (studentToBeAdded == null) {
            } else if (!roster.add(studentToBeAdded)) {
                System.out.println("Student " + studentToBeAdded.getProfile() + " is already in the roster.");
            } else {
                System.out.println("Student " + studentToBeAdded.getProfile() + " has been added to the roster.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Missing data in command line.");
        }
    }
    /**
     * This method will build the constructor required to remove the student
     * Asks for user input to build the constructor to search which student they want to remove
     * This will also check if the student is in the roster or not
     *
     * @param input to process the tokens and store them into the constructor
     */
    private Student buildRemoveStudent(StringTokenizer input){
        if (input.countTokens() == COUNT_REMOVE) {
            String fname = input.nextToken();
            String lname = input.nextToken();
            Date dob = new Date(input.nextToken());
            if (!dob.isValid()) {
                return null;
            }
            Profile profile = new Profile(lname, fname, dob);
            Student student  = new Resident(profile);
            return student;
        }
        return null;
    }

    /**
     * Removes student from the Roster class array.
     * If student exist in the array it is deleted and the appropriate statement is printed.
     * If student is not in the array the appropriate statement is printed.
     *
     * @param roster object that correlates to the array
     * @param input  user student input
     */
    private void removeStudentFromRoster(Roster roster, StringTokenizer input) {
        Student studentToBeDeleted = buildRemoveStudent(input);
        if (studentToBeDeleted == null) {
            System.out.println("Error man");
        } else if (roster.remove(studentToBeDeleted)) {
            System.out.println(studentToBeDeleted.getProfile() + " removed from the roster.");
        } else {
            System.out.println(studentToBeDeleted.getProfile() + " is not in the roster.");
        }
    }
    /**
     * This class will list by school
     * If the input for school is invalid or not provided it will throw an error
     * will only print students from school provided
     *
     * @param roster object that correlates to the array
     * @param input  user student input
     */
    private void listBySchool(Roster roster, StringTokenizer input) {
        if (!input.hasMoreTokens()) {
            System.out.println("No school name provided.");
            return;
        }
        String schoolName = input.nextToken();
        schoolName = schoolName.toUpperCase();

        if(schoolName.equals("SAS") || schoolName.equals("SC&I")
                || schoolName.equals("RBS") || schoolName.equals("SOE")) {
            System.out.println("* Students in " + schoolName + " *");
            for (Student student : roster.getRoster()) {
                if (student != null && student.getMajor().getSchoolName().equals(schoolName)) {
                    System.out.println(student);
                }
            }
        } else {
            System.out.println("School doesn't exist: " + schoolName);
            return;
        }
        System.out.println("* end of list **");
    }

    /**
     * Allows user to enter major in lower-case and return the correlating Major type.
     * @param major string input.
     * @return the major data type.
     */
    private Major getMajor(String major) {
        switch (major.toLowerCase()) {
            case "cs":
                return Major.CS;
            case "math":
                return Major.MATH;
            case "iti":
                return Major.ITI;
            case "bait":
                return Major.BAIT;
            case "ee":
                return Major.EE;
            default:
                System.out.println("Major code invalid: " + major);
                return null;
        }
    }

    /**
     * This class will build the required constructor to build the student
     * If dob or major is invalid it will throw an error
     *
     * @param input  user student input to change
     */

    private Student buildChangeStudent(StringTokenizer input){
        if (input.countTokens() == COUNT_CHANGE) {
            String fname = input.nextToken();
            String lname = input.nextToken();
            Date dob = new Date(input.nextToken());
            if (!dob.isValid()) {
                return null;
            }
            Profile profile = new Profile(lname, fname, dob);
            Major major = getMajor(input.nextToken());
            if (major == null) {
                return null;
            }
            Student student  = new Resident(profile, major);
            return student;
        }
        return null;
    }

    /**
     * Removes student from the Roster class array.
     * If student exist in the array it's major is changed
     * If student is not in the array the appropriate statement is printed.
     * If student is not in the array the appropriate statement is printed.
     * @param roster object that correlates to the array
     * @param input  user student input
     */
    private void changeMajor(Roster roster, StringTokenizer input) {
        Student studentToChangeMajor = buildChangeStudent(input);
        if (studentToChangeMajor == null) {

        } else if (studentToChangeMajor != null) {
            String newMajor = String.valueOf(studentToChangeMajor.getMajor().name());
            if (roster.changeMajorCode(studentToChangeMajor, String.valueOf(newMajor))) {
                System.out.println(studentToChangeMajor.getProfile() + " major changed to " + newMajor);
            } else {
                System.out.println(studentToChangeMajor.getProfile() + " is not in the roster.");
            }
        }
    }
    /**
     * Enrolls students based on which instance its from
     * checks credits for each sub class
     * checks if student is in roster first
     *
     * @param input  user student input
     */
    private void enrollStudent(StringTokenizer input) {
        try {
            String fname = input.nextToken();
            String lname = input.nextToken();
            Date birthDate = new Date(input.nextToken());
            int credits = Integer.parseInt(input.nextToken());

            Profile profile = new Profile(lname, fname, birthDate);

            Student filler = new Resident(profile);
            if(!roster.contains(filler)){
                System.out.println("Cannot enroll: " + profile + " is not in the roster.");
                return;
            }
            for (Student student : roster.getRoster()) {
                if (student.getProfile().equals(profile)){
                    if (student instanceof Resident) {
                        EnrollStudent studentToBeEnrolled = new EnrollStudent(profile, credits);

                        if (credits < 3 || credits > 24) {
                            System.out.println("(Resident) " + credits + ": invalid credit hours.");
                            return;
                        }
                        if (enrollment.contains(studentToBeEnrolled)) {
                            EnrollStudent existingStudent = enrollment.get(studentToBeEnrolled);
                            existingStudent.setCreditsEnrolled(credits);
                        } else {
                            enrollment.add(studentToBeEnrolled);
                        }
                        System.out.println(studentToBeEnrolled.getProfile() + " enrolled " + credits + " credits");
                        return;
                    } else if (student instanceof International) {
                        EnrollStudent studentToBeEnrolled = new EnrollStudent(profile, credits);

                        if ((credits < 12 || credits > 24) && !(((International) student).isStudyAbroad())) {
                            System.out.println("(International student) " + credits + ": invalid credit hours.");
                            return;
                        } else if (credits < 3 || credits > 12 && (((International) student).isStudyAbroad())) {
                            System.out.println("(International student: study abroad) " + credits + ": invalid credit hours.");
                            return;
                        }
                        if (enrollment.contains(studentToBeEnrolled)) {
                            EnrollStudent existingStudent = enrollment.get(studentToBeEnrolled);
                            existingStudent.setCreditsEnrolled(credits);
                        } else {
                            enrollment.add(studentToBeEnrolled);
                        }
                        System.out.println(studentToBeEnrolled.getProfile() + " enrolled " + credits + " credits");
                        return;
                    } else if (student instanceof NonResident) {
                        EnrollStudent studentToBeEnrolled = new EnrollStudent(profile, credits);

                        if (credits < 3 || credits > 24) {
                            System.out.println("(Non-Resident) " + credits + ": invalid credit hours.");
                            return;
                        }
                        if (enrollment.contains(studentToBeEnrolled)) {
                            EnrollStudent existingStudent = enrollment.get(studentToBeEnrolled);
                            existingStudent.setCreditsEnrolled(credits);
                        } else {
                            enrollment.add(studentToBeEnrolled);
                        }
                        System.out.println(studentToBeEnrolled.getProfile() + " enrolled " + credits + " credits");
                        return;
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Credits enrolled is not an integer.");
        } catch (Exception e) {
            System.out.println("Missing data in line command.");
        }
    }

    /**
     * Drops student from enrollment list
     * checks if student is enrolled
     * prompts message if dropped
     *
     * @param input  user student input
     */
    private void dropStudent(StringTokenizer input) {
        String fname = input.nextToken();
        String lname = input.nextToken();
        Date birthDate = new Date(input.nextToken());

        Profile profile = new Profile(lname, fname, birthDate);
        EnrollStudent studentToBeDropped = new EnrollStudent(profile);
        if (!enrollment.contains(studentToBeDropped)){
            System.out.println(studentToBeDropped.getProfile() + " is not enrolled");
        }
        else if(enrollment.contains(studentToBeDropped)){
            enrollment.remove(studentToBeDropped);
            System.out.println(studentToBeDropped.getProfile() + " dropped.");
        }

    }
    /**
     * This will print the enrolled student's tuition based on what student they are
     * checks if student is enrolled
     * this will compare the student from the roster to get their reference
     */
    public void printTuition() {
        double totalTuition = 0.0;
        DecimalFormat df = new DecimalFormat("#,##0.00");
        System.out.println("** Tuition due **");
        for (EnrollStudent student : enrollment.getEnrollment()) {
            for(Student student1 : roster.getRoster()) {
                if(student1 != null && student != null && student.getProfile().equals(student1.getProfile())) {
                    double tuition = student1.tuitionDue(student.getCreditsEnrolled());
                    student1.tuitionDue(student.getCreditsEnrolled());
                    System.out.println(student.getProfile() + " enrolled " + student.getCreditsEnrolled() + " credits: " + "tuition due: $" + df.format(tuition));

                }
            }
        }
        System.out.println("* end of tuition due *");
    }

    /**
     * This will print the enrolled student's tuition based on what student they are
     * checks if student is a resident and if amount of earning is valid
     * student form the roster and enrollment to compare
     *
     * @param input to award the student entered
     */
    private void awardScholarship(StringTokenizer input) {
        try {
            // Parse the input tokens.
            String fname = input.nextToken();
            String lname = input.nextToken();
            Date dob = new Date(input.nextToken());
            int amount = Integer.parseInt(input.nextToken());

            // Create a profile object from the parsed input.
            Profile profile = new Profile(lname, fname, dob);
            // Find the student in the roster.
            Student student = roster.get(profile);
            if (student == null) {
                System.out.println(profile + " is not in the roster.");
                return;
            }
            // Check if the student is eligible for a scholarship.
            if (!(student instanceof Resident)) {
                System.out.println(student.getProfile() + " is not a resident student and is not eligible for a scholarship.");
                return;
            }
            // Award the scholarship to the student.
            Resident resident = (Resident) student;
            EnrollStudent enrollStudent = enrollment.get(profile);
            if (amount <= 0) {
                System.out.println("Invalid scholarship amount: " + amount);
                return;
            } else if (amount > Resident.MAX_FINANCIAL_AID_AMOUNT) {
                System.out.println("Invalid scholarship amount: " + amount);
                return;
            }
            if(enrollStudent.getCreditsEnrolled() < 12){
                System.out.println("Part-time students are not eligible for scholarship");
                return;
            }

            resident.setScholarship(amount);

            // Print the new tuition due for the student.
            if (enrollStudent == null) {
                System.out.println("No enrollment found for " + profile + ".");
                return;
            }
            double tuition = resident.tuitionDue(enrollStudent.getCreditsEnrolled());
            System.out.println(resident.getProfile() + " :scholarship amount updated.");
        } catch (NoSuchElementException e) {
            System.out.println("Missing data in line command.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while processing the scholarship award: " + e.getMessage());
        }
    }
    /**
     * This will just print the students whose credits are over 120
     * if they are then they will be printed
     */
    private void semesterEnd() {
        System.out.println("** list of students eligible for graduation **");
        for(EnrollStudent student : enrollment.getEnrollment()){
            for(Student student1 : roster.getRoster()){
                if(student != null && student1 != null &&
                        student.getProfile().equals(student1.getProfile()) &&
                        (student1.getCreditCompleted() + student.getCreditsEnrolled() > 119)){
                    student1.setCreditCompleted(student1.getCreditCompleted() + student.getCreditsEnrolled());
                    System.out.println(student1);
                    break;
                }
            }
        }
    }

    /**
     * Validates if student is a TriState students based on residence in NY or CT.
     * @param state provided by the user
     * @return true if states are NY or CT, false if not.
     */
    private boolean checkTriStateValidity(String state) {
        return state.equals("NY") || state.equals("CT");
    }

    /**
     * Methods checks if International student is part of the study abroad program.
     * @param credits given by user input of student information
     * @param studyingAbroad given by user input of student information
     * @return true if student is an international student part of the study abroad program,
     * otherwise returns false.
     */
    private boolean checkInternationalValidity(int credits, boolean studyingAbroad) {
        if (studyingAbroad)
            return true;
        return credits >= Student.MIN_FULLTIME_CREDITS;
    }

    /**
     * Terminates program and prints termination statement.
     */
    private static void endRoasterManager() {
        System.out.println("Tuition Manager terminated.");
        System.exit(0);
    }
}