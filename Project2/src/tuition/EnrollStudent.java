package tuition;

/**
 * @author Sharukh Khan, Hamad Naveed
 */

public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    public EnrollStudent(Profile profile){
        this.profile = profile;
    }

    public EnrollStudent(Profile profile, int creditsEnrolled){
        this.profile = profile;
        this.creditsEnrolled = creditsEnrolled;

    }

    public Profile getProfile() {
        return profile;
    }


    public void setCreditsEnrolled(int newCreditsEnrolled) {
        this.creditsEnrolled = newCreditsEnrolled;
    }

    public int getCreditsEnrolled() {
        return this.creditsEnrolled;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EnrollStudent)) {
            return false;
        }
        EnrollStudent other = (EnrollStudent) obj;
        return this.profile.equals(other.profile);
    }

    @Override
    public String toString() {
        return profile + ": credits enrolled: " + creditsEnrolled;
    }


}