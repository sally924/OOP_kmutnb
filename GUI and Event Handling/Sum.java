import java.util.Scanner;
public class Sum {
    public int totalEnergy;
    public int[] arr;

    public Sum(int[] arr){
        this.arr = arr;

    }
    public int getTotalEnergy(){
        totalEnergy = totalEnergy(arr);
        return  totalEnergy;
    }

    public static int totalEnergy(int[] myArr){
        int sum = 0; int maxI = 0;int max = 0;  int diff = 0;
        for(int i = 0;i<myArr.length;i++){
            if(i != myArr.length-1){
                diff = Math.abs(myArr[i]-myArr[i+1]);
            }
            maxI = max < diff ? i : maxI;
            max  = max < diff ? diff : max;
        }
        sum += max;
        if(myArr.length > 2){
            int[] newArr = new int[myArr.length-2];
            int newArr_Index = 0;
            for(int i = 0;i<myArr.length;i++){
                if(i != maxI && i!= maxI+1){
                    newArr[newArr_Index++] = myArr[i];
                }
            }
            return sum + totalEnergy(newArr);

        }
        return  sum;

    }

// Main
public  class Main{
    public static  void main(String[] args){
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();

        int[] myArr = new int[n];
        for(int i = 0;i<n;i++){
            myArr[i] = kb.nextInt();
        }
        Sum s1 = new Sum(myArr);
        System.out.println(s1.getTotalEnergy());

    }
}

}
