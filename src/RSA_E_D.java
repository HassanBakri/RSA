import java.math.BigInteger;

public class RSA_E_D {
	int keySize;
	int clearTextSize ;    
	int   cipherTextSize ;    
	byte[] clearTextBlock ;
    byte[] cipherTextBlock ;
	static String Plain ;
	public RSA_E_D(int i) {
		keySize=i;
		cipherTextSize = 1 + (keySize-1)/8;            // In bytes
		clearTextSize = Math.min((keySize-1)/8,256);   // In bytes
		cipherTextBlock= new byte[cipherTextSize];
		clearTextBlock = new byte[clearTextSize];
	}
byte[] encript(byte[] ciper,BigInteger e,BigInteger n){
		//addPadding(ciper,clearTextSize);
		BigInteger cipher=new BigInteger(1,ciper);
		System.out.println("Vilid Encription  :"+((cipher.compareTo(n)==1)?"false":"true"));
		BigInteger exp;
		exp=cipher.modPow(e, n);
		System.out.println("BitLen is"+exp.bitLength());
		byte[] cipherTextData=exp.toByteArray();
		putBytesIntoBlock(cipherTextBlock, cipherTextData);
		return cipherTextBlock;
	}
public static void putBytesIntoBlock(byte[] block, byte[] data) {
    int bSize = block.length;
    int dSize = data.length;
    int i = 0;
    while (i<dSize && i<bSize) {
        block[bSize-i-1] = data[dSize-i-1];
        i++;
    }
    while (i<bSize) {
        block[bSize-i-1] = (byte)0x00;
        i++;
    }
 }
byte[] decript(byte[] ciper,BigInteger d,BigInteger n){
	//System.out.println("The Block Size :"+Math.floor((n.bitLength()-1)/8));
	BigInteger cipher=new BigInteger(1,ciper);
	System.out.println("Vilid DECRIPTION  :"+((cipher.compareTo(n)==1)?"false":"true"));
	BigInteger exp;
	exp=cipher.modPow(d, n);
	System.out.println("BitLen is"+exp.bitLength());
	byte[] clearTextData=exp.toByteArray();
	byte[] clearTextData1=new byte[clearTextData.length-1];;
	putBytesIntoBlock(clearTextBlock,clearTextData );
	System.out.println("First Byte  :"+clearTextData[0]);
	if(clearTextData[0]==0){System.arraycopy(clearTextData, 1, clearTextData1, 0, clearTextData.length-1);return clearTextData1;}
	else return clearTextData;//clearTextBlock;
}
byte[] addPadding(byte [] message ,int clearTextSize){
	if (message.length==clearTextSize)return message;
	System.arraycopy(message, 0, clearTextBlock, 0, message.length-1);
	int pad_size=clearTextSize -message.length ;
	int Number_of_padding_bytes=( pad_size ) % clearTextSize;
	byte paddin_val=(byte)( Number_of_padding_bytes % clearTextSize);
	for (int i=0; i<pad_size; i++) {    
		clearTextBlock[clearTextBlock.length-i-1] =  paddin_val;
    }
	return clearTextBlock;
}
void cutPadding(byte [] message){}
}
