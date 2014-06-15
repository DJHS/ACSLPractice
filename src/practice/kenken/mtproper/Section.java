package practice.kenken.mtproper;

import java.util.ArrayList;
import java.util.List;

/**
 * KenKen implementation, properly multi-threaded with Concurrency and Executor
 * @author derek
 */
public class Section implements Cloneable{
    private List<Integer> targets;
    private int sum;
    public Section(){
        targets = new ArrayList<>();
    }
    public void addTarget(int target){
        this.targets.add(target);
    }
    public void setSum(int sum){
        this.sum = sum;
    }
    public boolean checkAgainst(int[] arr){
        int tempSum = 0;
        for(int target : targets){
            tempSum += (arr[target - 1] + 1);
        }
        return tempSum == sum;
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        for(int t : targets){
            builder.append(t);
            builder.append(" ");
        }
        builder.append("sum=");
        builder.append(sum);
        builder.append(")");
        return builder.toString();
    }
    
    @Override
    public Section clone() {
        Section result = new Section();
        result.setSum(this.sum);
        for (Integer target : this.targets) {
            result.addTarget(target);
        }
        return result;
    }
}
