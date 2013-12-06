package com.kalyzee.framework.media.audio.header.impl;

import java.util.HashMap;
import java.util.Map;

import com.kalyzee.framework.media.audio.header.api.IAudioHeader;


public class AudioHeader implements IAudioHeader{
	
	private Map<String, String> metadatas;
	private int byteRate;
	private int sampleRate;
	private int numChannels;
	
	public AudioHeader(){
		metadatas = new HashMap<String, String>();
	}
	
	
	public int getByteRate() {
		return byteRate;
	}

	
	public int getSampleRate() {
		return sampleRate;
	}

	
	public int getNumChannels() {
		return numChannels;
	}

	
	public String getMetadata(String key) {
		return metadatas.get(key);
	}

	
	public void setByteRate(int byteRate) {
		this.byteRate = byteRate;
	}

	
	public void setSampleRate(int sampleRate) {	
		this.sampleRate = sampleRate;
		
	}

	public void setNumChannels(int channels) {
		this.numChannels = channels;
		
	}

	
}
