package com.kalyzee.framework.media.audio.writer.api;

import java.io.OutputStream;

public interface IWriter {
	
	void write(OutputStream outputStream);
	boolean isCompatible(String extensionFormat);
}
