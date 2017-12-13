import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {
    public void nextPermutation(int[] nums) {
        int n = nums.length, tmp, i, j, count = 0;
        if (n < 2)
            return;
        if (n == 2) {
            reverseArray(nums, 0, n - 1);
            return;
        }
        for (i = n-1; i > 0; i--) {
            if (nums[i-1] < nums[i]) {
                break;
            }
        }

        for (j = n-1; j >=i && i > 0; j--) {
            if (nums[j] > nums[i-1]) {
                tmp = nums[j];
                nums[j] = nums[i-1];
                nums[i-1] = tmp;
                break;
            }
        }

        reverseArray(nums, i, n-1);
    }

    private void reverseArray(int[] a, int start, int end) {
        for (int i = 0; i <= (end-start)/2; i++) {
            int tmp = a[start+i];
            a[start+i] = a[end-i];
            a[end-i] = tmp;
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        int numOfPermutation = 1;
        for (int i = n; i > 0; i--) {
            numOfPermutation *= i;
        }

        for (int i = 0; i < numOfPermutation; i++) {
            nextPermutation(nums);
            List<Integer> current = new ArrayList<>();
            for (int j : nums)
                current.add(j);
            res.add(current);
        }
        return res;
    }

    public static void main(String[] args) {
        Permutations pmts = new Permutations();
        pmts.permute(new int[]{1,2,3});
    }
}
