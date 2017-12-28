package geeksforgeeks.array;

import java.util.*;

public class CountEmployees {
	
	static int[] countEmployees(String[][] emps) {
		Collection<Employee> cr = countEmployees2(emps);
		Employee[] r = cr.toArray(new Employee[cr.size()]);
		Arrays.sort(r, (a, b) -> a.name.compareTo(b.name));
		int[] ret = new int[r.length];
		for (int i = 0; i < r.length; i++) ret[i] = r[i].count;
		return ret;
	}

	static class Employee {
		public String name;
		public Employee manager;
		public int count;
	}
	
	static Collection<Employee> countEmployees2(String[][] emps) {
		HashMap<String, Employee> empMap = new HashMap<String, Employee>();
		for (String[] emp : emps) {
			Employee e1 = empMap.get(emp[0]);
			if (e1 == null) {
				e1 = new Employee();
				e1.name = emp[0];
				empMap.put(e1.name, e1);
			}
			Employee e2 = empMap.get(emp[1]);
			if (e2 == null) {
				e2 = new Employee();
				e2.name = emp[1];
				empMap.put(e2.name, e2);
			}
			e1.manager = e2;
			for (Employee e = e1; e.manager != null && e.manager != e; e = e.manager) {
				e.manager.count += e1.count + 1;
			}
		}
		
		return empMap.values();
	}

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
}
