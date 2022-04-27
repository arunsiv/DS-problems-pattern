import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class InsertIntervals {
    // O(N) time | O(N) time
    // where N is the length of the intervals
    public static List<Interval> insertInterval(List<Interval> intervals, Interval newInterval) {
        // base checks
        if (newInterval == null) {
            return intervals;
        }

        if (intervals == null || intervals.isEmpty()) {
            return Arrays.asList(newInterval);
        }

        List<Interval> mergeIntervals = new ArrayList<>();
        // set the start and end of new interval to a variable
        int start = newInterval.start, end = newInterval.end;
        // initizalize count
        int count = 0;

        // add all the intervals that come before the new interval
        while (count < intervals.size() && intervals.get(count).end < start) {
            mergeIntervals.add(intervals.get(count));
            count++;
        }

        // check for overlapping and merge all the intervals that overlaps with the new
        // interval
        while (count < intervals.size() && intervals.get(count).start <= end) {
            start = Math.min(start, intervals.get(count).start);
            end = Math.max(end, intervals.get(count).end);
            count++;
        }

        // add the merged new intervals to the output
        mergeIntervals.add(new Interval(start, end));

        // add the remaining intervalss
        while (count < intervals.size()) {
            mergeIntervals.add(intervals.get(count));
            count++;
        }

        return mergeIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertIntervals.insertInterval(input, new Interval(4, 6)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertIntervals.insertInterval(input, new Interval(4, 10)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(2, 3));
        input.add(new Interval(5, 7));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertIntervals.insertInterval(input, new Interval(1, 4)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(3, 4));
        input.add(new Interval(5, 7));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertIntervals.insertInterval(input, new Interval(1, 6)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
    }
}