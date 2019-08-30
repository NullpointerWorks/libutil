/**
 * Creative Commons - Attribution, Share Alike 4.0<br>
 * Nullpointer Works (2019)<br>
 * Use of this library is subject to license terms.<br>
 * @version 1.0.0 pre-release
 * @author Michiel Drost - Nullpointer Works
 */
module libnpw.util
{
	requires transitive java.desktop;
	
	exports com.nullpointerworks.util;
	exports com.nullpointerworks.util.concurrency;
	exports com.nullpointerworks.util.file;
	exports com.nullpointerworks.util.file.bytefile;
	exports com.nullpointerworks.util.file.textfile;
	exports com.nullpointerworks.util.log;
	exports com.nullpointerworks.util.pack;
	exports com.nullpointerworks.util.pattern;
	exports com.nullpointerworks.util.sorting;
	exports com.nullpointerworks.util.threading;
	exports com.nullpointerworks.util.threading.event;
	exports com.nullpointerworks.util.threading.pool;
	exports com.nullpointerworks.util.timing;
	
	exports exp.nullpointerworks.util;
	exports exp.nullpointerworks.util.file;
}
