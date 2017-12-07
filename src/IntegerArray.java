import java.util.*;

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

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] sorted = new int[nums1.length+nums2.length];

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j])
                sorted[k++] = nums1[i++];
            else
                sorted[k++] = nums2[j++];
        }

        while (i < nums1.length)
            sorted[k++] = nums1[i++];

        while (j < nums2.length)
            sorted[k++] = nums2[j++];

        if (sorted.length % 2 == 1)
            return (double)sorted[sorted.length/2];
        else
            return ((double)sorted[sorted.length/2]+(double)sorted[sorted.length/2-1])/2;
    }

    private int findKthElement(int[] nums1, int[] nums2, int target) {
        int i = 0, j = 0, k = 0, possible_target = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j])
                possible_target = nums1[i++];
            else
                possible_target = nums2[j++];
            if (k == target)
                return possible_target;
            k++;
        }
        while (i < nums1.length) {
            possible_target = nums1[i++];
            if (k == target)
                return possible_target;
            k++;
        }
        while (j < nums2.length) {
            possible_target = nums2[j++];
            if (k == target)
                return possible_target;
            k++;
        }
        return possible_target;
    }

    private int binarySearchKthElement(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] empty = {};

        if (len1 == 0 && k < len2)
            return nums2[k];
        if (len2 == 0 && k < len1)
            return nums1[k];
        int mid1 = len1/2;
        int mid2 = len2/2;
        if (mid1+mid2 < k) {
            // k is to the right of this point, so drop the left half of one
            if (nums1[mid1] < nums2[mid2]) { // impossible k in the left half of n1
                if (len1 == 1) {
                    return binarySearchKthElement(empty, nums2, k - 1);
                } else {
                    return binarySearchKthElement(Arrays.copyOfRange(nums1, mid1, len1), nums2, k - mid1);
                }
            } else {
                if (len2 == 1) {
                    return binarySearchKthElement(nums1, empty, k - 1);
                } else {
                    return binarySearchKthElement(nums1, Arrays.copyOfRange(nums2, mid2, len2), k - mid2);
                }
            }
        } else {
            // k is to the left of this point, drop the right half of one
            if (nums1[mid1] < nums2[mid2]) { // impossible k in the right of n2
                if (len2 == 1)
                    return binarySearchKthElement(nums1, empty, k);
                else
                    return binarySearchKthElement(nums1, Arrays.copyOfRange(nums2, 0, mid2), k);
            } else {
                if (len1 == 1)
                    return binarySearchKthElement(empty, nums2, k);
                else
                    return binarySearchKthElement(Arrays.copyOfRange(nums1, 0, mid1), nums2, k);
            }
        }
    }

    public double onlySortToK(int[] nums1, int[] nums2) {
        if ((nums1.length+nums2.length) % 2 == 1)
            return binarySearchKthElement(nums1, nums2, (nums1.length+nums2.length)/2);
        else {
            int k = nums1.length + nums2.length;
            if (k > 2)
                k = k / 2;
            else
                k = 1;
            int m = binarySearchKthElement(nums1, nums2, k-1);
            int n = binarySearchKthElement(nums1, nums2, k);
            System.out.println(m);
            System.out.println(n);
            return (double)(m+n)/2;
        }

    }

    public int searchInRotatedSortedArray(int[] nums, int target) {
        int startPosition, n = nums.length, result=-1;
        boolean rotated = false;
        if (n == 0)
            return result;
        for (startPosition = 0; startPosition < n-1; startPosition++) {
            if (nums[startPosition] > nums[startPosition+1]) {
                startPosition += 1;
                rotated = true;
                break;
            }
        }

        int left = startPosition, right = n-1+startPosition, mid;
        if (!rotated) {
            left = 0;
            right = n - 1;
        }
        while (left < right) {
            mid = (left+right)/2;
            if (nums[mid%n] < target) {
                left = mid+1;
            } else if (nums[mid%n] > target) {
                right = mid-1;
            } else {
                result = mid%n;
                break;
            }
        }
        if (left == right && target == nums[left%n])
            result = left%n;
        return result;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1,-1};
        int n = nums.length;
        if (n == 0)
            return res;
        int lo = 0, hi = n-1, mid = (lo+hi)/2;
        while (lo < hi) {
            mid = (lo+hi)/2;
            if (nums[mid] == target)
                break;
            else if (nums[mid] > target) {
                hi = mid-1;
            } else {
                lo = mid+1;
            }
        }

        int targetIndex, start, end;
        if (nums[mid] == target) {
            targetIndex = mid;
        } else if (nums[lo] == target) {
            targetIndex = lo;
        } else {
            return res;
        }
        start = targetIndex;
        while (start >= 0 && nums[start] == target) {
            res[0] = start;
            start--;
        }
        end = targetIndex;
        while (end < n && nums[end] == target) {
            res[1] = end;
            end++;
        }
        return res;
    }

    public int searchInsert(int[] nums, int target) {
        int n = nums.length, lo = 0, hi = n-1, mid = (lo+hi)/2;
        if (n == 0)
            return 0;
        while (lo <= hi) {
            mid = (lo+hi)/2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                lo = mid+1;
            else
                hi = mid-1;
        }
        if (nums[mid] > target)
            return mid;
        else
            return mid+1;
    }

    private List<Integer> getOne(Map<Integer, Integer> map, int target) {
        List<Integer> res = new ArrayList<>();
        if (map.containsKey(target))
            res.add(target);
        return res;
    }

    private void findAllCombinations(int[] candidates, int target, List<List<Integer>> res, List<Integer> currentPath, int start, int shift) {
        for (int i = start+shift; i < candidates.length && candidates[i] <= target; i++) {
            int c = candidates[i];
            if (i > start+shift && c == candidates[i-1])
                continue;
            if (c < target) {
                currentPath.add(c);
                findAllCombinations(candidates, target-c, res, currentPath, i, 1);
                currentPath.remove(new Integer(c));
            } else if (c == target) {
                currentPath.add(c);
                res.add(new ArrayList<>(currentPath));
                currentPath.remove(new Integer(c));
            }
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        findAllCombinations(candidates, target, res, path, 0, 0);
        return res;
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        findAllCombinations(candidates, target, res, path, 0, 0);
        return res;
    }

    public static void main(String[] args) {
        IntegerArray ia = new IntegerArray();
        System.out.println(ia.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }
}
