import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;

class MedianOfTwoSortedArrayMerge {
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

    public static void main(String[] argv) {
        MedianOfTwoSortedArrayMerge solution = new MedianOfTwoSortedArrayMerge();
        int[] a1 = {};
        int[] a2 = {2,3};
        double result = solution.onlySortToK(a1, a2);
        System.out.println(result);
    }
}
