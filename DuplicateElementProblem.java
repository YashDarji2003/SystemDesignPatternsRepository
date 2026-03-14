import java.util.ArrayList;
import java.util.List;

public class DuplicateElementProblem {
    public static void main(String[] args) {
        
    }
    public int dupElement(int arr[])
    {
        List<Integer> l=new ArrayList<>();
        for(int data:arr)
        {
            l.add(data);
        }
        return l.get(1);
    }
}
