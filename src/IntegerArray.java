import java.util.ArrayList;
import java.util.List;

public class IntegerArray {
    public int findDuplicateLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] == nums[j]) return nums[i];
            }
        }
        return 0;
    }

    public int findDuplicateFastSlow(int[] nums) {
        int fast, slow;
        // assume length of nums larger than 1 according to question
        fast = nums[nums[0]];
        slow = nums[0];
        while (slow != fast) {
            fast = nums[nums[fast]];
            slow = nums[slow];
        }
        fast = 0;
        while (slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return fast;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> results = new ArrayList<>();
        for (int n : nums) {
            if (nums[Math.abs(n)-1] > 0) nums[Math.abs(n)-1] *= -1;
        }
        int count = 0;
        for (int n : nums) {
            if (n > 0) results.add(count+1);
            count++;
        }
        return results;
    }

    public int removeDuplicates(int[] nums) {
        int n = nums.length, count = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i-1]) {
                nums[count++] = nums[i];
            }
        }
        return count;
    }

    public int removeElement(int[] nums, int val) {
        int n = nums.length, count = 0, i = n, p;
        while (i > 0) {
            p = i-1-count;
            while (p >= 0 && nums[p] == val) {
                count++;
                p = i-1-count;
            }
            if (count > 0) {
                for (int j = i-count; j < n-count; j++) {
                    nums[j] = nums[j+count];
                }
                n -= count;
                i -= count;
                count = 0;

            }
            i--;
        }
        return n;
    }

    public static void main(String[] args) {
        IntegerArray ia = new IntegerArray();
        System.out.println(ia.removeElement(new int[]{3,3}, 3));
    }
}
