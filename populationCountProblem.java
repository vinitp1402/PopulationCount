import java.util.*;

public class populationCountProblem {



	public static void main(String agrs[]) throws Exception {

		// Read the two arrays
		int[] b = {1902, 1941, 2004, 1957, 1989, 1909,
				1918, 1913, 1979, 1961, 1977, 1909};
		int[] d = { 1991, 1978, 2008, 2005, 2010, 2002, 2003, 1991 };
		
		//call processData method to work out on the problem
		countPopulation ct = new countPopulation();
		ct.processData(b, d);


	}

}

class countPopulation {
	
	void processData(int[] b, int []d) {
		
		int[][] bornArray = new int[b.length][2];
		int[][] diedArray = new int[d.length][2];

		// Birth increases the population count by 1. So assign 1
		for (int k = 0; k < b.length; k++) {
			bornArray[k][0] = b[k];
			bornArray[k][1] = 1;
		}

		// Death decreases the population count by 1. So assign -1
		for (int k = 0; k < d.length; k++) {
			diedArray[k][0] = d[k];
			diedArray[k][1] = -1;
		}

		System.out.println("Born: " + Arrays.deepToString(bornArray));
		System.out.println("Died: " + Arrays.deepToString(diedArray));
		
		// append two Arrays
		int[][] combined = new int[bornArray.length + diedArray.length][];
		System.arraycopy(bornArray, 0, combined, 0, bornArray.length);
		System.arraycopy(diedArray, 0, combined, bornArray.length, diedArray.length);
		
		
		
		// Sort the two Arrays on the basis of year
		Arrays.sort(combined, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});

		// Count population now
		for (int i = 1; i < combined.length; i++) {

			combined[i][1] = combined[i][1] + combined[i - 1][1];

		}

		System.out.println("Counted & Sorted Array:");
		System.out.println(Arrays.deepToString(combined));

		// Now report the years where population has decreased
		List<Integer> in = new ArrayList<Integer>();
		for (int curr = 1; curr < combined.length; curr++) {

			if (combined[curr][1] - combined[curr - 1][1] < 0) {
				 
				//death this year is counted next year
				in.add(combined[curr][0]+1);

			}

		}
	
		// Remove duplicates from the array
		Set<Integer> set = new HashSet<>(in);
		in.clear();
		in.addAll(set);

		System.out.println("Years where population decreased(w/o dups): " + in);
	}
		
	}
	

