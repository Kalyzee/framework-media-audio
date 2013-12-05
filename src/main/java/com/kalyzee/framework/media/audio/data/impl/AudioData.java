package com.kalyzee.framework.media.audio.data.impl;

import com.kalyzee.framework.media.audio.data.api.IAudioData;

public class AudioData implements IAudioData{

	private byte[] data;
	
	public byte[] getData() {
		return data;
	}
	
	public void setData(byte[] data){
		this.data = data;
	}

}
