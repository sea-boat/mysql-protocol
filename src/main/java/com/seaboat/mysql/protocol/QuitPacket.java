package com.seaboat.mysql.protocol;

/**
 * 
 * @author seaboat
 * @date 2016-09-25
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>quit command packet.</p>
 */
public class QuitPacket extends MySQLPacket {
	// payload length is 1,packet id is 0,payload is 1
	public static final byte[] QUIT = new byte[] { 1, 0, 0, 0, 1 };

	@Override
	public int calcPacketSize() {
		return 1;
	}

	@Override
	protected String getPacketInfo() {
		return "MySQL Quit Packet";
	}

}
