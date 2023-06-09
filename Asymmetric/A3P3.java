package asymmetric;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class A3P3 {
	/*Alice and Bob use RSA to communicate. One day, Alice wanted to send a message m to Bob so she encrypted it (without padding) 
	using his public exponent e and modulus n and sent the obtained ciphertext c to him. You need to find out the content of the message 
	given only the information in this program fragment (you may want to right-click this link and save it rather than copy-and-paste from 
	the screen to avoid potential errors):
	BigInteger phi = new BigInteger("8584037913642434144111279062847405921823163865842701785008602377400681495147541519557274092429073976252689387304835782258785521935078205581766754116919200");
	BigInteger q = new BigInteger("87020952829623092932322362936864583897972618059974315662422560067745889600571");
	BigInteger e = new BigInteger("65537");
	BigInteger c = new BigInteger("1817487313698347891034157970684926175211834109573277793102901854482611726141560963120214926234448852417078321539316776648109260519063106558303669880226359");
	Note that the fragment lets you know q (one of the two primes that Bob uses for his RSA), phi (Bob's Euler totient),
	e (his public exponent), and c (the ciphertext he received). Note, however, that n is not known. 
	If the message m can be expressed as a string of English characters before printing it. Otherwise, write Not Printable.*/
	public static void main(String[] args) throws Exception{
		BigInteger phi = new BigInteger("8584037913642434144111279062847405921823163865842701785008602377400681495147541519557274092429073976252689387304835782258785521935078205581766754116919200");
		BigInteger q = new BigInteger("87020952829623092932322362936864583897972618059974315662422560067745889600571");
		BigInteger e = new BigInteger("65537");
		BigInteger c = new BigInteger("1817487313698347891034157970684926175211834109573277793102901854482611726141560963120214926234448852417078321539316776648109260519063106558303669880226359");
		BigInteger p = phi.divide(q.subtract(BigInteger.ONE)).add(BigInteger.ONE);
		BigInteger n = p.multiply(q);
		BigInteger d = e.modInverse(phi);

		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e);
		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d);
		PublicKey pub = keyFactory.generatePublic(pubSpec);
		PrivateKey priv = keyFactory.generatePrivate(privSpec);
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, priv);
		byte[] pt = cipher.doFinal(c.toByteArray());
		System.out.println("The answer is " + new String(pt));
	}
}
