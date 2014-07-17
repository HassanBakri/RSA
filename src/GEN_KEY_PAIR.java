import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;

public class GEN_KEY_PAIR {
	public void init() throws Exception{
		KeyPairGenrator kpg = new KeyPairGenrator(1024);
		saveToFile("public.key",kpg.n, kpg.e);
		saveToFile("private.key", kpg.n, kpg.d);
	}
private void saveToFile(String fileName, BigInteger mod, BigInteger exp) throws IOException {
	  ObjectOutputStream oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
	  try {
		  System.out.println("MOd  :"+mod);
		  System.out.println("EXP  :"+exp);
	    oout.writeObject(mod);
	    oout.writeObject(exp);
	  } catch (Exception e) {
	    throw new IOException("Unexpected error", e);
	  } finally {
	    oout.close();
	  }
}
public static void main(String[] args) {
	try {
		new GEN_KEY_PAIR().init();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}