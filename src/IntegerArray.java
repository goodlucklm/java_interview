public class IntegerArray {
    public int findDuplicateLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
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

    public static void main(String[] args) {
        IntegerArray ia = new IntegerArray();
        System.out.println(ia.findDuplicateFastSlow(new int[]{1,3,4,2,2}));
    }
}
