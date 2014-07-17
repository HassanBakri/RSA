import java.math.BigInteger;
import java.util.Random;


public class KeyPairGenrator {
BigInteger p,q,n,e,d,v;
Random rnd,rnd1;
int prefferd_block_size;
public KeyPairGenrator() {
	rnd=new Random();
	rnd1=new Random();
	p=new BigInteger(512, 1,rnd );
	q=p.nextProbablePrime();
	n=q.multiply(p);
	v=(p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
	e=getPublic(v);
	d=getPrivate(e,v);
	prefferd_block_size=(int)Math.min(Math.floor((1024-1)/8),256);
	}
public KeyPairGenrator(int keyLen) {
	rnd=new Random();
	rnd1=new Random();
	p=new BigInteger(keyLen/2, 1,rnd );
	q=new BigInteger(keyLen/2, 1,rnd1 );
	n=q.multiply(p);
	v=(p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
	e=getPublic(v);
	d=getPrivate(e,v);
	prefferd_block_size=(int)Math.min(Math.floor((keyLen-1)/8),256);
	}
private BigInteger getPrivate(BigInteger e2, BigInteger v2) {
	BigInteger i=BigInteger.valueOf(1);
	i=e2.modInverse(v2);
	System.out.println("The Private Key Is :"+i);

return i;	
}
@SuppressWarnings("unused")
private BigInteger getPrivate1(BigInteger e2, BigInteger v2) {
	BigInteger i=BigInteger.valueOf(1);
	BigInteger toB1=(i.multiply(e2).mod(v2));
	while(!(toB1.equals(BigInteger.ONE))){
		i=i.add(BigInteger.ONE);
		toB1=i.multiply(e2);
		toB1=toB1.mod(v2);
		//System.out.println(i);
	}
	System.out.println("The Private Key Is :"+i);
	return i;
}
private BigInteger getPublic(BigInteger v2) {
	BigInteger ret =BigInteger.probablePrime(v2.bitLength()-1,rnd);
	while(!(ret.gcd(v2).equals(BigInteger.ONE))){
	//System.out.println(ret);
	ret =ret.nextProbablePrime();}
	System.out.println("The Publinc Key Is :"+ret);
	return ret;
}
public BigInteger getPrivateKey(){return d;}
public BigInteger getPublicKey(){return e;}
public static void main(String[] args) {
	new KeyPairGenrator();
}
}
