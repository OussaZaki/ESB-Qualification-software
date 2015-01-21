/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @authors alpha, Julie
 */

@XmlType (propOrder={"responseTime", "averageResponseTime", "lostRequests"})
public class TotalResult {
    
    /**
     * Attributes
     */
    
    private String averageResponseTime;

    private String lostRequests;

    private ResponseTime responseTime;
    
    /**
     * Constructor
     * @param averageResponseTime
     * @param lostRequests
     * @param responseTime 
     */
    public TotalResult(String averageResponseTime, String lostRequests, ResponseTime responseTime) {
        this.setAverageResponseTime(averageResponseTime);
        this.setLostRequests(lostRequests);
        this.setResponseTime(responseTime);
    }
    
    public TotalResult(){
        
    }
    
    /**
     * Getters and setters
     * @return 
     */

    public String getAverageResponseTime ()
    {
        return averageResponseTime;
    }

    public void setAverageResponseTime (String averageResponseTime)
    {
        this.averageResponseTime = averageResponseTime;
    }

    public String getLostRequests ()
    {
        return lostRequests;
    }

    public void setLostRequests (String lostRequests)
    {
        this.lostRequests = lostRequests;
    }

    public ResponseTime getResponseTime ()
    {
        return responseTime;
    }

    public void setResponseTime (ResponseTime responseTime)
    {
        this.responseTime = responseTime;
    }
}
