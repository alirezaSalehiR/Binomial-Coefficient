import java.math.BigInteger;
import java.util.Scanner;


public class Main {


    //Dynamic binomial coefficient 
    // 100 % completed
    // now program supports a larger range and it's more accurate than before thanks to  using BigInteger instead of Integer
    // created By Alireza Salehi
    //finished  2018/15/06  0:47
    // auxiliary space has been decreased (K+1) length array (unoptimized one is (((n-1)(n))/2)+k )
    // complexity is : ((K)*(K+1) /2 ) + ( (n-1) - (k) +1  )*(k)  +1
    //unoptimized binomial binomial Coefficient complexity is : (N)(N-1)+ K


    public static void main(String [] args){

        Scanner input= new Scanner(System.in);

        int n=input.nextInt();
        int k=input.nextInt();


        BigInteger answer=dyn_binomial_coefficient(n,k);

        System.out.println(answer);






    }


    public  static BigInteger dyn_binomial_coefficient(int n, int k)
    {



        if (n==k || k==0|| k==1)
            return BigInteger.ONE;





        k=kOptimize(n, k);


        BigInteger table[] = new BigInteger[k + 1];



        for (int i=1;i<table.length;i++)// BigInteger initialization
            table[i]=BigInteger.ZERO;

        table[0] = BigInteger.ONE;




        for (int i = 1; i <= n-1; i++) //it should end at one to last  
           for (int j = Math.min(i, k) ; j > 0; j--)
                table[j] = table[j].add(table[j-1]);  //  it doesn't start from first index




        return table[k].add(table[k-1]);


        // trace (unoptimized (only memory auxiliary has been reduced) )
        // auxiliary space is a (K+1) length array
        //round -1:  1  0  0  0  0  0 0         // array  is initialized
        //round  0:  1  0  0  0  0  0 0        // i=0   last nonzero index is "0   "  is "1"  and the rest are zero
        //round  1:  1  1  0  0  0  0 0        // i=1   last nonzero index is "1 st"  is "1"  and the rest are zero
        //round  2:  1  2  1  0  0  0 0        // i=2   last nonzero index is "2 nd"  is "1"  and the rest are zero
        //round  3:  1  3  3  1  0  0 0        // i=3   last nonzero index is "3 th"  is "1"  and the rest are zero
        //round  4:  1  4  6  4  1  0 0        // i=4   last nonzero index is "4 th"  is "1"  and the rest are zero
        //round  5:  1  5  10 10 5  1 0        // i=5   last nonzero index is "5 th"  is "1"  and the rest are zero
        //round  6:  1  6  15 20 15 6 1

        //==============================================================================================================

        // trace( optimized k, reduced auxiliary memory, Min(i,k) has been added for better performance  )
        // compute C(6,4);
        // auxiliary space is a (K+1) length array .
        // Min(4,6-4)=2  ==> compute C(6,2)
        //round -1:  1  0  0        // array  is initialized  // it  has his own loop.
        //round  0:  F  1           // Min(0,2)=0
        //round  1:  F  1  1        // Min(1,2)=1
        //round  2:  F  1  2  1     // Min(2,2)=2
        //round  3:  F  1  3  3     // Min(3,2)=2
        //round  4:  F  1  4  6     // Min(4,2)=2
        //round  5:  F  1  5  10    // Min(5,2)=2 // exit
        //round  00: -  -  -  F*     // F*=[K-1]+[K]

        // "*" shows the place that holds  C(n,k)
        // "F" shows places that loop ends (it finishes sooner than unoptimized one).
        // "-" shows in the last round we don't  make  change those indexes .




        // as long as "table[j] = table[j].add(table[j-1])" and we take  (k+1)th index of  table  at round n as output so,
        // wo don't need  know indexes after (K+1). that is why table has (K+1) indexes not (n) ; and the answer to this
        //  question why i have used min in  my inner second for loop;
        // as k get smaller  complexity and auxiliary space  get lower;

    }





    private static  int  kOptimize(int n,int k){

        // tip : C(n,k)=C(n,n-k)
        // lower k is better.

        return Math.min (n-k ,k);

    }












}
