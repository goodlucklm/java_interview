import java.util.*;

public class NIntegerSumSorted {
    private void findNSum(int[] inputs, int degree, int target, int curr, List<Integer> path, List<List<Integer>> results) {
        // input array too short
        if (inputs.length < degree) return;
        if (inputs.length == degree) {
            int sum = 0;
            for (int n : inputs)
                sum += n;
            if (sum == target) {
                results.add(new ArrayList<>());
                for (int n : inputs)
                    results.get(0).add(n);
            }
            return;
        }

        // target not reachable
        int len = inputs.length;
        int max = inputs[len - 1], min = inputs[0];
        if (max * degree < target) return;
        if (min * degree > target) return;

        if (degree > 2) {
            for (int i = curr; i < inputs.length - (degree - 1); i++) {
                int current = inputs[i];
                if (i > 0 && current == inputs[i - 1]) continue; // duplicated item as the one before
                if ((current + (degree - 1) * max) < target) continue; // current too small
                if ((current + (degree - 1) * min) > target) continue; // current too big
                if (null == path)
                    path = new ArrayList<>();
                path.add(current);
                findNSum(inputs, degree - 1, target - current, i + 1, path, results);
                path.remove(path.size() - 1);
            }
        } else { // degree == 2, twoSum
            int left = curr, right = len - 1;
            while (left < right) { // this condition covers right == curr
                int left_value = inputs[left];
                int right_value = inputs[right];
                int sum = left_value + right_value;
                if (sum == target) {
                    List<Integer> L = new ArrayList<>(path);
                    L.add(inputs[left]);
                    L.add(inputs[right]);
                    Collections.sort(L);
                    results.add(L);
                    while (left_value == inputs[left] && left < right)
                        left++;
                    while (right_value == inputs[right] && left < right)
                        right--;
                } else {
                    if (sum < target) {
                        while (left_value == inputs[left] && left < right)
                            left++;
                    } else {
                        while (right_value == inputs[right] && left < right)
                            right--;
                    }
                }
            }
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        findNSum(nums, 4, target, 0, null, null);
        return results;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        findNSum(nums, 3, 0, 0, null, results);
        return results;
    }

    public int threeSumClosest(int[] nums, int target) {
        int result = 0, distance = Integer.MAX_VALUE, tmp, n;
        // calculate all possible pair sums, store in a map
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                map.put(nums[i] + nums[j], 1);
            }
        }

        // go through each number and find closer pair sum
        for (int i = 0; i < nums.length; i++) {
            tmp = target - nums[i];
            if (map.containsKey(tmp)) return target;
            n = 1;
            while (!map.containsKey(tmp - n) || !map.containsKey(tmp + n)) {
                n += 1;
                if (n >= distance) break;
            }
            distance = n;
            if (map.containsKey(tmp + n)) result = nums[i] + map.get(tmp + n);
            else result = nums[i] + map.get(tmp - n);
        }
        return result;
    }

    public static void main(String[] args) {
        NIntegerSumSorted niss = new NIntegerSumSorted();
        System.out.println("the result is " + niss.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }
}
