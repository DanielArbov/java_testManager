package id209475862_id207232760;

import java.io.Serializable;

public class Set<T> implements Serializable {
	
	private T[] arr;
	private final int NUM = 2;
	private int size;
	
	public Set() {
		arr = (T[])new Object[NUM];
		size = 0;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getCapacity() {
		return arr.length;
	}
	
	public T getContent(int index) {
		return arr[index];
	}
	
	public void setContent(T content, int index)
	{
		if(!checkIfExist(content))
		arr[index] = content; 
	}
	
	private boolean checkIfExist(T item)
	{
		for(int i = 0 ; i<size; i++)
		{
			if(arr[i] == item)
				return true;
		}
		
		return false;
	}
	
	private void doubleTheArr()
	{
		T[] arr2 = (T[])new Object[arr.length*NUM];
		for(int i = 0; i<size; i++)
		{
			arr2[i] = arr[i];
			
		}
		
		this.arr = arr2;
		
		
	}
	
	public void add(T item)
	{
		if(!checkIfExist(item))
		{
			if(this.size == arr.length)
				doubleTheArr();
			
			arr[size] = item;
			size++;
				
		}
	}
	
	public void delete(int index)
	{
		for(int i = index; i<size-1; i++)
		{
			arr[i] = arr[i+1];
		}
		arr[size -1] = null;
		
		size--;
	}
	
	public String toString()
	{
		String str = "[ ";
		for(int i = 0; i<size; i++)
		{
			str+= arr[i] + " ";
		}
		
		str+= " ]";
		
		return str;
	}

}