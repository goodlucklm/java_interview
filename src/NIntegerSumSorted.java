import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NIntegerSumSorted {
    List<List<Integer>> findNSum(int[] inputs, int degree, int target, int curr) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();

        // input array too short
        if (inputs.length < degree) return results;
        if (inputs.length == degree) {
            int sum = 0;
            for (int n : inputs)
                sum += n;
            if (sum == target) {
                results.add(new ArrayList<Integer>());
                for (int n : inputs)
                    results.get(0).add(n);
            }
            return results;
        }

        // target not reachable
        int len = inputs.length;
        int max = inputs[len-1], min = inputs[0];
        if (max*degree < target) return results;
        if (min*degree > target) return results;

        if (degree > 2) {
            for (int i = curr; i < inputs.length - (degree-1); i++) {
                int current = inputs[i];
                // if (i > 0 && current == inputs[i - 1]) continue; // duplicated item as the one before
                if ((current + (degree - 1) * max) < target) continue; // current too small
                if ((current + (degree - 1) * min) > target) continue; // current too big

                List<List<Integer>> temp;
                temp = findNSum(inputs, degree - 1, target - current, i+1);
                for (int j = 0; j < temp.size(); j++) {
                    temp.get(j).add(current);
                    Collections.sort(temp.get(j));
                    if (!results.contains(temp.get(j)))
                        results.add(temp.get(j));
                }
            }
        } else { // degree == 2, twoSum
            int left = curr, right = len-1;
            while (left < right) { // this condition covers right == curr
                int sum = inputs[left] + inputs[right];
                if (sum == target) {
                    List<Integer> L = new ArrayList<>();
                    L.add(inputs[left]);
                    L.add(inputs[right]);
                    results.add(L);
                    left++;
                    right--;
                } else {
                    if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return results;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return findNSum(nums, 4, target, 0);
    }

    public static void main(String[] args) {
        NIntegerSumSorted niss = new NIntegerSumSorted();
        niss.fourSum(new int[]{-1,0,1,2,-1,-4}, -1);
    }
}
