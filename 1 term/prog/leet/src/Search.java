public class Search {
    public static long minPairSum(int[] nums, int[] cost) {
        int right = Integer.MIN_VALUE;
        int left = Integer.MAX_VALUE;
        for (int num : nums) {
            right = Math.max(right, num);
            left = Math.min(left, num);
        }
        int mid = (right + left) / 2;
        while(left < right){
            if(getCost(mid, nums, cost) < getCost(mid+1, nums, cost))
                right = mid;
            else
                left = mid+1;
            mid = (right + left) / 2;
        }

        return getCost(right, nums, cost);
    }
    private static long getCost(int num, int[] nums, int[] cost){
        long result = 0;
        for (int i = 0; i < nums.length; i++){
            result += (long) Math.abs(nums[i] - num) * cost[i];
        }
        return result;
    }
}
