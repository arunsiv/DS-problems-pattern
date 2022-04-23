public class CycleArrayLoop {
    // O(N^2) time | O(1) space
    // where N is the number of elements in the array
    public static boolean loopExists(int[] nums) {
        // base checks
        if (nums == null || nums.length < 2) {
            return false;
        }

        // iternate through each element and check if there is a loop
        for (int i = 0; i < nums.length; i++) {
            // declare the pointers
            int slowPointer = i, fastPointer = i;

            // get the direction
            boolean isForward = (nums[i] > 0);

            //check if there is a loop from this element
            do {
                // move slow pointer by 1 step
                slowPointer = findNextIndex(nums, isForward, slowPointer);

                // move fast pointer by 1 step
                fastPointer = findNextIndex(nums, isForward, fastPointer);

                // move fast pointer by another step if its not -1
                if (fastPointer != -1) {
                    fastPointer = findNextIndex(nums, isForward, fastPointer);
                }

            } while (slowPointer != -1 && fastPointer != -1 && slowPointer != fastPointer);

            // check if there is a loop
            if (slowPointer != -1 && slowPointer == fastPointer) {
                return true;
            }
        }

        return false;
    }

    private static int findNextIndex(int[] nums, boolean isForward, int currentIndex) {
        boolean direction = (nums[currentIndex] > 0);

        // check for change in direction of the loop
        if (isForward != direction) {
            return -1;
        }

        // get the next index
        int nextIndex = (currentIndex + nums[currentIndex]) % nums.length;
        //System.out.println(currentIndex + ":" + nextIndex);

        // wrap around for -ve numbers
        if (nextIndex < 0) {
            nextIndex += nums.length;
        }

        // check for one element cycle
        if (nextIndex == currentIndex) {
            return -1;
        }

        return nextIndex;
    }

    // O(N) time | O(1) space
    // where N is the number of elements in the array
    public static boolean circularArrayLoop(int[] nums) {
        int[] color = new int[nums.length];
        for(int i = 0 ; i < nums.length ; i++) {
            if(color[i] == 0 && DFS(nums, color, i)) return true;
        }
        return false;
    }

    private static boolean DFS(int[] nums, int[] color, int start) {
        //return true if find cycle
        if(color[start] == 2) return false;
        color[start] = 1;
        int next = start + nums[start];
        next = next % nums.length + nums.length;
        next %= nums.length;
        if(next == start || nums[next] * nums[start] < 0) {
            color[start] = 2;
            return false;
        }
        if(color[next] == 1) {
            color[start] = 2;
            return true;
        }
        if(DFS(nums, color, next)) return true;
        color[start] = 2;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(CycleArrayLoop.loopExists(new int[] { 1, 2, -1, 2, 2 }));
        System.out.println(CycleArrayLoop.circularArrayLoop(new int[] { 1, 2, -1, 2, 2 }));
        System.out.println(CycleArrayLoop.loopExists(new int[] { -2, -3, -9 }));
        System.out.println(CycleArrayLoop.circularArrayLoop(new int[] { -2, -3, -9 }));
    }
}