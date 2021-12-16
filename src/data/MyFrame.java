package data;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener {
	MyGraph G;
	private JComboBox cb1,cb2;//������
	JTextArea result;//������
	MyCanvas mycanvas;//��ͼ
	public MyFrame(){
		G=new MyGraph();
		this.setSize(500,600);
		this.setLocationRelativeTo(null);//����
		setResizable(false);//���ɵ�����С
		JPanel p=new JPanel();
		p.setLayout(new BorderLayout());
		
		//����
		JLabel lb1=new JLabel("���ϸ��ٹ�·��ͨͼ");
		JPanel pp=new JPanel();
		pp.add(lb1);
		p.add(pp,BorderLayout.NORTH);
		
		//�м�
		mycanvas=new MyCanvas(G);//������ͼ����
		p.setBackground(Color.white);
		p.add(mycanvas,BorderLayout.CENTER);//�ŵ��м�
		this.add(p,BorderLayout.CENTER);
		
		//�ϱ�
		JPanel p1=new JPanel();
		//JComboBox cb1,cb2;//������
		cb1=new JComboBox(G.citysname);//����
		cb1.setMaximumRowCount(5);
		cb2=new JComboBox(G.citysname);
		cb2.setMaximumRowCount(5);
		JButton bt=new JButton("����");
		bt.addActionListener(this);//����ť��Ӽ�����
		JLabel lb2=new JLabel("��ѡ����ʼ�Ǻ��յ㣺");
		p1.add(lb2);
		p1.add(cb1);
		JLabel lb3=new JLabel(" ==>> ");
		p1.add(lb3);
		p1.add(cb2);
		p1.add(bt);
		
		//��ѡ���������ı���ϲ���һ�������
		JPanel p2=new JPanel();
		result=new JTextArea(8,65);
		result.setLineWrap(true);//�����Զ����й���
		result.setWrapStyleWord(true);
		result.setBackground(Color.YELLOW);
		
		p2.setLayout(new BorderLayout());
		p2.add(p1,BorderLayout.NORTH);
		p2.add(result,BorderLayout.CENTER);
		
		//p2���ŵ��ϱ�
		this.add(p2,BorderLayout.SOUTH);//�ŵ��ϱ�
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 
	
	public static void main(String[] args) {
		MyFrame app=new MyFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//������
		int bgcity,endcity;
		bgcity=cb1.getSelectedIndex();
		endcity=cb2.getSelectedIndex();
		if(bgcity==endcity){
			result.setText("�յ��������ͬ�����������룡");
			return;
		}
		//�������·��
//		if(bgcity!=0){
			cb1.removeAllItems();
			cb2.removeAllItems();
//		}
		G.shortpath(bgcity, endcity);
		
		//String dist=G.citysname[0]+"-->>"+G.citysname[endcity]+"��̾�����"+G.D[endcity]+"km.\r\n";
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
		
		result.setText("   ��̵�·��Ϊ:\n"+ppath+"\n"+"   �����ĳ��м����ڳ��м�ľ���Ϊ��\n"+pcity);
		
		/*String dist=G.citysname[0]+"-->>"+G.citysname[endcity]+"��̾�����"+G.D[endcity]+"km.\r\n";
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
			pname=pname+G.citysname[bg]+"-->"+G.citysname[end]+"��������"+G.edges[bg][end]+";";
			j--;
		}
		result.setText(dist+"�����ĳ���Ϊ��\r\n"+pname);*/
		for(int i=0;i<G.citysname.length;i++){
			cb1.addItem(G.citysname[i]);
			cb2.addItem(G.citysname[i]);
		}
		
		//cb1.setSelectedIndex(bgcity);
		cb2.setSelectedIndex(endcity);
		cb1.repaint();
		cb2.repaint();
		this.repaint();//������frame
		mycanvas.repaint();
	}

}
