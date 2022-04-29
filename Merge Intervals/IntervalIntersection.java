import java.util.ArrayList;
import java.util.List;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class IntervalIntersection {
    // O(m+n) time | O(m+n) space
    // m and n are length of the 2 input arrays
    public static Interval[] findIntersection(Interval[] arr1, Interval[] arr2) {
        List<Interval> result = new ArrayList<>();
        int m = arr1.length, n = arr2.length;
        int i = 0, j = 0;

        // iterate through both the arrays
        while (i < m && j < n) {
            Interval a = arr1[i];
            Interval b = arr2[j];

            // check for overlapping using criss cross points
            if (a.start <= b.end && b.start <= a.end) {
                // store the intersection part
                Interval intersection = new Interval(Math.max(a.start, b.start), Math.min(a.end, b.end));
                result.add(intersection);
            }

            // whichever interval ends first, move to the next one
            if (a.end <= b.end) {
                i++;
            } else {
                j++;
            }
        }

        Interval[] resultArray = new Interval[result.size()];
        return result.toArray(resultArray);
    }

    public static void main(String[] args) {
        Interval[] input1 = new Interval[] { new Interval(1, 3), new Interval(5, 6),
                new Interval(7, 9) };
        Interval[] input2 = new Interval[] { new Interval(2, 3), new Interval(5, 7) };
        Interval[] result = IntervalIntersection.findIntersection(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input1 = new Interval[] { new Interval(1, 3), new Interval(5, 7),
                new Interval(9, 12) };
        input2 = new Interval[] { new Interval(5, 10) };
        result = IntervalIntersection.findIntersection(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input1 = new Interval[] { new Interval(0, 2), new Interval(5, 10),
                new Interval(13, 23), new Interval(24, 25) };
        input2 = new Interval[] { new Interval(1, 5), new Interval(8, 12),
                new Interval(15, 24), new Interval(25, 26) };
        result = IntervalIntersection.findIntersection(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
    }
}
