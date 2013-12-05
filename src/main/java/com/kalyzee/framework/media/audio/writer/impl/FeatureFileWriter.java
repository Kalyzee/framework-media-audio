package com.kalyzee.framework.media.audio.writer.impl;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import edu.cmu.sphinx.frontend.Data;
import edu.cmu.sphinx.frontend.DataEndSignal;
import edu.cmu.sphinx.frontend.DoubleData;
import edu.cmu.sphinx.frontend.FloatData;
import edu.cmu.sphinx.frontend.FrontEnd;
import edu.cmu.sphinx.frontend.util.StreamDataSource;
import edu.cmu.sphinx.util.props.ConfigurationManager;



public class FeatureFileWriter {

    private FrontEnd frontEnd;
    private StreamDataSource audioSource;
    private List<float[]> allFeatures;
    private int featureLength = -1;
    
  
    /**
     * Constructs a FeatureFileDumper.
     * 
     * @param cm
     *            the configuration manager
     * @param frontEndName
     *            the name for the frontend
     */
    public FeatureFileWriter(ConfigurationManager cm, String frontEndName)
            throws IOException {
        try {
            frontEnd = (FrontEnd) cm.lookup(frontEndName);
            audioSource = (StreamDataSource) cm.lookup("streamDataSource");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Process the file and store the features
     * 
     * @param inputAudioFile
     *            the input audio file
     * @throws FileNotFoundException
     */
    public void processFile(byte[] audioData) throws FileNotFoundException {
        audioSource
                .setInputStream(new ByteArrayInputStream(audioData), "audio");
        allFeatures = new LinkedList<float[]>();
        getAllFeatures();
        //logger.info("Frames: " + allFeatures.size());
    }

    /**
     * Retrieve all Features from the frontend, and cache all those with actual
     * feature data.
     */
    private void getAllFeatures() {
        /*
         * Run through all the data and produce feature.
         */
        try {
            assert (allFeatures != null);
            
            Data feature = frontEnd.getData();
            
            while (!(feature instanceof DataEndSignal)) {
                if (feature instanceof DoubleData) {
                	System.out.println(((DoubleData) feature).getFirstSampleNumber());
                	System.out.println(((DoubleData) feature).getSampleRate());
                	System.out.println(((DoubleData) feature).getFirstSampleNumber()/(((DoubleData) feature).getSampleRate()));
                    double[] featureData = ((DoubleData) feature).getValues();
                    if (featureLength < 0) {
                        featureLength = featureData.length;
                       // logger.info("Feature length: " + featureLength);
                    }
                    float[] convertedData = new float[featureData.length];
                    for (int i = 0; i < featureData.length; i++) {
                        convertedData[i] = (float) featureData[i];
                    }
                    allFeatures.add(convertedData);
                } else if (feature instanceof FloatData) {
                    float[] featureData = ((FloatData) feature).getValues();
                    if (featureLength < 0) {
                        featureLength = featureData.length;
                      //  logger.info("Feature length: " + featureLength);
                    }
                    allFeatures.add(featureData);
                }
                feature = frontEnd.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the total number of data points that should be written to the
     * output file.
     * 
     * @return the total number of data points that should be written
     */
    private int getNumberDataPoints() {
        return (allFeatures.size() * featureLength);
    }
    
    /**
     * Dumps the feature to the given binary output.
     * 
     * @param outputFile
     *            the binary output file
     */
    public void dumpBinary(String outputFile) throws IOException {
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(
                outputFile));
        outStream.writeInt(getNumberDataPoints());

        for (float[] feature : allFeatures) {
            for (float val : feature) {
                outStream.writeFloat(val);
            }
        }

        outStream.close();
    }

	
}
