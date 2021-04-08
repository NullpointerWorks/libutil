/**
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 *
 * @version 1.0.1
 * @author Michiel Drost - Nullpointer Works
 */
module libnpw.util
{
	requires transitive java.desktop;
	
	exports com.nullpointerworks.util;
	exports com.nullpointerworks.util.concurrency;
	exports com.nullpointerworks.util.concurrency.event;
	exports com.nullpointerworks.util.concurrency.pool;
	exports com.nullpointerworks.util.file;
	exports com.nullpointerworks.util.file.bytefile;
	exports com.nullpointerworks.util.file.textfile;
	exports com.nullpointerworks.util.log;
	exports com.nullpointerworks.util.pack;
	exports com.nullpointerworks.util.pattern;
	exports com.nullpointerworks.util.sorting;
	exports com.nullpointerworks.util.timing;
}
