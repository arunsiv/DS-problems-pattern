import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class EmployeeInterval {
    Interval interval; // interval representing employee's working hours
    int employeeIndex; // index of the list containing working hours of this employee
    int intervalIndex; // index of the interval in the employee list

    public EmployeeInterval(Interval interval, int employeeIndex, int intervalIndex) {
        this.interval = interval;
        this.employeeIndex = employeeIndex;
        this.intervalIndex = intervalIndex;
    }
}

public class EmployeeFreeTime {
    // O(n logn) time | O(n) space
    // where n is the number of intervals
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
        for (Interval currentInterval : employeeSchedule) {
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

    // O(n * logk) time | O(k) space
    // n is the number of intervals
    // k is the number of employees
    public static List<Interval> findEmployeeFreeTimePriorityQueue(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        // PriorityQueue to store one interval from each employee
        PriorityQueue<EmployeeInterval> minHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.interval.start, b.interval.start));

        // insert the first interval of each employee to the queue
        for (int i = 0; i < schedule.size(); i++) {
            minHeap.offer(new EmployeeInterval(schedule.get(i).get(0), i, 0));
        }

        // for (EmployeeInterval employeeInterval : minHeap) {
        //     System.out.print("[" + employeeInterval.interval.start + "," + employeeInterval.interval.end + "]\t");
        // }
        // System.out.println();

        Interval previousInterval = minHeap.peek().interval;

        while (!minHeap.isEmpty()) {
            for (EmployeeInterval employeeInterval : minHeap) {
                System.out.print("[" + employeeInterval.interval.start + "," + employeeInterval.interval.end + "]\t");
            }
            System.out.println();

            EmployeeInterval queueTop = minHeap.poll();

            System.out.println("PI: " + previousInterval.start + "," + previousInterval.end);
            System.out.println("CI: " + queueTop.interval.start + "," + queueTop.interval.end);

            // if previousInterval is not overlapping with the next interval, insert a free
            // interval
            if (previousInterval.end < queueTop.interval.start) {
                result.add(new Interval(previousInterval.end, queueTop.interval.start));
                previousInterval = queueTop.interval;
            } else {
                // overlapping intervals, update the previousInterval if needed
                if (previousInterval.end < queueTop.interval.end) {
                    System.out.println("--->");
                    previousInterval = queueTop.interval;
                }
            }

            // if there are more intervals available for the same employee, add their next
            // interval
            List<Interval> employeeSchedule = schedule.get(queueTop.employeeIndex);
            if (employeeSchedule.size() > queueTop.intervalIndex + 1) {
                System.out.println("---?");
                minHeap.offer(new EmployeeInterval(
                        employeeSchedule.get(queueTop.intervalIndex + 1), queueTop.employeeIndex,
                        queueTop.intervalIndex + 1));
            }

            System.out.println("-----");
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
        //System.out.print("Free intervals: ");
        for (Interval interval : result)
            //System.out.print("[" + interval.start + ", " + interval.end + "] ");
        //System.out.println();

        // input = new ArrayList<>();
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3),
        //         new Interval(9, 12))));
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(6, 8))));
        // result = EmployeeFreeTime.findEmployeeFreeTime(input);
        // System.out.print("Free intervals: ");
        // for (Interval interval : result)
        //     System.out.print("[" + interval.start + ", " + interval.end + "] ");
        // System.out.println();

        // input = new ArrayList<>();
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3))));
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(3, 5),
        //         new Interval(7, 9))));
        // result = EmployeeFreeTime.findEmployeeFreeTime(input);
        // System.out.print("Free intervals: ");
        // for (Interval interval : result)
        //     System.out.print("[" + interval.start + ", " + interval.end + "] ");
        // System.out.println();

        // input = new ArrayList<>();
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3),
        //         new Interval(3, 5))));
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(3, 5),
        //         new Interval(5, 7))));
        // result = EmployeeFreeTime.findEmployeeFreeTime(input);
        // System.out.print("Free intervals: ");
        // for (Interval interval : result)
        //     System.out.print("[" + interval.start + ", " + interval.end + "] ");
        // System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3),
                new Interval(5, 6))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4),
                new Interval(6, 8))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 3),
                new Interval(7, 9))));
        result = EmployeeFreeTime.findEmployeeFreeTimePriorityQueue(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        // input = new ArrayList<>();
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3),
        //         new Interval(9, 12))));
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(6, 8))));
        // result = EmployeeFreeTime.findEmployeeFreeTimePriorityQueue(input);
        // System.out.print("Free intervals: ");
        // for (Interval interval : result)
        //     System.out.print("[" + interval.start + ", " + interval.end + "] ");
        // System.out.println();

        // input = new ArrayList<>();
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3))));
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(3, 5),
        //         new Interval(7, 9))));
        // result = EmployeeFreeTime.findEmployeeFreeTimePriorityQueue(input);
        // System.out.print("Free intervals: ");
        // for (Interval interval : result)
        //     System.out.print("[" + interval.start + ", " + interval.end + "] ");
        // System.out.println();

        // input = new ArrayList<>();
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3),
        //         new Interval(3, 5))));
        // input.add(new ArrayList<Interval>(Arrays.asList(new Interval(3, 5),
        //         new Interval(5, 7))));
        // result = EmployeeFreeTime.findEmployeeFreeTimePriorityQueue(input);
        // System.out.print("Free intervals: ");
        // for (Interval interval : result)
        //     System.out.print("[" + interval.start + ", " + interval.end + "] ");
        // System.out.println();
    }
}