import java.math.BigInteger;


public class BreakRsa {
	BigInteger e,n,v,d;
public BreakRsa(BigInteger e,BigInteger n) {
	this.n=n;
	this.e=e;
	}
public BigInteger Break(){
	BigInteger p=BigInteger.ONE;
	BigInteger q=p.add(BigInteger.ONE);
	if(n.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE))
	do{
	
	p=p.add(BigInteger.valueOf(2));
	}while(!(n.mod(p).equals(BigInteger.ZERO)));
	q=n.divide(p);
	v=(p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
	d=getPrivate(e,v);
	return d;
}
private BigInteger getPrivate(BigInteger e2, BigInteger v2) {
	BigInteger i=BigInteger.valueOf(1);
	i=e2.modInverse(v2);
	System.out.println("The Private Key Is :"+i);

return i;	
}
public static void main(String[] args) {
	BreakRsa b=new BreakRsa(BigInteger.valueOf(7), BigInteger.valueOf(33));
	System.out.println(b.Break().toString());
}
}
