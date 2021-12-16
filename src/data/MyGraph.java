package data;

public class MyGraph {
	static final int MAXSIZE=10000;//设置路径的值为无穷大
	int[] P;// P[j]:从源结点j的最短路径中，结点j的前继结点；
	int[] D;// D[j]:从源结点到目的结点j的当前最短路径
	int num;//用于储存城市的数目
	MyEdge[] paths=new MyEdge[100];//依次存储最短路径的各边
	String[] citysname=new String[]{//存放节点城市的名称
			"怀化市","吉首市","张家界市",
			"常德市","益阳市","岳阳市",
			"长沙市","株洲市","湘潭市",
			"娄底市","邵阳市","衡阳市",
			"永州市","郴州市",
	};
	int[][] edges=new int[][]{//各个城市之间的直接距离
			{0,114,10000,10000,10000,10000,10000,10000,10000,264,217,10000,10000,10000},
			{114,0,198,245,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000},
			{10000,198,0,245,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000},
			{10000,245,198,0,102,185,10000,10000,10000,10000,10000,10000,10000,10000},
			{10000,10000,10000,102,0,10000,79,10000,10000,10000,10000,10000,10000,10000},
			{10000,10000,10000,185,10000,0,155,10000,195,10000,10000,10000,10000,10000},
			{10000,10000,10000,10000,79,155,0,74,57,10000,10000,10000,10000,10000},
			{10000,10000,10000,10000,10000,10000,74,0,25,10000,10000,142,10000,10000},
			{10000,10000,10000,10000,10000,195,57,25,0,120,200,147,10000,10000},
			{264,10000,10000,10000,10000,10000,10000,10000,120,0,97,10000,10000,10000},
			{217,10000,10000,10000,10000,10000,10000,10000,200,97,0,142,124,10000},
			{10000,10000,10000,10000,10000,10000,10000,142,147,10000,142,0,137,177},
			{10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,177,137,0,10000},
			{10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,200,10000,0},
	};
	int [][] location =new int[][]{//存放城市的坐标
			{100,170},{90,120},{140,60},
			{210,70},{250,90},{300,50},
			{290,135},{300,160},{275,150},
			{218,162},{185,200},{255,215},
			{200,246},{290,280},
	};
	
	public MyGraph(){//构造方法初始化
		num=citysname.length;
		P=new int[num];
		D=new int[num];
	}
	
	public void shortpath(int bgcity,int endcity){//公共方法，调用其它方法
		for(int i=0;i<paths.length;i++)
			paths[i]=null;//将路径数组置空
		if(bgcity!=0){//将起点调整为0
			String temp=citysname[0];//交换城市名称
			citysname[0]=citysname[bgcity];
			citysname[bgcity]=temp;
			//交换城市位置
			for(int i=0;i<2;i++){
				int t=location[0][i];
				location[0][i]=location[bgcity][i];
				location[bgcity][i]=t;
			}
			for(int i=0;i<edges.length;i++){
				int t=edges[0][i];
				edges[0][i]=edges[bgcity][i];
				edges[bgcity][i]=t;
			}
			for(int i=0;i<edges.length;i++){
				int t=edges[i][0];
				edges[i][0]=edges[i][bgcity];
				edges[i][bgcity]=t;
			}
		}
		shortestPath(this);
		mypath(endcity);
			
	}
	
	public void shortestPath(MyGraph G){//最短路径计算方法
		boolean[] st=new boolean[G.num];
		//int[] distance=new int[G.num];
		for(int i=0;i<G.num;i++){
			D[i]=G.edges[0][i];//初始化源点到各结点的距离
			P[i]=0;//各节点的前继点都为源点
		}
		st[0]=true;//将源点标记
		P[0]=-1;//源点的前继点没有
		for(int i=0;i<G.num;i++){
			int min=MAXSIZE;//初始化最短路径，用于记录未标记结点中的最短距离
			int index=-1;//用于记录未标记结点中与源点距离最短的结点
			//比较从源点到其余顶点的最短路径
			for(int j=0;j<G.num;j++){
				if(st[j]==false){//从源点到j顶点的最短路径未找到
					//从源点到j顶点的最短路径未找到
					if(D[j]<min){
						index=j;
						min=D[j];
					}
				}
			}
			//找到源点到索引为index顶点的最短距离长度
			if(index!=-1)
				st[index]=true;
			//更新当前最短路径及距离
			for(int w=0;w<G.num;w++)
				if(st[w]==false){
					if(G.edges[index][w]!=MAXSIZE
							&&(min+G.edges[index][w]<D[w])){
						D[w]=min+G.edges[index][w];
						P[w]=index;//记录前继结点
					}
				}
		}
	}
	
	private void mypath(int e){//回溯，把路径记录到数组paths
		int j;
		j=e;
		int n=0;
		while(P[j]>=0){
			MyEdge line=new MyEdge(P[j],j);
			paths[n++]=line;
			j=P[j];
		}
	}
}
