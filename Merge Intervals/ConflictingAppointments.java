import java.util.Arrays;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class ConflictingAppointments {
    // O(n logn) time | O(1) space
    // wher n is the number of appointments
    public static boolean canAttendAllAppointments(Interval[] appointments) {
        // base checks
        if (appointments == null || appointments.length == 0) {
            return false;
        }

        // sort the appintments by start time
        Arrays.sort(appointments, (a, b) -> Integer.compare(a.start, b.start));

        // oterate through each appointment and look for conflicts
        for (int i = 1; i < appointments.length; i++) {
            if (appointments[i].start < appointments[i - 1].end) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Interval[] intervals = { new Interval(1, 4), new Interval(2, 5),
                new Interval(7, 9) };
        boolean result = ConflictingAppointments.canAttendAllAppointments(intervals);
        System.out.println("Can attend all appointments: " + result);

        Interval[] intervals1 = { new Interval(6, 7), new Interval(2, 4),
                new Interval(8, 12) };
        result = ConflictingAppointments.canAttendAllAppointments(intervals1);
        System.out.println("Can attend all appointments: " + result);

        Interval[] intervals2 = { new Interval(4, 5), new Interval(2, 3),
                new Interval(3, 6) };
        result = ConflictingAppointments.canAttendAllAppointments(intervals2);
        System.out.println("Can attend all appointments: " + result);
    }
}