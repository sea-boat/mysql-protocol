package com.seaboat.mysql.protocol;

/**
 * 
 * @author seaboat
 * @date 2016-09-25
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>proxy's version.</p>
 */
public interface Versions {

	byte PROTOCOL_VERSION = 10;

	byte[] SERVER_VERSION = "5.6.0-snapshot".getBytes();
}