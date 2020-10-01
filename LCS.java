// Java implementation of the above approach 
import java.util.*; 

class GFG 
{ 

static final int N = 100005; 

// Keeping the values array indexed by 1. 
static int arr[] = { 0, 1, 2, 2, 1, 4, 3, 6 }; 
static Vector<Integer> []tree = new Vector[N]; 

static int idx; 
static int []tin = new int[N]; 
static int []tout = new int[N]; 
static int []converted = new int[N]; 

// Function to perform DFS in the tree 
static void dfs(int node, int parent) 
{ 
	++idx; 
	converted[idx] = node; 

	// To store starting range of a node 
	tin[node] = idx; 
	for (int i : tree[node]) 
	{ 
		if (i != parent) 
			dfs(i, node); 
	} 

	// To store ending range of a node 
	tout[node] = idx; 
} 

// Segment tree 
static int []t = new int[N * 4]; 

// Build using the converted array indexes. 
// Here a simple n-ary tree is converted 
// into a segment tree. 

// Now O(NlogN) range updates and queries 
// can be performed. 
static void build(int node, int start, int end) 
{ 

	if (start == end) 
		t[node] = arr[converted[start]]; 
	else
	{ 
		int mid = (start + end) >> 1; 
		build(2 * node, start, mid); 
		build(2 * node + 1, mid + 1, end); 

		t[node] = t[2 * node] + t[2 * node + 1]; 
	} 
} 

// Function to perform update operation 
// on the tree 
static void update(int node, int start, int end, 
					int lf, int rg, int c) 
{ 
	if (start > end || start > rg || end < lf) 
		return; 

	if (start == end) 
	{ 
		t[node] = c; 
	} 
	else
	{ 

		int mid = (start + end) >> 1; 
		update(2 * node, start, mid, lf, rg, c); 
		update(2 * node + 1, mid + 1, end, lf, rg, c); 

		t[node] = t[2 * node] + t[2 * node + 1]; 
	} 
} 

// Function to find the sum at every node 
static int query(int node, int start, int end, 
				int lf, int rg) 
{ 
	if (start > rg || end < lf) 
		return 0; 

	if (lf <= start && end <= rg) 
	{ 
		return t[node]; 
	} 
	else
	{ 
		int ans = 0; 
		int mid = (start + end) >> 1; 
		ans += query(2 * node, start, mid, lf, rg); 

		ans += query(2 * node + 1, mid + 1, 
					end, lf, rg); 

		return ans; 
	} 
} 

// Function to print the tree 
static void printTree(int q, int node, int n) 
{ 
	while (q-- > 0) 
	{ 
		
		// Calculating range of node in segment tree 
		int lf = tin[node]; 
		int rg = tout[node]; 
		int res = query(1, 1, n, lf, rg); 
		System.out.print("sum at node " + node 
							+ ": " + res +"\n"); 
		node++; 
	} 
} 

// Driver code 
public static void main(String[] args) 
{ 
	int n = 7; 
	int q = 7; 
	for(int i = 0; i < N; i++) 
		tree[i] = new Vector<Integer>(); 
	
	// Creating the tree. 
	tree[1].add(2); 
	tree[1].add(3); 
	tree[1].add(4); 
	tree[3].add(5); 
	tree[3].add(6); 
	tree[3].add(7); 

	// DFS to get converted array. 
	idx = 0; 
	dfs(1, -1); 

	// Build segment tree with converted array. 
	build(1, 1, n); 

	printTree(7, 1, 7); 

	// Updating the value at node 3 
	int node = 3; 
	int lf = tin[node]; 
	int rg = tout[node]; 
	int value = 4; 

	update(1, 1, n, lf, rg, value); 

	System.out.print("After Update" + "\n"); 
	printTree(7, 1, 7); 
} 
} 

// This code is contributed by 29AjayKumar 
