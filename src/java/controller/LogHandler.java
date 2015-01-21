package controller;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import model.LinkConsumerProvider;
import model.ResponseTime;
import model.Result;
import model.TotalResult;
import model.logHelper;

/**
 *
 * @author Oussama, belliot
 */
public class LogHandler {

     private boolean firstMessage;
    private long firstMessageTime;
    private long lastMessageTime;
    private final int  PARAM=1000000;
    // link, threadId, logHelper
    private HashMap<String, HashMap<String, logHelper>> theLog;

    public LogHandler() {
        theLog = new HashMap<String, HashMap<String, logHelper>>();
    }

    public void add(String log) {
        String[] data = log.split(";");
        // 0 : Id linkProviderConsumer
        // 1 : sent or recieved
        // 2 : TO DO : processing time  
        // 3 : message
        // 4 : id thread
        // 5 : timestamp

        HashMap<String, logHelper> lineOfLog;

        // if not found, create row for word and save reference
        // if able to find row, save reference to hash map of data
        if ((lineOfLog = theLog.get(data[0])) == null) {
            theLog.put(data[0], new HashMap<String, logHelper>());
            lineOfLog = theLog.get(data[0]);
        }

        logHelper littleHelper;

        if ((littleHelper = lineOfLog.get(data[4])) == null) {
            lineOfLog.put(data[4], new logHelper(-1));
            littleHelper = lineOfLog.get(data[4]);
        }

        if (data[1].equals("sent")) {
            littleHelper.setSentTime(Long.parseLong(data[5]));
        } else if (data[1].equals("recieved")) {
            littleHelper.setProcessingTime(Integer.parseInt(data[2]));
            littleHelper.setRecievedTime(Long.parseLong(data[5]));
        }
    }
    
     
    public Result fillInResultForm(HashMap<String, HashMap<String, logHelper>> theLog) {
        long maxRespTime = 0;
        long minRespTime = Integer.MAX_VALUE;
        long[] avRespTime = new long[theLog.size()];
        int lostReq = 0;
        
        long timeTemp=0;
         long timeTempMin=0;
        long averageTemp;
        
        int counter = 0;
        
        ArrayList<LinkConsumerProvider> listConsProv = new ArrayList<LinkConsumerProvider>();
        LinkConsumerProvider lcp = null;

        /***** Calcul of the result variables *****/
        
        // Access to link Cons Prov
        for (Entry<String, HashMap<String, logHelper>> subLog : theLog.entrySet()) {
            // Access to thread
            averageTemp = 0;
            for (Entry<String, logHelper> smallLog : subLog.getValue().entrySet()) {
                // Test if the request is lost
                if (smallLog.getValue().getProcessingTime() != -1) {
                    timeTemp = smallLog.getValue().getRecievedTime()
                             - smallLog.getValue().getSentTime()
                             - smallLog.getValue().getProcessingTime()*1000000;
                  // timeTempMin= timeTemp;
                    // If it is the maximum time
                  /// System.out.println(timeTemp/PARAM);
                    if (timeTemp > maxRespTime){
                       maxRespTime= timeTemp;
                    }
                    // If it is the minimum time
                    if (timeTemp < minRespTime){
                        minRespTime= timeTemp;
                    }
                    
                    averageTemp += timeTemp;
                     
                }
                else {
                    // The request is lost
                    lostReq++;
                }
                
            }
            
            // Calcul of the average time for on link Cons Prov
            averageTemp = averageTemp / subLog.getValue().size();
            avRespTime[counter] = averageTemp;
            
            // Add the current link Cons Prov to the ArrayList
            lcp = new LinkConsumerProvider(String.valueOf((averageTemp/PARAM)),
                    String.valueOf(counter + 1), String.valueOf(counter + 1));
            listConsProv.add(lcp);
            
            // Use to fill the average-response-time table
            counter++;
        }
        
        // Calcul of the global average response time
        averageTemp = 0;
        for (int i = 0; i < avRespTime.length; i++){
            averageTemp += avRespTime[i];
        }
        averageTemp = averageTemp / (long) avRespTime.length;
        
        /******************************************/
        
        
        /***** Creation of the Result instance *****/
        
        // /!\ Not sure for the "sec" parameter
        ResponseTime responseTime = new ResponseTime("ms", String.valueOf((maxRespTime/PARAM)), String.valueOf((minRespTime/PARAM)));
        TotalResult totalResult = new TotalResult(String.valueOf((averageTemp/PARAM)), String.valueOf(lostReq), responseTime);

        Result result = new Result(totalResult,
                listConsProv.toArray(new LinkConsumerProvider[listConsProv.size()]));
        
        /*******************************************/
        
        return result;
    
    }
    
    
    @Override
    public String toString() {
        String message = new String();
        message = theLog.toString();
        return message;
    }
    
    
}
