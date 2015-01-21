/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import model.Result;

import java.io.FileNotFoundException;

/**
 *
 * @authors alpha, Julie
 */
public class ResultHandler {
    
    private Result result;
    
    /**
     * 
     * @param result 
     */
    public ResultHandler(Result result) {
        super();
        this.result = result;
    }
    
    /**
     * Write the results in a file
     * @param filepath 
     */
    public void createResultFile(String filepath){
    	try {
                if (result != null) {
                    result.writeResult(filepath);
                }else {
                    System.out.println("no result");
                }
            } catch (FileNotFoundException e) {
                    fileNotFoundException(e);
            }
    }
	
	/**
	 * When a file is not found
	 */
	private void fileNotFoundException(FileNotFoundException e){
		System.err.println("ResultHandler Error : File not found");
		e.printStackTrace();
	}
    
}
