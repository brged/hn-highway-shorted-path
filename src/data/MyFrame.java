package data;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener {
	MyGraph G;
	private JComboBox cb1,cb2;//下拉项
	JTextArea result;//输出结果
	MyCanvas mycanvas;//地图
	public MyFrame(){
		G=new MyGraph();
		this.setSize(500,600);
		this.setLocationRelativeTo(null);//居中
		setResizable(false);//不可调整大小
		JPanel p=new JPanel();
		p.setLayout(new BorderLayout());
		
		//北边
		JLabel lb1=new JLabel("湖南高速公路交通图");
		JPanel pp=new JPanel();
		pp.add(lb1);
		p.add(pp,BorderLayout.NORTH);
		
		//中间
		mycanvas=new MyCanvas(G);//创建地图对象
		p.setBackground(Color.white);
		p.add(mycanvas,BorderLayout.CENTER);//放到中间
		this.add(p,BorderLayout.CENTER);
		
		//南边
		JPanel p1=new JPanel();
		//JComboBox cb1,cb2;//下拉项
		cb1=new JComboBox(G.citysname);//下拉
		cb1.setMaximumRowCount(5);
		cb2=new JComboBox(G.citysname);
		cb2.setMaximumRowCount(5);
		JButton bt=new JButton("搜索");
		bt.addActionListener(this);//给按钮添加监听器
		JLabel lb2=new JLabel("请选择起始城和终点：");
		p1.add(lb2);
		p1.add(cb1);
		JLabel lb3=new JLabel(" ==>> ");
		p1.add(lb3);
		p1.add(cb2);
		p1.add(bt);
		
		//将选择框与输出文本框合并到一个面板中
		JPanel p2=new JPanel();
		result=new JTextArea(8,65);
		result.setLineWrap(true);//激活自动换行功能
		result.setWrapStyleWord(true);
		result.setBackground(Color.YELLOW);
		
		p2.setLayout(new BorderLayout());
		p2.add(p1,BorderLayout.NORTH);
		p2.add(result,BorderLayout.CENTER);
		
		//p2面板放到南边
		this.add(p2,BorderLayout.SOUTH);//放到南边
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 
	
	public static void main(String[] args) {
		MyFrame app=new MyFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//监听器
		int bgcity,endcity;
		bgcity=cb1.getSelectedIndex();
		endcity=cb2.getSelectedIndex();
		if(bgcity==endcity){
			result.setText("终点与起点相同，请重新输入！");
			return;
		}
		//计算最短路径
//		if(bgcity!=0){
			cb1.removeAllItems();
			cb2.removeAllItems();
//		}
		G.shortpath(bgcity, endcity);
		
		//String dist=G.citysname[0]+"-->>"+G.citysname[endcity]+"最短距离是"+G.D[endcity]+"km.\r\n";
		int k=0;
		while(G.paths[k]!=null) k++;
		k--;
		

		String ppath=new String();
		String pcity=new String();
		
		for(int i=k;i>0;i--)
			ppath=ppath+G.citysname[G.paths[i].e]+" -->> ";
		ppath=ppath+G.citysname [endcity];
		ppath=G.citysname[0]+" -->> "+ppath;
		
		for(int j=k;j>=0;j--){
			int bg,ed;
			bg=G.paths[j].b;
			ed=G.paths[j].e;
			pcity=pcity+G.citysname[bg]+" -->>("+G.edges[bg][ed]+" km"+")-->> "+G.citysname[ed]+"\n";
		}
		
		result.setText("   最短的路径为:\n"+ppath+"\n"+"   经过的城市及相邻城市间的距离为：\n"+pcity);
		
		/*String dist=G.citysname[0]+"-->>"+G.citysname[endcity]+"最短距离是"+G.D[endcity]+"km.\r\n";
		String pname=new String();
		int k=0;
		while(G.paths[k]!=null) k++;
		k--;
		while(k>0)
			pname=pname+G.citysname[G.paths[k--].e]+"-->";
		pname=G.citysname[0]+"-->"+pname;
		pname=pname+G.citysname[endcity]+"\r\n";
		//String pname=new String();
		int j=0;
		while(G.paths[j]!=null)
			j++;
		j--;
		while(j>=0){
			int bg,end;
			bg=G.paths[j].b;
			end=G.paths[j].e;
			pname=pname+G.citysname[bg]+"-->"+G.citysname[end]+"，距离是"+G.edges[bg][end]+";";
			j--;
		}
		result.setText(dist+"经过的城市为：\r\n"+pname);*/
		for(int i=0;i<G.citysname.length;i++){
			cb1.addItem(G.citysname[i]);
			cb2.addItem(G.citysname[i]);
		}
		
		//cb1.setSelectedIndex(bgcity);
		cb2.setSelectedIndex(endcity);
		cb1.repaint();
		cb2.repaint();
		this.repaint();//仅更新frame
		mycanvas.repaint();
	}

}
