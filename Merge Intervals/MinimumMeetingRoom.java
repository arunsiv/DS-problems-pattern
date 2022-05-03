import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Meeting {
    int start;
    int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
};

public class MinimumMeetingRoom {
    // O(n logn) time | O(n) space
    // where n is the number of meetings
    public static int findMinimumMeetingRooms(List<Meeting> meetings) {
        // base checks
        if (meetings == null || meetings.size() == 0) {
            return 0;
        }

        int[] start = new int[meetings.size()];
        int[] end = new int[meetings.size()];

        // store the start and end time of meetings in a seperate array
        for (int i = 0; i < meetings.size(); i++) {
            start[i] = meetings.get(i).start;
            end[i] = meetings.get(i).end;
        }

        // sort the start and end times
        Arrays.sort(start);
        Arrays.sort(end);

        // for (int i : start) {
        // System.out.print(i + "\t");
        // }
        // System.out.println("");

        // for (int i : end) {
        // System.out.print(i + "\t");
        // }
        // System.out.println("");

        int startPointer = 0, endPointer = 0, count = 0, maxRoom = 0;

        while (startPointer < meetings.size() && endPointer < meetings.size()) {
            // check if start is < end
            if (start[startPointer] < end[endPointer]) {
                count++;
                startPointer++;
            } else {
                count--;
                endPointer++;
            }

            // determine how many meeting rooms are required
            maxRoom = Math.max(count, maxRoom);
        }

        return maxRoom;
    }

    // O(n logn) time | O(n) space
    // where n is the number of meetings
    public static int findMinimumMeetingRoomsUsingPriorityQueue(List<Meeting> meetings) {
        // base checks
        if (meetings == null || meetings.size() == 0) {
            return 0;
        }

        // sort the input by start time
        Collections.sort(meetings, (a, b) -> Integer.compare(a.start, b.start));

        int minRoomsRequired = 0;

        // create a priority queue which stores the meeting that ends first at the
        // top(sort by end time)
        PriorityQueue<Meeting> minHeap = new PriorityQueue<>(meetings.size(), (a, b) -> Integer.compare(a.end, b.end));

        for (Meeting meeting : meetings) {
            // remove all the meetings that have ended
            while (!minHeap.isEmpty() && meeting.start >= minHeap.peek().end) {
                minHeap.poll();
            }

            // add the current meeting to the pq
            minHeap.add(meeting);

            // all active meeting are in the minHeap, so we need rooms for all of them.
            minRoomsRequired = Math.max(minRoomsRequired, minHeap.size());
        }

        return minRoomsRequired;
    }

    public static void main(String[] args) {
        List<Meeting> input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 5));
                add(new Meeting(7, 9));
            }
        };
        int result = MinimumMeetingRoom.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(6, 7));
                add(new Meeting(2, 4));
                add(new Meeting(8, 12));
            }
        };
        result = MinimumMeetingRoom.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 3));
                add(new Meeting(3, 6));
            }
        };
        result = MinimumMeetingRoom.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(0, 30));
                add(new Meeting(10, 30));
                add(new Meeting(15, 25));
                add(new Meeting(20, 30));
                add(new Meeting(20, 30));
            }
        };
        result = MinimumMeetingRoom.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 5));
                add(new Meeting(7, 9));
            }
        };
        result = MinimumMeetingRoom.findMinimumMeetingRoomsUsingPriorityQueue(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(6, 7));
                add(new Meeting(2, 4));
                add(new Meeting(8, 12));
            }
        };
        result = MinimumMeetingRoom.findMinimumMeetingRoomsUsingPriorityQueue(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 3));
                add(new Meeting(3, 6));
            }
        };
        result = MinimumMeetingRoom.findMinimumMeetingRoomsUsingPriorityQueue(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(0, 30));
                add(new Meeting(10, 30));
                add(new Meeting(15, 25));
                add(new Meeting(20, 30));
                add(new Meeting(20, 30));
            }
        };
        result = MinimumMeetingRoom.findMinimumMeetingRoomsUsingPriorityQueue(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(0, 10));
                add(new Meeting(5, 20));
                add(new Meeting(25, 30));
            }
        };
        result = MinimumMeetingRoom.findMinimumMeetingRoomsUsingPriorityQueue(input);
        System.out.println("Minimum meeting rooms required: " + result);
    }
}