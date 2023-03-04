package tuition;

import org.junit.Test;

import static org.junit.Assert.*;

public class RosterTest {

    @Test
    public void testAdd() {
        Roster roster = new Roster();
        Date dob = new Date("9/13/2001");
        Profile profile = new Profile("doe", "John", dob);
        Student student1 = new Resident(profile, Major.CS, 1);
        assertTrue(roster.add(student1));
        assertEquals(1, roster.getSize());
        Student student2 = roster.get(profile);
        assertEquals(student1, student2);

    }

    @Test
    public void testRemove() {
        Roster roster = new Roster();
        Date dob1 = new Date("9/13/2001");
        Profile profile1 = new Profile("doe", "John", dob1);
        Student student1 = new Resident(profile1, Major.CS, 1);
        assertTrue(roster.add(student1));
        assertEquals(1, roster.getSize());

        Date dob2 = new Date("6/1/2002");
        Profile profile2 = new Profile("smith", "Jane", dob2);
        Student student2 = new NonResident(profile2, Major.BAIT, 3);
        assertTrue(roster.add(student2));
        assertEquals(2, roster.getSize());

        assertTrue(roster.remove(student2));
        assertEquals(1, roster.getSize());
        assertNull(roster.get(profile1));
        assertEquals(student2, roster.get(profile2));
    }
}