package com.seaboat.mysql.protocol;
/**
 * 
 * <pre><b>quit command packet.</b></pre>
 * @author 
 * <pre>seaboat</pre>
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * @version 1.0
 * @see http://dev.mysql.com/doc/internals/en/com-quit.html
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
