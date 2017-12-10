package linkedList;

import java.util.ArrayList;

public class LinkedListHarness {

	public static void main(String args[]) {
		
		LinkedList<Integer> testList = new LinkedList<Integer>();
		assert testList.isEmpty() == true;
		testList.add(3);
		assert testList.isEmpty() == false;
		assert testList.get(0) == 3;
		testList.remove(3);
		assert testList.isEmpty() == true;
		ArrayList<Integer> newInts = new ArrayList<Integer>(5);
		newInts.add(2);
		newInts.add(10);
		newInts.add(18);
		newInts.add(99);
		newInts.add(-33);
		testList.addAll(newInts);
		assert testList.contains(2);
		assert testList.containsAll(newInts);
		for(int i = 0; i < testList.size(); i++)
			assert testList.get(i).equals(newInts.get(i));
		testList.clear();
		assert testList.isEmpty();
		testList.addAll(newInts);
		ArrayList<Integer> removeInts = new ArrayList<Integer>(3);
		removeInts.add(10);
		removeInts.add(18);
		testList.removeAll(removeInts);
		assert !testList.contains(10);
		assert !testList.contains(18);
		Integer[] output = new Integer[testList.size()];
		testList.toArray(output);
		for(int i = 0; i < testList.size(); i++)
			assert output[i] == testList.get(i);
		output = new Integer[5];
		testList.toArray(output);
		for(int i = 0; i < testList.size(); i++)
			if( i < 3)
				assert output[i] == testList.get(i);
			else
				assert output[i] == null;
		testList.add(10, 0);
		testList.add(69, 2);
		assert testList.get(0) == 10;
		assert testList.get(2) == 69;
		assert testList.get(1) == 2;			
	}
	
	
	
}
