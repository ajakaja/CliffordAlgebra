package util;
import java.util.ArrayList;
import java.util.HashMap;


public class Combinatorics {


	/**
	 * better be small numbers or this will not work.
	 * @param n
	 * @param k
	 * @return
	 */
	public static long multinomial(int n, int[] k){
		long f = factorial(n);
		for(int i : k){
			f /= factorial(i);
		}

		return f;
	}



	static HashMap<Integer, Long> facs = new HashMap<Integer, Long>();
	public static long factorial(int n){

		if(facs.containsKey(n)){
			return facs.get(n);
		}else{
			long res = n * factorial(n-1);
			facs.put(n, res);
			return res;
		}

	}

	public static long choose(int n, int k){
		long ret = 1; 
		for(int i = n-k + 1; i <= n; i++){
			ret *= i;
		}
		for(int i = 1; i <= k; i++){
			ret /= i;
		}
		return ret;
	}

	//uses Gosper's hack to calculate next combination. I don't understand it. As seen on StackOver
	//Feed it a null matrix to start; when it returns a null you've seen them all.
	//There is no error checking.
	private static int[] nextCombination(int n, int k, int[] comb){
		int limit = 1 << n;
		long set;

		if(comb == null){
			set = (1 << k) -1;
		}
		else{
			set = 0;
			for(int i : comb){
				set = (set | (1 << i));
			}
			if(set >= limit){
				return null;
			}else{
				long c = set & -set;
				long r = set + c;
				set = (((r^set) >>> 2) / c) | r;
			}
		}
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i = 0; i < n; i++){
			if( (set & (1 << i)) != 0){
				temp.add(i);
			}
		}

		if(temp.size() != k) return null;
		int[] ret = new int[temp.size()];
		for(int i = 0; i < ret.length; i++){
			ret[i] = temp.get(i);
		}
		return ret;

	}
	
	
	/**
	 * returns an array containing (n choose k) elements, each of which is a unique combination of k elements out of n, labeled by index (starting at 0)
	 * @param n
	 * @param k
	 * @return
	 */
	public static int[][] getSubsets(int n, int k){
		if(k < 0 || k > n) return null;
		int num = (int) Combinatorics.choose(n, k);
		
		int[][] ret = new int[num][k]; //becomes an array where each entry is an array of k elements out of n; each is unique.
		int[] comb = null;
		int iter = 0;
		do{
			
			comb = Combinatorics.nextCombination(n, k, comb);
			
			if(comb != null){
				for(int i = 0; i < k; i++){
					ret[iter][i]=comb[i];
				}
			}
			iter ++;
		}while(comb != null);
		
		for(int i = 0; i < ret.length; i++){
			ret[i] = getPositive(ret[i], n);
		}
		
		return ret;

	}
	
	/**
	 * if this ordering of elements out of n is of negative parity, swaps the first two elements.
	 * Determined by: the (each element minus the previous mod 4) - if odd, gives +; if even gives -. 
	 * The parity is the multiplication of this for every adjacent pair in the array.
	 * @param arg
	 * @param n
	 * @return
	 */
	private static int[] getPositive(int[] arg, int n){
		if(arg.length < 2) return arg;
		int parity = 1;
		for(int i = 0; i < arg.length - 1; i++){
			int diff;
			if(arg[i+1] > arg[i]) diff = arg[i+1]-arg[i];
			else diff = arg[i+1] + n - arg[i];
			if(diff % 2 == 0) parity *= -1;
		}
		
		if(parity == -1){
			int temp = arg[0];
			arg[0] = arg[1];
			arg[1] = temp;
		}
		return arg;
		
		
	}

	
	// TODO: generate combinations in cyclic order instead.
}
