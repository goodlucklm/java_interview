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

    public static void main(String[] args) {
        Permutations pmts = new Permutations();
        pmts.nextPermutation(new int[]{1,2});
    }
}
