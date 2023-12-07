import java.util.PriorityQueue;
import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.lang.Math;
public class PriorityHeap 
{
	ArrayList heap;
	int length;
	public PriorityHeap()
	{
		length = 0;
		heap = new ArrayList<Tuple>();
	}
	public int size()
	{
		return(length);
	}
	public boolean compare(Tuple a, Tuple b) 
	{
		return(a.getLength() > b.getLength());
	}
	public Tuple grab(int index)
	{
		return ((Tuple) heap.get(index));
	}
	public Tuple first()
	{
		if (length == 0)
		{
			return(new Tuple(100000,100000));
		}
		else
		{
			return ((Tuple) heap.get(0));
		}
	}
	public Tuple pop()
	{
		length -= 1;
		return((Tuple)heap.remove(0));
	}
	public Tuple take(int index)
	{
		length -= 1;
		return((Tuple)heap.remove(index));
	}
	public void insert(Tuple a) 
	{
		//System.out.println("Insert called");
		if (length == 0)
		{
			//System.out.println("0");
			heap.add(0, a);
			length+=1;
		}
		else
		{
			int low = 0;
			int high = length-1;
			int mid = 0;
			while (low <= high) 
			{
				mid = low + (high - low)/2;
				if(low == high && low == 0)
				{
					//System.out.println("Low = High, Low = 0");
					if (compare(a,(Tuple)heap.get(mid)))
					{
						heap.add(low+1, a);
						length+=1;
						break;
					}
					else
					{
						heap.add(low, a);
						length+=1;
						break;
					}
				}
				else if(low == high && low != 0)
				{
					//System.out.println("Low = High, Low != 0");
					heap.add(high+1, a);
					length+=1;
					break;
				}
				else if (compare(a,(Tuple)heap.get(mid)) && !(compare(a,(Tuple)heap.get(mid+1))))
				{
					//System.out.println("Greater than mid, less than mid+1");
					heap.add(mid+1,a);
					length+=1;
					break;
				}
			
				else if (compare(a,(Tuple)heap.get(mid)) && (compare(a,(Tuple)heap.get(mid+1))))
				{
					//System.out.println("Greater than mid, greater than mid+1");
					low = mid+1;
				}
				else 
				{
					//System.out.println("Less than mid");
					high = mid;
				}
			}
		}
	}
}