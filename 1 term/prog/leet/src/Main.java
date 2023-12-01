import java.sql.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Search.minPairSum(new int[]{735103,366367,132236,133334,808160,113001,49051,735598,686615,665317,999793,426087,587000,649989,509946,743518}, new int[]{724182,447415,723725,902336,600863,287644,13836,665183,448859,917248,397790,898215,790754,320604,468575,825614}));
    }
    public static int garbageCollection(String[] garbage, int[] travel) {
        int m = 0;

        int p = 0;
        int g = 0;
        int[] aux;
        for (String item:garbage) {
            aux = countGarbage(item);
            m += aux[0];
            p += aux[1];
            g += aux[2];
        }


    }
    private static int[] countGarbage(String garbage) {
        int m = 0;
        int p = 0;
        int g = 0;
        for (char item:garbage.toCharArray()) {
            if(item == 'M')
                m++;
            else if(item == 'P')
                p++;
            else if(item == 'G')
                g++;
        }
        return new int[]{m,p,g};
    }
}
