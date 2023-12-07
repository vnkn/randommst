
import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.lang.Math;

public class randmst 
{
	private static int graph_size;

	/**
	 *	Creates an checkerboard with all the pieces in their starting places.
	 */
	public static void main(String[] args)
	{
		int trials = Integer.parseInt(args[2]);
		graph_size = Integer.parseInt(args[1]);
		int dim = Integer.parseInt(args[3]);
		double total_sum = 0;
		//int trials = 3;
		//int dim = 4;
		//int graph_size = 2048;
		double sum = 0;
		double max_edge = 0;
		int variance = 1;
		for (int t = 0; t < trials; t++)
		{
			PriorityHeap[] rows = new PriorityHeap[graph_size];
			for (int i = 0; i < graph_size; i++) 
			{
				rows[i] = new PriorityHeap();
			}
	        if (dim == 0)
	        {
	        	create_graph(graph_size, rows, max_edge);
	        }
	        if (dim == 1)
	        {
	        	create_test(graph_size, rows,max_edge);
	        }
	        if (dim == 2)
	        {
	        	create2d(graph_size, rows, max_edge);
	        }
	        if (dim == 3)
	        {
	        	create3d(graph_size, rows, max_edge);
	        }
	        if (dim == 4)
	        {
	        	create4d(graph_size, rows, max_edge);
	        }
	        Timer timer = new Timer();
	        long startTime = System.currentTimeMillis();
			//createString(graph_size, rows);
			sum = createMST(graph_size, rows);
			total_sum += sum;
	        long endTime = System.currentTimeMillis();
	        System.out.println("Time = " + (endTime-startTime)/1000.);
		}
		double average = total_sum/trials;
		System.out.println(average + " " + graph_size + " " + trials + " " + dim);
		//double random = 0;
		//double random_sum = 0;
		//for (int s = 0; s < 500; s++)
		//{
			//random_sum += Math.random();
		//}
		//System.out.println(random_sum/500);
                System.exit(0);

	}
	 public static void  create2d(int graph_size, PriorityHeap rows[], double max_edge)
	 {
	        double[] x = new double[graph_size];
	        double[] y = new double[graph_size];
	        //Create a cutoff for edges over a certain length not to be added to the graph
	        double cutoff = (7.8*Math.pow(Math.pow(2, .5), Math.log(graph_size)/Math.log(2))-7)/graph_size;
	        //Create two arrays of random values for vertex x and y values
	        for(int i = 0; i < graph_size; i++)
	        {
	            x[i] = Math.random();
	            //System.out.println(x[i] + "Generated");
	            y[i] = Math.random();
	            //System.out.println(y[i] + "Generated");
	        }
	        double toadd = 0;
	        //Add the edges to the graph if they are above a certain size
			for(int k = 0; k < graph_size; k++)
			{
				for(int l = k; l < graph_size; l++)
				{
					if (k != l)
					{
		                toadd = (Math.sqrt(Math.pow(x[l] - x[k],2) + (Math.pow(y[l] - y[k],2))));
		                //Add it to the heap of both vertices
						Tuple tup1 = new Tuple(l, toadd);
						Tuple tup2 = new Tuple(k, toadd);
						if (graph_size > 4095)
						{
							if(toadd < cutoff*2)
							{
								rows[k].insert(tup1);
								rows[l].insert(tup2);
							}
						}
						else if (graph_size <= 4095)
						{
							rows[k].insert(tup1);
							rows[l].insert(tup2);
						}
					}
					//System.out.println("Case 2: Num 1: " + k + " Num2: " + l + "Sum: " + (k+l));
				}
			}
	    }
		
		public static void create_graph(int graph_size, PriorityHeap rows[], double max_edge) 
		{
			//Create a cutoff for edges over a certain length not to be added to the graph
			double cutoff = 1.2/graph_size;
			//Add the edges to the graph if they are above a certain size
			for(int k = 0; k < graph_size; k++)
			{
				for(int l = k; l < graph_size; l++)
				{
					if (k != l)
					{
						double rand = Math.random();
						 //Add it to the heap of both vertices
						Tuple tup1 = new Tuple(l, rand);
						Tuple tup2 = new Tuple(k, rand);
						if (graph_size > 4095)
						{
							if(rand < cutoff*20)
							{
								rows[k].insert(tup1);
								rows[l].insert(tup2);
							}
						}
						else if (graph_size <= 4095)
						{
							rows[k].insert(tup1);
							rows[l].insert(tup2);
						}
					}
					//System.out.println("Case 2: Num 1: " + k + " Num2: " + l + "Sum: " + (k+l));
				}
			}
		}
	    public static void create3d(int graph_size, PriorityHeap rows[], double max_edge)
	    {
	    	//Create a cutoff for edges over a certain length not to be added to the graph
	    	double cutoff = (17.8*Math.pow(Math.pow(2, .5), Math.log(graph_size)/Math.log(2))-7)/graph_size;
	 	        double[] x = new double[graph_size];
	 	        double[] y = new double[graph_size];
	 	        double[] z = new double[graph_size];
	 	        //Create three arrays of random values for vertex x,y and z values
	 	        for(int i = 0; i < graph_size; i++)
	 	        {
	 	            x[i] = Math.random();
	 	            //System.out.println(x[i] + "Generated");
	 	            y[i] = Math.random();
	 	            //System.out.println(y[i] + "Generated");
	 	            z[i] = Math.random();
	 	        }
	 	        double toadd = 0;
	 	     //Add the edges to the graph if they are above a certain size
	 			for(int k = 0; k < graph_size; k++)
	 			{
	 				for(int l = k; l < graph_size; l++)
	 				{
	 					if (k != l)
	 					{
	 		                toadd = (Math.sqrt(Math.pow(x[l] - x[k],2) + (Math.pow(y[l] - y[k],2)) +  (Math.pow(z[l] - z[k],2))));
	 		               //Add it to the heap of both vertices
	 						Tuple tup1 = new Tuple(l, toadd);
	 						Tuple tup2 = new Tuple(k, toadd);
	 						if (graph_size > 4095)
							{
								if(toadd < cutoff*2)
								{
									rows[k].insert(tup1);
									rows[l].insert(tup2);
								}
							}
							else if (graph_size <= 4095)
							{
								rows[k].insert(tup1);
								rows[l].insert(tup2);
							}
	 					}
	 					//System.out.println("Case 2: Num 1: " + k + " Num2: " + l + "Sum: " + (k+l));
	 				}
	 			}
	 	    
	    }
	    public static void create4d(int graph_size, PriorityHeap rows[], double max_edge)
	    {
	    	//Create a cutoff for edges over a certain length not to be added to the graph
	    		double cutoff = (28.59*Math.pow(Math.pow(2, .5), Math.log(graph_size)/Math.log(2))-7)/graph_size;
	 	        double[] x = new double[graph_size];
	 	        double[] y = new double[graph_size];
	 	        double[] z = new double[graph_size];
	 	        double[] w = new double[graph_size];
	 	     //Create two arrays of random values for vertex w,x,y and z values
	 	        for(int i = 0; i < graph_size; i++)
	 	        {
	 	            x[i] = Math.random();
	 	            //System.out.println(x[i] + "Generated");
	 	            y[i] = Math.random();
	 	            //System.out.println(y[i] + "Generated");
	 	            z[i] = Math.random();

	 	            w[i] = Math.random();
	 	        }
	 	        double toadd = 0;
	 	     //Add the edges to the graph if they are above a certain size
	 			for(int k = 0; k < graph_size; k++)
	 			{
	 				for(int l = k; l < graph_size; l++)
	 				{
	 					if (k != l)
	 					{
	 		                toadd = (Math.sqrt(Math.pow(x[l] - x[k],2) + (Math.pow(y[l] - y[k],2)) +  (Math.pow(z[l] - z[k],2))  + (Math.pow(w[l] - w[k],2)) ));
	 		               //Add it to the heap of both vertices
	 						Tuple tup1 = new Tuple(l, toadd);
	 						Tuple tup2 = new Tuple(k, toadd);
	 						if (graph_size > 4095)
							{
								if(toadd < cutoff*1.5)
								{
									rows[k].insert(tup1);
									rows[l].insert(tup2);
								}
							}
							else if (graph_size <= 4095)
							{
								rows[k].insert(tup1);
								rows[l].insert(tup2);
							}
	 					}
	 					//System.out.println("Case 2: Num 1: " + k + " Num2: " + l + "Sum: " + (k+l));
	 				}
	 			}
	 	    
	    }
	
	public static void create_test(int graph_size, PriorityHeap rows[],  double max_edge)
	{
		for(int k = 0; k < graph_size; k++)
		{
			for(int l = 0; l < graph_size - k-1; l++)
			{
				Tuple tup = new Tuple(l+k+1, (int)(Math.random() * 100 + 1));
				 if (tup.getLength() > max_edge)
	                {
	                	max_edge = tup.getLength();
	                }
				if (tup.getLength() < 500)
				{
					rows[k].insert(tup);
				}
				//System.out.println("Case 2: Num 1: " + k + " Num2: " + l + "Sum: " + (k+l));
			}
		}
	}


	public static void createString(int graph_size, PriorityHeap rows[])
	{
		for(int k = 0; k < graph_size; k++)
		{
			for(int l = 0; l < graph_size-1; l++)
			{
				System.out.print((((Tuple)rows[k].grab(l))).getLength() + ",");
				System.out.print((((Tuple)rows[k].grab(l))).getVertex() + " ");
			}
			System.out.println("\n");
		}
	}
        

	public static double createMST(int graph_size, PriorityHeap graph[])
	{
		double max_edge = 0;
		//Create two sets, one for the elements of the MST and one for those left in the graph
		int[] cut2 = new int[graph_size];
		int cut2_size = graph_size-1; 
		int[] cut = new int[graph_size];
		int cut_size = 1;
		//Add the first node to the set of elements in the MST and set the first node to not be in the MST
		cut[0] = 0;
		cut2[0] = -1;
        int current_check = 0;
        //Initialize the MST set to have all values of -1, so that they are not mistaken to be needed to be used and initialize the graph left set to be the same as their index
		for (int n = 1; n < graph_size; n ++)
		{
			cut[n] = -1;
		}
		for (int n = 1; n < graph_size; n ++)
		{
			cut2[n] = n;
		}
		//System.out.print("Initial Cut 1 = " + cut[1]);
		//System.out.print("Initial Cut = ");
		for (int n = 0; n < graph_size; n ++)
		{
			//System.out.print(cut[n] + " ");
		}
		//System.out.println();
		//System.out.print("Initial Cut2 = ");
		for (int n = 0; n < graph_size; n ++)
		{
			//System.out.print(cut2[n] + " ");
		}
		//System.out.println();
		//initialize needed variables, including tuples to store the used edges in
		int allowed_added = 0;
		double sum = 0;
		double shortest_edge = 750;
		int shortest_edge_row = -2;
		boolean should_allow = true;
		boolean should_allow2 = true;
		Tuple used = new Tuple(1,1);
		Tuple dummy = new Tuple(1,1);
		int vertex_used = 0;
		int row_used = 0;
		int current_checked_index = 0;
		int location = -2;
		//loop through shortest node searching n-1 times so that we add n-1 edges to the MST
		for (int i = 0; i < graph_size-1; i++)
		{
			//initialize the shortest edge to be an unreachable value
			shortest_edge = 750.0;
			//go through and find whether the edge is in the cut or cut2
			for (int j = 0; j < graph_size; j++)
			{
				//if it is in the cut then check if the vertex it connects to is in cut2
				if (cut[j] == j)
				{
                    current_check = 0;
                    //System.out.println("Tried is " + ((Tuple)graph[j].first()).getLength());
					//System.out.println("Row in cut = " + j);
					//System.out.println("This edge = " + ((Tuple)graph[j].first()).getLength());
                    //check if it is the shortest edge in the loop so far
					if ((((Tuple)graph[j].first()).getLength()) < shortest_edge)
					{
						//System.out.println("Is Shortest Edge");
						//check if the vertex is in cut2 and if it is make it the current smallest edge
						if (cut2[((Tuple)graph[j].first()).getVertex()] == ((Tuple)graph[j].first()).getVertex())
						{
							location = 0;
							vertex_used = cut2[((Tuple)graph[j].first()).getVertex()];
							shortest_edge = ((Tuple)graph[j].first()).getLength();
							row_used = j;
                            //System.out.println("Length is " + ((Tuple)graph[j].first()).getLength());
							//System.out.println("Vertex in cut2 = " + vertex_used);
						}
						else if (cut[((Tuple)graph[j].first()).getVertex()] == ((Tuple)graph[j].first()).getVertex())
						{
							//System.out.println("Edge doesn't cross");
							dummy = (Tuple)(graph[j].pop());
							j--;                                 
						}
						else
						{
							continue;
						}
					}
				}
				//check if the current row is in cut2
				
				else
				{
					continue;
				}
				//System.out.println("Run " + j + ": Shortest Edge = " + shortest_edge);
			}
		
            //System.out.println("Vertex = " + vertex_used + "Row Used =  " + row_used +  ", Length = " + shortest_edge);
			used = (Tuple)graph[row_used].take(location);
			sum += shortest_edge;
			cut2[vertex_used] = -1;
			cut[vertex_used] = vertex_used;
			
			cut_size += 1;
			cut2_size -= 1;
			//System.out.print("Cut = " );
			for (int n = 0; n < graph_size; n ++)
			{
				//System.out.print(cut[n]);
			}
			//System.out.println();
			//System.out.print("Cut2 = ");
			for (int n = 0; n < graph_size; n ++)
			{
				//System.out.print(cut2[n]);
			}
			//System.out.println();
			//System.out.println();
		}
		return(sum);
	}

}
