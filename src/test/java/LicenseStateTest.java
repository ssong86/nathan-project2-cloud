import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.rekognition.model.DetectTextRequest;
import com.amazonaws.services.rekognition.model.DetectTextResult;
import com.amazonaws.services.rekognition.model.TextDetection;
import java.util.List;
import java.util.Arrays;

public class LicenseStateTest {

	public static void main(String[] args) throws Exception {
	
	    String[] photos = {"nplate-0.jpg", "nplate-1.png", "nplate-2.png", "nplate-3.jpg", "nplate-4.jpg", "nplate-5.jpg",
	    		"nplate-6.jpg", "nplate-7.jpg", "nplate-8.jpg", "nplate-9.png","plate-0.jpg", "plate-1.jpg", "plate-2.jpeg", 
	    		"plate-3.jpg", "plate-4.jpg", "plate-5.jpg", "plate-6.jpg", "plate-7.jpg", "plate-8.jpg", "plate-9.jpg", 
	    		"plate-Q.png", "plate-T.png", "plate-X.png", "plate-a.jpg", "plate-b.jpg", "plate-c.jpg", "plate-d.jpg", 
	    		"plate-e.jpg", "plate-f.jpg", "plate-g.jpg", "plate-h.jpg", "plate-i.jpg", "plate-j.jpg", "plate-k.jpg", 
	    		"plate-l.jpg", "plate-m.jpg", "plate-n.jpg", "plate-o.jpg", "plate-p.jpg", "plate-q.jpg", "plate-r.jpg", 
	    		"plate-s.jpg", "plate-t.jpg", "plate-u.jpg", "plate-v.jpg", "plate-w.jpg", "plate-x.jpg", "plate-y.jpg", "plate-z.jpg"} ; // images in s3
		//String photos ="plate-t.jpg";
	   
	    String mybucket = "nathan-rekog-demo"; // my bucket name
	    //detectStates(photos, mybucket);
	    
	    for(int a=0;a<photos.length;a++) {
	    	System.out.println("File read: "+photos[a]);
	    	detectStates(photos[a], mybucket);
	    }
	}
	
    public static void detectStates(String photo, String bucket){
    	String[] usStates = {"California", "Alabama", "Arkansas", "Arizona", "Alaska", "Colorado", "Connecticut", 
        		"Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", 
        		"Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
        		"Missouri", "Montana", "Nebraska", "Nevada", "Newhampshire", "Newjersey", "Newmexico", "Newyork", 
        		"Northcarolina", "Northdakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", 
        		"Southcarolina", "Southdakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", 
        		"Westbirginia", "Wisconsin", "Wyoming" }; // 50 us states
	    AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion("us-west-2").build();
	
	    DetectTextRequest request = new DetectTextRequest()
	            .withImage(new Image()
	            .withS3Object(new S3Object()
	            .withName(photo)
	            .withBucket(bucket)));
	  
	
	    try {
	       DetectTextResult result = rekognitionClient.detectText(request);
	       List<TextDetection> textDetections = result.getTextDetections();
	
	       
	      //System.out.println("Test " + textDetections);	
	      //System.out.println("Detected lines and words for " + photo);
	      //System.out.println("Detected list: "+ textDetections.getDetectedText());
	        
	       for (TextDetection text: textDetections) {
	    	   	   String comp = text.getDetectedText();
	    	   	   comp = comp.substring(0,1).toUpperCase() + comp.substring(1).toLowerCase();
	    	   	   //System.out.println(length);
	    		   //System.out.println("Detected: "+ comp);
	    		   //System.out.println("Confidence: " + text.getConfidence().toString());
	    		   //System.out.println("Id : " + text.getId());
	    		   //System.out.println("Parent Id: " + text.getParentId());
	    		   //System.out.println("Type: " + text.getType());
	    	   	  
	    	   	   for(int i=0; i < usStates.length; i++) {
	    	   		   //System.out.println(usStates[i]);
	    	   		   //System.out.println();
	    	   		   if(usStates[i].equals(comp)) {
	    	   			   System.out.println("Detected state: " + comp);
	    	   			   return;
	    	   		   }
	    	   	   }
	               
	       }
	    } catch(AmazonRekognitionException e) {
	       e.printStackTrace();
	    }
 }
}
