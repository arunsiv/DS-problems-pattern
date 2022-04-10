import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SubarrayProductLessThanK {
    //O(N^3) time | O(N^3)
    //where N is the length of the input string
    //time: O(N) for for loop and O(N^2) for creating subarrays in the worst case
    public static List<List<Integer>> findSubarrays(int[] nums, int k) {
        // base checks
        if (k < 0 || nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        List<List<Integer>> subArrays = new ArrayList<>();
        double product = 1;
        int left = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            // multiple the current element with the product
            product *= nums[right];

            // if the product is greater than k, adjust the window
            while (product >= k && left <= right) {
                product /= nums[left++];
            }

            // add the window length
            count += right - left + 1;

            // since the product of all numbers from left to right is less than the k.
            // therefore, all subarrays from left to right will have a product less than the
            // k too.
            // so, to avoid duplicates, we will start with a subarray containing only
            // nums[right] and then extend it
            List<Integer> tempSubarray = new LinkedList<>();
            for (int i = right; i >= left; i--) {
                tempSubarray.add(0, nums[i]);
                subArrays.add(new ArrayList<>(tempSubarray));
            }
        }

        System.out.println("Total no. of subarrays with product less than K is : " + count);
        return subArrays;
    }

    public static void main(String[] args) {
        System.out.println(
                SubarrayProductLessThanK.findSubarrays(new int[] { 2, 5, 3, 10 }, 30));
        System.out.println(
                SubarrayProductLessThanK.findSubarrays(new int[] { 10, 5, 2, 6 }, 100));
        System.out.println(
                SubarrayProductLessThanK.findSubarrays(new int[] { 8, 2, 6, 5 }, 50));
        System.out.println(
                SubarrayProductLessThanK.findSubarrays(new int[] { 1, 2, 3 }, 0));
        System.out.println(
                SubarrayProductLessThanK.findSubarrays(new int[] { 1, 1, 1 }, 1));
    }
}