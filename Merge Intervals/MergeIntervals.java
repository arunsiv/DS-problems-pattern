import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class MergeIntervals {
    // O(nlogn) time | O(n) space
    // n is the lengh of the intervals
    public static List<Interval> mergeIntervals(List<Interval> intervals) {
        // base checks
        if (intervals.size() < 2) {
            return intervals;
        }

        // sort the intervals based on the start value
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        // output list
        List<Interval> mergedIntervals = new ArrayList<>();

        // initializde the iterator
        Iterator<Interval> intervaIterator = intervals.iterator();

        // get the first interval
        Interval interval = intervaIterator.next();

        // store the start and end of first interval
        int start = interval.start;
        int end = interval.end;

        while (intervaIterator.hasNext()) {
            // get the next interval
            interval = intervaIterator.next();

            // check if there is an overlap in the current and prev interval
            if (interval.start <= end) {
                // overlap found
                // extend the end
                end = Math.max(end, interval.end);
            } else {
                // no overlap found
                // add the interval to the output
                mergedIntervals.add(new Interval(start, end));

                // set the current interval's start and end to the variables
                start = interval.start;
                end = interval.end;
            }
        }

        // add the last interval to the output
        mergedIntervals.add(new Interval(start, end));

        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 5));
        input.add(new Interval(7, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.mergeIntervals(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(6, 7));
        input.add(new Interval(2, 4));
        input.add(new Interval(5, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.mergeIntervals(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 6));
        input.add(new Interval(3, 5));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.mergeIntervals(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
    }
}