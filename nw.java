
public class nw {
private int [][]E;
private String n,m;
private char[] nalign;
private char[] malign;
	public nw(String a, String b) {
		n=a;m=b;
		E=new int[n.length()+1][m.length()+1];//comparison matrix
		initalize();
	}
	public void initalize(){
		E[0][0]=0;//sets the starting value of the matrix
		for(int i=1;i<=n.length();i++)
			E[i][0]=0;
		for(int j=1;j<m.length();j++)
			E[0][j]=0;
	}
	private int cost(char a, char b){
		if(a==b)return 1;//1 bonus point if the chars are the same
		else return 0;
	}
	public int compare(){
		for (int i=0; i<=n.length(); i++){//Needleman-Wunsch algo
			for(int j=0; j<=m.length();j++){
				if(i>0&&j>0){
					E[i][j]=Math.max(E[i-1][j-1]+cost(n.charAt(i-1),m.charAt(j-1)), Math.max(E[i-1][j], E[i][j-1]));
				}
				System.out.print(E[i][j]);//visualize the matrix
			
			}
			System.out.println("");
		}
		return E[n.length()][m.length()];
	}
	public void bestalign(){//if the matrix is created, this method seaches the best alignement
		int i=n.length();
		int j=m.length();
		nalign=new char[i+j+2];//saves the alignment
		malign=new char[i+j+2];
		int s=i+j+1;
		int t=i+j+1;
		int d, v, h;
		while(i>0||j>0){
			if(i!=0&&j!=0)
				 d=E[i-1][j-1];
			else
				 d=-1;
			if(i!=0)
				 v=E[i-1][j];
			else
				 v=-1;
			if(j!=0)
				 h=E[i][j-1];
			else
				 h=-1;
			int a=E[i][j];
			if(a>h&&a>v&&d<a){//if the comparison-value is higher, the way was diagonal (because diagonal means match means +1)
				nalign[s]=n.charAt(i-1);
				malign[t]=m.charAt(j-1);
				i=i-1;
				j=j-1;
				s--;
				t--;
			}
			else if(h==a){
				
				malign[t]=m.charAt(j-1);
				nalign[s]='_';
				if(j>0)
					j=j-1;
				s--;
				t--;
			}
			else if(v==a){
				nalign[s]=n.charAt(i-1);
				malign[t]='_';
				if(i>0)
					i=i-1;
				t--;
				s--;
			}


		}
		
	}
public static void main(String[]args){
	String b="GAATTCAGTTAGCATGCU";//example DNA-sequence
	String a="GATTACAGGATCGA";
	nw Needle =new nw(a,b);
	System.out.println("The strings match to a value of: "+Needle.compare());
	System.out.println("The best alignment of '"+a+"' and '"+b+"' is:");
	Needle.bestalign();
	for(int i=0;i<Needle.nalign.length;i++){
		if(Needle.nalign[i]!=0)
		System.out.print(Needle.nalign[i]);
	}
	System.out.println("");
	for(int i=0;i<Needle.malign.length;i++){
		if(Needle.nalign[i]!=0)
		System.out.print(Needle.malign[i]);
	}
}
}
