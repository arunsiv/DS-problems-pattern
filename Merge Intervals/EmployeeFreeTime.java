import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
};

public class EmployeeFreeTime {
    // O(n logn) time | O(n) space
    // where n is the number of schedule
    public static List<Interval> findEmployeeFreeTime(List<List<Interval>> schedule) {
        // base checks
        if (schedule == null || schedule.size() == 0) {
            return null;
        }

        List<Interval> employeeSchedule = new ArrayList<>();
        List<Interval> result = new ArrayList<>();

        // Add all employee schedule to a list
        schedule.forEach(interval -> employeeSchedule.addAll(interval));

        // sort the schedule by start time
        Collections.sort(employeeSchedule, (a, b) -> Integer.compare(a.start, b.start));

        // Iterate through the list and find non overlapping intervals
        Interval prevInterval = employeeSchedule.get(0);
        for (Interval currentInterval: employeeSchedule) {
            // if there is no pverlap, add the gap to the result
            if (prevInterval.end < currentInterval.start) {
                result.add(new Interval(prevInterval.end, currentInterval.start));
                prevInterval = currentInterval;
            } else {
                prevInterval = prevInterval.end < currentInterval.end ? currentInterval : prevInterval;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Interval>> input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3),
                new Interval(5, 6))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 3),
                new Interval(6, 8))));
        List<Interval> result = EmployeeFreeTime.findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3),
                new Interval(9, 12))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(6, 8))));
        result = EmployeeFreeTime.findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(3, 5),
                new Interval(7, 9))));
        result = EmployeeFreeTime.findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3),
                new Interval(3, 5))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(3, 5),
                new Interval(5, 7))));
        result = EmployeeFreeTime.findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();
    }
}