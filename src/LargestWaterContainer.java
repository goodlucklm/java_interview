public class LargestWaterContainer {
    public int maxArea(int[] height) {
        int left = 0; int right = height.length-1;
        long maxArea = 0;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right-left));
            if (height[left] < height[right])
                left++;
            else
                right--;
        }
        return (int)maxArea;
    }

    public static void main(String[] args) {
        LargestWaterContainer lwc = new LargestWaterContainer();
        System.out.println(lwc.maxArea(new int[]{1,1}));
    }
}
