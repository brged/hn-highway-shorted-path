package data;

import java.awt.*;

public class MyCanvas extends Canvas {
		MyEdge[] paths;//��ʼ�������ڴ�����̱�
		MyGraph G;//��ʼ��
		public MyCanvas(MyGraph G){//��ͼ�����ö���ֵ
			this.G=G;//��ͼ�����ö���ֵ
		}
		public void paint(Graphics g){//���Ƴ���
			//���Ƴ���
			for(int i=0;i<G.citysname.length;i++){
				g.setColor(Color.red);//�ı仭�ʵ���ɫ
				g.fillArc(G.location[i][0], G.location[i][1]-2, 4, 4, 0, 360);
				g.drawArc(G.location[i][0]-2, G.location[i][1]-4, 8, 8, 0, 360);
				g.setColor(Color.black);//�ı仭�ʵ���ɫ
				g.drawString(G.citysname[i], G.location[i][0]-15, G.location[i][1]-8);
			}
			//���Ʊ�
			for(int i=0;i<G.citysname.length;i++){
				for(int j=i+1;j<G.citysname.length;j++){
					if(G.edges[i][j]<G.MAXSIZE){
						g.drawLine(G.location[i][0], G.location[i][1], G.location[j][0], G.location[j][1]);
					}
				}
			}
			//�������·��
			paths=G.paths;
			int i=0;
			((Graphics2D)g).setStroke(new BasicStroke(4.0f));
			g.setColor(Color.green);
			while(paths[i]!=null){
				//������̱�
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
