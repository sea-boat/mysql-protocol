package com.seaboat.mysql.protocol;

/**
 * 
 * @author seaboat
 * @date 2016-09-25
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>ping command packet.</p>
 */
public class PingPacket extends MySQLPacket {
	// payload length is 1,packet id is 0,payload is 0e
	public static final byte[] PING = new byte[] { 1, 0, 0, 0, 14 };

	@Override
	public int calcPacketSize() {
		return 1;
	}

	@Override
	protected String getPacketInfo() {
		return "MySQL Ping Packet";
	}

}
