package data;

import java.awt.*;

public class MyCanvas extends Canvas {
		MyEdge[] paths;//初始化，用于储存最短边
		MyGraph G;//初始化
		public MyCanvas(MyGraph G){//将图的引用对象赋值
			this.G=G;//将图的引用对象赋值
		}
		public void paint(Graphics g){//绘制城市
			//绘制城市
			for(int i=0;i<G.citysname.length;i++){
				g.setColor(Color.red);//改变画笔的颜色
				g.fillArc(G.location[i][0], G.location[i][1]-2, 4, 4, 0, 360);
				g.drawArc(G.location[i][0]-2, G.location[i][1]-4, 8, 8, 0, 360);
				g.setColor(Color.black);//改变画笔的颜色
				g.drawString(G.citysname[i], G.location[i][0]-15, G.location[i][1]-8);
			}
			//绘制边
			for(int i=0;i<G.citysname.length;i++){
				for(int j=i+1;j<G.citysname.length;j++){
					if(G.edges[i][j]<G.MAXSIZE){
						g.drawLine(G.location[i][0], G.location[i][1], G.location[j][0], G.location[j][1]);
					}
				}
			}
			//绘制最短路径
			paths=G.paths;
			int i=0;
			((Graphics2D)g).setStroke(new BasicStroke(4.0f));
			g.setColor(Color.green);
			while(paths[i]!=null){
				//绘制最短边
				int x1,y1,x2,y2;
				int b,e;
				b=paths[i].b;
				e=paths[i].e;
				x1=G.location[b][0];
				y1=G.location[b][1];
				x2=G.location[e][0];
				y2=G.location[e][1];
				g.drawLine(x1, y1, x2, y2);
				i++;
			}
		}

}
