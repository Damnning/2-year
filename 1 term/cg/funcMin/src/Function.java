import java.util.Vector;

public class Function {

    static double step = 0.1;
    static double stepsLimit = 1000;
    public static double getXDerivative(double x){
        return 4*x + 2;
    }
    public static double getYDerivative(double y){
        return 2*y;
    }
    public static double[] getMin(double x, double y){
        double minX = x;
        double minY = y;
        double xDerivative = getXDerivative(x);
        double yDerivative = getYDerivative(y);
        double currentXStep = step;
        double currentYStep = step;
        for(int i = 0; i < stepsLimit; i++){
            if(Math.abs(xDerivative) > Math.exp(-5)){
                currentXStep = Math.signum(xDerivative) * currentXStep;
                minX = minX - currentXStep;
                xDerivative = getXDerivative(minX);
            }
            if(Math.abs(yDerivative) > Math.exp(-5)){
                currentYStep = Math.signum(yDerivative) * currentYStep;
                minY = minY - currentYStep;
                yDerivative = getYDerivative(minY);
            }

            if(Math.abs(xDerivative) > Math.exp(-1) && Math.abs(yDerivative) < Math.exp(-1))
                break;
        }
        return new double[]{minX, minY};
    }
}
