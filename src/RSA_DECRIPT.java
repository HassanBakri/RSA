import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.math.BigInteger;

public class RSA_DECRIPT {
		FileInputStream is ;
		FileOutputStream os ;
		BigInteger priKey;
		BigInteger n;
		static File file;
		RSA_E_D rsa;
		public RSA_DECRIPT(){
			try {
				priKey= getPrivateKey("private.key");	
				rsa=new RSA_E_D(priKey.bitLength());		
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
			e.printStackTrace();}
		}
		public RSA_DECRIPT(String input,String output) throws FileNotFoundException {
			this();
			file=new File(input);
			is= new FileInputStream(input);//"encrypted.txt"
			os= new FileOutputStream(output);//"temp\\decrypted.txt"
		}
		public RSA_DECRIPT(String input,String output,String keyFileName) throws IOException {
			this();
			file=new File(input);
			is= new FileInputStream(input);//"encrypted.txt"
			os= new FileOutputStream(output);//"temp\\decrypted.txt"
			priKey= getPrivateKey(keyFileName);
		}
		public void rsaDECRYPTFile() throws  IOException {
			doCopy(is, os);
			}
		public byte[] rsaDECRYPT(byte[] data) throws IOException {
			  byte[] cipherData = rsa.decript(data,priKey,n);
			  return cipherData;
			}
		int dataSize;
		public  void doCopy(InputStream is, OutputStream os) throws IOException {
			byte[] bytes = new byte[rsa.cipherTextSize];//(int) file.length()
			byte [] ouput=new byte[rsa.clearTextSize];	
			int numBytes;
			System.out.println("r");
			try {
			while ((numBytes = is.read(bytes)) != -1) {
			ouput =  rsa.decript(bytes,priKey,n);
			System.out.println(numBytes+" Bytes readed From Ciphertext\nPlin  Text:"+new String(ouput,"UTF-8")+"\n"+ouput.length+"Pain text Length");
			/*dataSize = rsa.clearTextSize;
            if (is.available()==0) {
              dataSize = getDataSize(ouput);
              System.out.println("Plain text :"+dataSize);
            }
            if (dataSize>0) {
               os.write(ouput,0,dataSize);
               os.flush();
            }*/System.out.println(ouput[0]+"\t"+ouput[1]+"\t"+ouput.length);
			 os.write(ouput);//,1,ouput.length-1);
			}
			}catch (Exception e) {
				System.err.println(e);			}
			
			is.close();}
		 public static int getDataSize(byte[] block) {
		      int bSize = block.length;
		      int padValue = block[bSize-2];
		      return (bSize-padValue)%bSize;
		   }
		public BigInteger getPrivateKey(String keyFileName) throws FileNotFoundException, IOException{
			File f=new File(keyFileName);
			FileInputStream fis=new FileInputStream(f);
			ObjectInputStream ois =   new ObjectInputStream(fis);
			  try {
			     n = (BigInteger) ois.readObject();
			    BigInteger e = (BigInteger) ois.readObject();
			  return e;
			  } catch (Exception e) {
			    throw new RuntimeException("Spurious serialisation error", e);
			  } finally{
				if(ois != null){
				ois.close();
				if(fis != null){
					fis.close();
				}}
			}
		}
		public static void main(String[] args) throws IOException {
			try {
				
			RSA_DECRIPT dec=new RSA_DECRIPT("encrypted","DICRIPTED" );
			dec.rsaDECRYPTFile();
			} catch (FileNotFoundException e) {				e.printStackTrace();
			}
		}
}
