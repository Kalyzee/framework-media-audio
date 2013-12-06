package com.kalyzee.framework.media.audio.data.impl;

import java.io.InputStream;

import com.kalyzee.framework.media.audio.data.api.IAudioData;

public class AudioData implements IAudioData{

	private InputStream inputStream;
	private int size;
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public void setInputStream(InputStream inputStream){
		this.inputStream = inputStream;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
