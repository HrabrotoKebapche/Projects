import java.util.ArrayList;
import java.util.Scanner;

public class sha {
public static void main(String[] args) {
	

	int h0 = 0x67452301;
	int h1 = 0xEFCDAB89;
    int h2 = 0x98BADCFE;
    int h3 = 0x10325476;
    int h4 = 0xC3D2E1F0;
    
    Scanner sc = new Scanner(System.in);
    String text = sc.nextLine();
    char[] ascii = text.toCharArray();
    int ascii_length = ascii.length;
    StringBuilder stringBuilder = new StringBuilder();
    String real_ascii;
    for(int i=0;i<ascii_length;i++){
    	stringBuilder.append(0);
    	stringBuilder.append((Integer.toBinaryString((int)ascii[i])));
    	//System.out.println((int)ascii[i]);
    	//System.out.println(Integer.toBinaryString((int)ascii[i]));
    	//System.out.println(stringBuilder.toString());
    }
    int originalsize = stringBuilder.length();
    stringBuilder.append(1);
    int to448 = 448 - stringBuilder.length(); 
    for(int i =0;i<to448;i++){
    	stringBuilder.append(0);
    }

	int to512 = 64-Integer.toBinaryString(originalsize).length();
	for(int i=0;i<to512;i++){
		stringBuilder.append(0);
	}
	

	stringBuilder.append(Integer.toBinaryString(originalsize));
	real_ascii = stringBuilder.toString();
	//System.out.println(real_ascii.length());
	//System.out.println(real_ascii);
	
	
	ArrayList<String> arr = new ArrayList<String>();
	
	for(int i=0;i<512;i+=32){
		arr.add(real_ascii.substring(i,i+32));
	}
	
/*	for(String s: arr){
		System.out.println(s);
	}*/
	//System.out.println(arr);
	//System.out.println(arr.get(2));
	int i4=14, i3 = 9, i2 = 3, i1 = 1;
	for(int i = 0;i<64;i++){
//		System.out.println(arr);
	arr.add(makeWord(arr.get(i1),arr.get(i2),arr.get(i3),arr.get(i4)));
	//arr.add(makeWord2(Integer.parseInt(arr.get(i1),2),Integer.parseInt(arr.get(i2),2),Integer.parseInt(arr.get(i3),2),Integer.parseInt(arr.get(i4),2)));
	i1++;
	i2++;
	i3++;
	i4++;
	}
	
	
	System.out.println(arr.size());
	int a = h0;
	int b = h1;
	int c = h2;
	int d = h3;
	int e = h4;
	
	for(int i = 1;i<20;i++){
		int[] change =  funct1(a,b,c,d,e,(Integer.parseInt(arr.get(i))));
		a=change[0];
		b=change[1];
		c=change[2];
		d=change[3];
		e=change[4];
	}
	for(int i = 21;i<40;i++){
		int[] change =  funct1(a,b,c,d,e,(Integer.parseInt(arr.get(i))));
		a=change[0];
		b=change[1];
		c=change[2];
		d=change[3];
		e=change[4];
	}
	for(int i = 41;i<60;i++){
		int[] change =  funct3(a,b,c,d,e,(Integer.parseInt(arr.get(i))));
		a=change[0];
		b=change[1];
		c=change[2];
		d=change[3];
		e=change[4];
	}
	for(int i = 61;i<80;i++){
		int[] change =  funct4(a,b,c,d,e,(Integer.parseInt(arr.get(i))));
		a=change[0];
		b=change[1];
		c=change[2];
		d=change[3];
		e=change[4];
	}
	h0 = h0+a;
	h1 = h1+b;
	h2 = h2+c;
	h3 = h3+d;
	h4 = h4+e;
	
	h0 = h0 & 0b00011111111111111111111111111111111;
	h1 = h1 & 0b00011111111111111111111111111111111;
	h2 = h2 & 0b00011111111111111111111111111111111;
	h3 = h3 & 0b00011111111111111111111111111111111;
	h4 = h4 & 0b00011111111111111111111111111111111;

	String final_hash = new StringBuilder(Integer.toHexString(h0)).append(Integer.toHexString(h1)).append(Integer.toHexString(h2)).append(Integer.toHexString(h3)).append(Integer.toHexString(h4)).toString();
	System.out.println(final_hash);

	sc.close();
}

public static String makeWord(String w1,String w2,String w3,String w4){
	//System.out.println(w1);
	int z4 = (Integer.parseInt(w4,2));
	int z3 = (Integer.parseInt(w3,2));
	
	int z2 = (Integer.parseInt(w2,2));
	
	int z1 = (Integer.parseInt(w1,2));
	
	z3 = z4^z3;
	z2 = z2^z3;
	z1 = z1^z2;
	
	//z1 = z1<<1;
	//System.out.println(Integer.toBinaryString(z1));
	int s =32 - Integer.toBinaryString(z1).length();
	//System.out.println(s);
	if(s>0){
		//System.out.println("wwwwwwwwwwwwwwwwww");
		StringBuilder sb = new StringBuilder();
		while(s>0){
			sb.append(0);
			s--;
		}
		sb.append(Integer.toBinaryString(z1));
		char[] temp = sb.toString().toCharArray();
		char t = temp[0];
		temp[0]=temp[31];
		temp[31]=t;
		return String.valueOf(temp);
		//int sb2 = (Integer.parseInt(sb.toString(),2));
		//sb2 = Integer.rotateLeft(1, sb2);
		//System.out.println("qwceqwceqwijecoqijwecoijqowiecjoqwjeoiqjcejqoijew");
		//return ((Integer.toBinaryString(sb2)).toString());
	}
	//System.out.println("wwwwwwwwwwwwwwwwww");
	z1 = Integer.rotateLeft(1, z1);
	//System.out.println(z1);
	return (Integer.toBinaryString(z1)).toString(); 
	/*int a = (byte)(((Integer.parseInt(w4,2)) ^ (Integer.parseInt(w3,2))));
	System.out.println(a);
	int b = Integer.parseInt(w2,2) ^ a;
	//System.out.println(b);
	int c = Integer.parseInt(w1,2) ^ b;
	int d = c<<1;
	return Integer.toBinaryString(d).toString();*/
}


public static String makeWord2(int w1,int w2,int w3,int w4){
	
	
	w3 = w4^w3;
	w2 = w2^w3;
	w1 = w1^w2;
	
	int s =31 - Integer.toBinaryString(21).length();

	if(s>0){

		StringBuilder sb = new StringBuilder();
		while(s>0){
			sb.append(0);
			s--;
		}
		sb.append(Integer.toBinaryString(21));
		char[] temp = sb.toString().toCharArray();
		char t = temp[0];
		temp[0]=temp[30];
		temp[30]=t;
		return String.valueOf(temp);
	}

	w1 = Integer.rotateLeft(w1, 1);
	return (Integer.toBinaryString(w1)).toString(); 

}


public static int[] funct1(int a,int b,int c,int d,int e, int word){
	int k = 0b01011010100000100111100110011001;
	int f = ((~b)&d)|(b&c); 
	int temp = Integer.rotateLeft(a, 5)+f+e+k+word;
	temp = temp & 0b00000000000011111111111111111111111111111111;
	e = d;
	d=c;
	c = Integer.rotateLeft(b, 30);
	b = a;
	a = temp;
	int[] fin = {a,b,c,d,e};
	return fin;
}
public static int[] funct2(int a,int b,int c,int d,int e, int word){
	int k = 0b01101110110110011110101110100001;
	int f = (b^c)^d; 
	int temp = Integer.rotateLeft(a, 5)+f+e+k+word;
	temp = temp & 0b00000000000011111111111111111111111111111111;
	e = d;
	d = c;
	c = Integer.rotateLeft(b, 30);
	b = a;
	a = temp;
	int[] fin = {a,b,c,d,e};
	return fin;
}
public static int[] funct3(int a,int b,int c,int d,int e, int word){
	int k = 0b10001111000110111011110011011100;
	int f = ((b & c) | (b & d)) | (c & d); 
	int temp = Integer.rotateLeft(a, 5)+f+e+k+word;
	temp = temp & 0b00000000000011111111111111111111111111111111;
	e = d;
	d = c;
	c = Integer.rotateLeft(b, 30);
	b = a;
	a = temp;
	int[] fin = {a,b,c,d,e};
	return fin;
}
public static int[] funct4(int a,int b,int c,int d,int e, int word){
	int k = 0b11001010011000101100000111010110;
	int f = (b ^ c) ^ d; 
	int temp = Integer.rotateLeft(a, 5)+f+e+k+word;
	temp = temp & 0b00000000000011111111111111111111111111111111;
	e = d;
	d = c;
	c = Integer.rotateLeft(b, 30);
	b = a;
	a = temp;
	int[] fin = {a,b,c,d,e};
	return fin;
}
}
