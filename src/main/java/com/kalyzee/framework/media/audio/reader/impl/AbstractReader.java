package com.kalyzee.framework.media.audio.reader.impl;

import java.io.IOException;
import java.io.InputStream;

import com.kalyzee.framework.media.audio.reader.api.IReader;

public abstract class AbstractReader implements IReader{
	
	protected int read2ToInt(InputStream is) throws IOException{
		byte[] array = new byte[2];
		is.read(array);
		return ((array[1] & 0xFF) << 8 | (array[0] & 0xFF));
	}
	
	protected int read4ToInt(InputStream is) throws IOException{
		byte[] array = new byte[4];
		is.read(array);
		return ((array[3] & 0xFF) << 24) | ((array[2] & 0xFF) << 16)
				| ((array[1] & 0xFF) << 8) | (array[0] & 0xFF);
	
	}
	
	protected String readToString(InputStream is, int numBytes) throws IOException{
		byte[] array = new byte[numBytes];
		is.read(array);
		return new String(array);
	}
	
}
