package com.seaboat.mysql.protocol;

/**
 * 
 * @author seaboat
 * @date 2016-09-25
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>server capabilities .</p>
 */
public interface Capabilities {

	// new more secure passwords
	int CLIENT_LONG_PASSWORD = 1;

	// Found instead of affected rows
	int CLIENT_FOUND_ROWS = 2;

	// Get all column flags
	int CLIENT_LONG_FLAG = 4;

	// One can specify db on connect
	int CLIENT_CONNECT_WITH_DB = 8;

	// Don't allow database.table.column
	int CLIENT_NO_SCHEMA = 16;

	// Can use compression protocol
	int CLIENT_COMPRESS = 32;

	// Odbc client
	int CLIENT_ODBC = 64;

	// Can use LOAD DATA LOCAL
	int CLIENT_LOCAL_FILES = 128;

	// Ignore spaces before '('
	int CLIENT_IGNORE_SPACE = 256;

	// New 4.1 protocol This is an interactive client
	int CLIENT_PROTOCOL_41 = 512;

	// This is an interactive client
	int CLIENT_INTERACTIVE = 1024;

	// Switch to SSL after handshake
	int CLIENT_SSL = 2048;

	// IGNORE sigpipes
	int CLIENT_IGNORE_SIGPIPE = 4096;

	// Client knows about transactions
	int CLIENT_TRANSACTIONS = 8192;

	// Old flag for 4.1 protocol
	int CLIENT_RESERVED = 16384;

	// New 4.1 authentication
	int CLIENT_SECURE_CONNECTION = 32768;

	// Enable/disable multi-stmt support
	int CLIENT_MULTI_STATEMENTS = 65536;

	// Enable/disable multi-results
	int CLIENT_MULTI_RESULTS = 131072;

}
