package com.kalyzee.framework.media.audio.writer.api;

import java.io.OutputStream;

import com.kalyzee.framework.media.audio.api.IAudio;

public interface IWriter {
	
	void write(OutputStream outputStream, IAudio audio);
	boolean isCompatible(String extensionFormat);
}
