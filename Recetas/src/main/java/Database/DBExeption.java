package Database;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;

import org.apache.wicket.util.parse.metapattern.parsers.WordParser;




public class DBExeption extends Exception {

		     private Set<DBMessage> dbMessages = new TreeSet<DBMessage>();
		
		     public DBExeption(List<DBMessage> dbMessages) {
		         this.dbMessages.addAll(dbMessages);
		     }
		
		     public DBExeption(DBMessage dbMessage) {
		         this.dbMessages.add(dbMessage);
		     }
		
		     public DBExeption(Exception ex) {
		    	 dbMessages.add(new DBMessage(null, ex.toString()));
		     }
		
		     public DBExeption(javax.validation.ConstraintViolationException cve) {
		         for (ConstraintViolation constraintViolation : cve.getConstraintViolations()) {
		             String fieldName;
		             String message;
		             fieldName = constraintViolation.getPropertyPath().toString();
		             //fieldName = getCaptions(constraintViolation.getRootBeanClass(), constraintViolation.getPropertyPath());
		             message = constraintViolation.getMessage();
		
		             dbMessages.add(new DBMessage(fieldName, message));
		         }
		     }
		
		     public DBExeption(org.hibernate.exception.ConstraintViolationException cve) {
		    	 dbMessages.add(new DBMessage(null, cve.getLocalizedMessage()));
		     }
		     
		     public DBExeption(String fieldName, String message) {
		    	 dbMessages.add(new DBMessage(fieldName, message));
		     }
		     public Set<DBMessage> getBussinessMessages() {
		         return dbMessages;
		     }
		     
		     public class DBMessage implements Comparable<DBMessage> {
		    	      private final String fieldName;
		    	      private final String message;
		    	 
		    	      public DBMessage(String fieldName, String message) {
		    	          if (message==null) {
		    	             throw new IllegalArgumentException("message no puede ser null");
		    	          }
		    	 
		    	         if ((fieldName!=null) && (fieldName.trim().equals(""))) {
		    	             this.fieldName =null;
		    	         } else {
		    	             this.fieldName = fieldName;
		    	         }
		    	         this.message = message;
		    	     }
		    	
		    	     @Override
		    	     public String toString() {
		    	         if (fieldName!=null) {
		    	             return "'"+fieldName+ "'-"+message;
		    	         } else {
		    	             return message;
		    	         }
		    	     }
		    	
		    	     /**
		    	      * @return the fieldName
		    	      */
		    	     public String getFieldName() {
		    	         return fieldName;
		    	     }
		    	
		    	     /**
		    	      * @return the message
		    	      */
		    	     public String getMessage() {
		    	         return message;
		    	     }
		    	
		    	
		    	     @Override
		    	     public int compareTo(DBMessage o) {
		    	         if ((getFieldName()==null) && (o.getFieldName()==null)) {
		    	             return getMessage().compareTo(o.getMessage());
		    	         } else if ((getFieldName()==null) && (o.getFieldName()!=null)) {
		    	             return 1;
		    	         } else if ((getFieldName()!=null) && (o.getFieldName()==null)) {
		    	             return -1;
		    	         } else if ((getFieldName()!=null) && (o.getFieldName()!=null)) {
		    	             if (getFieldName().equals(o.getFieldName())) {
		    	                 return getMessage().compareTo(o.getMessage());
		    	             } else {
		    	                 return getFieldName().compareTo(o.getFieldName());
		    	             }
		    	         } else {
		    	            throw new RuntimeException("Error de lógica");
		    	         }
		    	     }
		    	
		    	
		    	 }
}
