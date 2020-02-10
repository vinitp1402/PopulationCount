import java.util.*;

public class populationCountProblem {



	public static void main(String agrs[]) throws Exception {

		// Read the two arrays
		int[] b = { 1902, 1941, 2004, 1957, 1989, 1909, 1918, 1913, 1979, 1961,
				1977, 1909, 1991 };
		int[] d = { 1991, 1978, 2008, 2005, 2010, 2002, 2003, 1991 };

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

		// append two Arrays
	
		int[][] combined = appendArrays(bornArray, diedArray);

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
		for (int curr = 1; curr < combined.length - 1; curr++) {

			if (combined[curr][1] - combined[curr - 1][1] < 0) {

				in.add(combined[curr][0]);

			}

		}
		// Remove duplicates from the array
		Set<Integer> set = new HashSet<>(in);
		in.clear();
		in.addAll(set);

		System.out.println("Years where population decreased: " + in);
	}

	public static int[][] appendArrays(int[][] a, int[][] b) {

		int[][] result = new int[a.length + b.length][];
		System.arraycopy(a, 0, result, 0, a.length);
		System.arraycopy(b, 0, result, a.length, b.length);

		return result;
	}

}
