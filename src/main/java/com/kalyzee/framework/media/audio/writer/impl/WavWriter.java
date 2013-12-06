package com.kalyzee.framework.media.audio.writer.impl;

import java.io.OutputStream;

import com.kalyzee.framework.media.audio.api.IAudio;
import com.kalyzee.framework.media.audio.writer.api.IWriter;

public class WavWriter extends AbstractWriter implements IWriter{

	public void write(OutputStream outputStream, IAudio audio) {
		
	}

	public boolean isCompatible(String extension) {
		return extension.trim().toLowerCase().equals("wav");
	}
}
