/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author samsung
 */
public class logHelper {
    
    private int processingTime;
    private long sentTime;
    private long recievedTime;

    public logHelper() {
    }
    
    public logHelper(int processingTime) {
        this.processingTime = processingTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public long getSentTime() {
        return sentTime;
    }

    public void setSentTime(long sentTime) {
        this.sentTime = sentTime;
    }

    public long getRecievedTime() {
        return recievedTime;
    }

    public void setRecievedTime(long recievedTime) {
        this.recievedTime = recievedTime;
    }

    @Override
    public String toString() {
        return "{" + "processingTime=" + processingTime + ", sentTime=" + sentTime + ", recievedTime=" + recievedTime + "}\n";
    }
    
    
}
