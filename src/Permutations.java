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

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        if (null == nums)
            return res;
        int n = nums.length;
        if (n == 0)
            return res;

        // init before loop
        curr.add(nums[0]);
        res.add(curr);

        for (int i = 1; i < n; i++) {
            List<List<Integer>> newRes = new ArrayList<>();
            for (List<Integer> L : res) {
                int s = L.size();
                for (int j = 0; j <= s; j++) {
                    curr = new ArrayList<>(L);
                    curr.add(j, nums[i]);
                    newRes.add(curr);
                    if (j < s && L.get(j) == nums[i])
                        break;
                }
            }
            res = newRes;
        }

        return res;
    }

    public static void main(String[] args) {
        Permutations pmts = new Permutations();
        pmts.permuteUnique(new int[]{1,2,1});
    }
}
