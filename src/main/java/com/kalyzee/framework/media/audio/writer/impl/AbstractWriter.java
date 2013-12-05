package com.kalyzee.framework.media.audio.writer.impl;

import java.io.IOException;
import java.io.OutputStream;

import com.kalyzee.framework.media.audio.writer.api.IWriter;

public abstract class AbstractWriter implements IWriter{
	
	protected void writeString(OutputStream os, String str) throws IOException{
		os.write(str.getBytes());
	}
	
	protected void writeInt2(OutputStream os, int value) throws IOException{
		os.write(new byte[]{ (byte) (value ) , (byte) (value  >> 8)});
	}
	
	protected void writeInt4(OutputStream os, int value) throws IOException{
		os.write(new byte[]{ (byte) (value ) , (byte) (value  >> 8),  (byte) (value >> 16) , (byte) (value >> 24)});
	}
	

	
}
