public class Sorting {

    public static int minPairSum(int[] nums) { //Counting sort
        int maxSum = Integer.MIN_VALUE;
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        int[] frequency = new int[100001];
        for (int num:nums) {
            low = Math.min(low, num);
            high = Math.max(high, num);
            frequency[num]++;
        }
        while(low <= high){
            if(frequency[low] == 0)
                low++;
            else  if(frequency[high] == 0)
                high--;
            else{
                maxSum = Math.max(maxSum, low + high);
                frequency[low]--;
                frequency[high]--;
            }
        }
        return maxSum;
    }
}
