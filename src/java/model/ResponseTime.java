/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @authors alpha, Julie
 */
public class ResponseTime {
    
    /**
     * Attributes
     */
    
    private String timeUnit;

    private String maxResponseTime;

    private String minResponseTime;
    
    /**
     * Constructor
     * @param timeUnit
     * @param maxResponseTime
     * @param minResponseTime 
     */
    public ResponseTime(String timeUnit, String maxResponseTime, String minResponseTime) {
        this.setTimeUnit(timeUnit);
        this.setMaxResponseTime(maxResponseTime);
        this.setMinResponseTime(minResponseTime);
    }
    
    public ResponseTime(){
        
    }

    /**
     * Getters and setters
     * @return 
     */
    
    @XmlAttribute
    public String getTimeUnit ()
    {
        return timeUnit;
    }

    public void setTimeUnit (String timeUnit)
    {
        this.timeUnit = timeUnit;
    }

    public String getMaxResponseTime ()
    {
        return maxResponseTime;
    }

    public void setMaxResponseTime (String maxResponseTime)
    {
        this.maxResponseTime = maxResponseTime;
    }

    public String getMinResponseTime ()
    {
        return minResponseTime;
    }

    public void setMinResponseTime (String minResponseTime)
    {
        this.minResponseTime = minResponseTime;
    }
}
