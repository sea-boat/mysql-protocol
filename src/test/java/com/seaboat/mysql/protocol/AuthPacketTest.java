package com.seaboat.mysql.protocol;

import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.seaboat.mysql.protocol.AuthPacket;
import com.seaboat.mysql.protocol.Capabilities;
import com.seaboat.mysql.protocol.util.HexUtil;
import com.seaboat.mysql.protocol.util.RandomUtil;
import com.seaboat.mysql.protocol.util.SecurityUtil;

/**
 * 
 * @author seaboat
 * @date 2016-09-25
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>test auth packet.</p>
 */
public class AuthPacketTest {
	@Test
	public void produce() {
		// handshake packet's rand1 and rand2
		byte[] rand1 = RandomUtil.randomBytes(8);
		byte[] rand2 = RandomUtil.randomBytes(12);
		byte[] seed = new byte[rand1.length + rand2.length];
		System.arraycopy(rand1, 0, seed, 0, rand1.length);
		System.arraycopy(rand2, 0, seed, rand1.length, rand2.length);

		AuthPacket auth = new AuthPacket();
		auth.packetId = 0;
		auth.clientFlags = getClientCapabilities();
		auth.maxPacketSize = 1024 * 1024 * 16;
		auth.user = "seaboat";
		try {
			auth.password = SecurityUtil
					.scramble411("seaboat".getBytes(), seed);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		auth.database = "test";
        
		ByteBuffer buffer = ByteBuffer.allocate(256);
		auth.write(buffer);
		buffer.flip();
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes, 0, bytes.length);
		String result = HexUtil.Bytes2HexString(bytes);
		System.out.println(result);
		assertTrue(Integer.valueOf(result.substring(0, 2), 16) == result
				.length() / 2 - 4);
		
		AuthPacket auth2 = new AuthPacket();
		auth2.read(bytes);
		assertTrue(auth2.database.equals("test"));
	}

	protected int getClientCapabilities() {
		int flag = 0;
		flag |= Capabilities.CLIENT_LONG_PASSWORD;
		flag |= Capabilities.CLIENT_FOUND_ROWS;
		flag |= Capabilities.CLIENT_LONG_FLAG;
		flag |= Capabilities.CLIENT_CONNECT_WITH_DB;
		flag |= Capabilities.CLIENT_ODBC;
		flag |= Capabilities.CLIENT_IGNORE_SPACE;
		flag |= Capabilities.CLIENT_PROTOCOL_41;
		flag |= Capabilities.CLIENT_INTERACTIVE;
		flag |= Capabilities.CLIENT_IGNORE_SIGPIPE;
		flag |= Capabilities.CLIENT_TRANSACTIONS;
		flag |= Capabilities.CLIENT_SECURE_CONNECTION;
		return flag;
	}
}
