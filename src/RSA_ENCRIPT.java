import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.math.BigInteger;

public class RSA_ENCRIPT {
		FileInputStream is ;
		FileOutputStream os ;
		BigInteger pubKey;
		BigInteger n;
		RSA_E_D rsa;
	public RSA_ENCRIPT() throws IOException {
			pubKey = readKeyFromFile("public.key");
			rsa=new RSA_E_D(pubKey.bitLength());
		}
	public RSA_ENCRIPT(String input,String output) throws IOException {
		this();
		is= new FileInputStream(input);//"original.txt"
		os= new FileOutputStream(output);//"encrypted.txt"
		}
	public RSA_ENCRIPT(String input,String output,String keyFileName) throws IOException {
		this();
		is= new FileInputStream(input);//"original.txt"
		os= new FileOutputStream(output);//"encrypted.txt"
		pubKey = readKeyFromFile(keyFileName);
	}
	public byte[] rsaEncrypt(byte[] data) throws  IOException{
		byte[] cipherData = rsa.encript(data, pubKey, n);
		return cipherData;
		}
	public void rsaEncryptFile() throws  IOException{
		doCopy(is, os);
		}
	public void doCopy(InputStream is, OutputStream os) throws IOException {
		int k=127;//(int) Math.floor((n.bitLength()-1)/8);
		byte[] bytes = new byte[k];
		byte [] ouput=new byte[k];
		int numBytes;
		System.out.println("r");
		try {
			while ((numBytes = is.read(bytes)) != -1) {
				byte[] x=new byte[numBytes];
				System.arraycopy(bytes, 0, x, 0, numBytes);
			ouput = rsa.encript(x, pubKey, n);
			System.out.println(numBytes+" Bytes Readed from Plian text\nPlain Text   :"+new String(bytes)+"\n"
								+ouput.length+"   Cipher text length"+"\nCipher text    :"+new String(ouput));
			os.write(ouput);
			}}finally{
			System.out.println("a");
		os.flush();
		is.close();}
	}
	public BigInteger readKeyFromFile(String keyFileName) throws IOException {
		  //InputStream in =    ServerConnection.class.getResourceAsStream(keyFileName);
		File f=new File(keyFileName);
		FileInputStream fis=new FileInputStream(f);
		  ObjectInputStream ois =   new ObjectInputStream(fis);
		  try {
		    n = (BigInteger) ois.readObject();
		    pubKey = (BigInteger) ois.readObject();
		    return pubKey;
		  	} catch (Exception e) {
		  		throw new RuntimeException("Spurious serialisation error", e);
		  	}  finally{
				if(ois != null){
				ois.close();
				if(fis != null){
				fis.close();
				}
				}
			}
		}
public static void main(String[] args) {
		try {
			RSA_ENCRIPT enc=new RSA_ENCRIPT("0.png", "encrypted");
			enc.rsaEncryptFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
