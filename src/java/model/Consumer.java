/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A consumer<br/>
 * Extends ConsumerProvider
 * 
 * @author Louis
 */

@XmlRootElement(name = "consumer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Consumer {

	/**
	 * Id of the Consumer/Provider
	 */
	private int id;
        
        /**
	 * Request by seconds send by the producer
	 */
	private int requestBySeconde;

	public Consumer() {

	}

	public Consumer(int id, int requestBySeconde) {
		this.id = id;
                this.requestBySeconde = requestBySeconde;
	}

        
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
        
	public int getRequestBySeconde() {
		return requestBySeconde;
	}

	public void setRequestBySeconde(int requestBySeconde) {
		this.requestBySeconde = requestBySeconde;
	}

    @Override
    public String toString() {
        return "cons: "+id+" "+requestBySeconde+"\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Consumer other = (Consumer) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.requestBySeconde != other.requestBySeconde) {
            return false;
        }
        return true;
    }
        
        
}
