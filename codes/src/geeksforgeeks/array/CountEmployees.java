package geeksforgeeks.array;

import java.util.*;

public class CountEmployees {

	public static void main(String[] args) {
		String[][] emps = new String[][] {
			{ "A", "C" },
			{ "B", "C" },
			{ "C", "F" },
			{ "D", "E" },
			{ "E", "F" },
			{ "F", "F" }, 
		};
		int[] r = countEmployees(emps);
		int[] expected = new int[] {0,0,2,0,1,5};
		System.out.println(0 == Arrays.compare(r, expected));
	}
	
	static int[] countEmployees(String[][] emps) {
		Employee[] r = countEmployees2(emps);
		Arrays.sort(r, new Comparator<Employee>() {
			public int compare(Employee a, Employee b) {
				return a.name.compareTo(b.name);
			}
		});
		int[] ret = new int[r.length];
		for (int i = 0; i < r.length; i++) ret[i] = r[i].count;
		return ret;
	}

	static class Employee {
		public String name;
		public Employee manager;
		public int count;
	}
	
	static int[] countEmployees2(String[][] emps) {
		HashMap<String, Employee> empMap = new HashMap<String, Employee>();
		for (String[] emp : emps) {
			Employee e1 = empMap.get(emp[0]);
		}
	}
}
