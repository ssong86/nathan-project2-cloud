package com.amazonaws.samples;

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

public class LicenseState {

 public static void main(String[] args) throws Exception {

    String photo = "plates/plate-0.jpg"; // images in s3
    String bucket = "nathan-rekog-demo"; // my bucket name
    String[] usStates = {"california", "alabama", "arkansas", "arizona", "alaska", "colorado", "connecticut", 
    		"delaware", "florida", "georgia", "hawaii", "idaho", "illinois", "indiana", "iowa", "kansas", 
    		"kentucky", "louisiana", "maine", "maryland", "massachusetts", "michigan", "minnesota", "mississippi",
    		"missouri", "montana", "nebraska", "nevada", "new hampshire", "new jersey", "new mexico", "new york", 
    		"north carolina", "north dakota", "ohio", "oklahoma", "oregon", "pennsylvania", "rhode island", 
    		"south carolina", "southdakota", "tennessee", "texas", "utah", "vermont", "virginia", "washington", 
    		"west virginia", "wisconsin", "wyoming" }; // 50 us states
    String foundState = "";

    AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion("us-west-2").build();

    DetectTextRequest request = new DetectTextRequest()
            .withImage(new Image()
            .withS3Object(new S3Object()
            .withName(photo)
            .withBucket(bucket)));
  

    try {
       DetectTextResult result = rekognitionClient.detectText(request);
       List<TextDetection> textDetections = result.getTextDetections();

       
       //System.out.println("Test: " + textDetections);
       //String results = textDetections.get("LINE");

       //System.out.println("Detected lines and words for " + photo);
       
       
       //System.out.println("Detected list: "+ textDetections.getDetectedText());
       for (TextDetection text: textDetections) {
	   	   String comp = text.getDetectedText();
	   	   String type = text.getType();
	   	   comp = comp.substring(0).toLowerCase();
	   	   
	   	   //System.out.println(length);
	   	   //System.out.println("Detected: "+ comp);
	   	   //System.out.println("Confidence: " + text.getConfidence().toString());
	   	   //System.out.println("Id : " + text.getId());
	   	   //System.out.println("Parent Id: " + text.getParentId());
	   	   //System.out.println("Type: " + text.getType());  
	   	  
	   	   //if(type.equals("LINE"))
	   	   
	   	   
	   	   for(int i=0; i < usStates.length; i++) {
	   		   //System.out.println(usStates[i]);
	   		   //System.out.println();
	   		   
	   		   if(comp.contains(usStates[i]) && type.equals("LINE")) { //type line
	   			   foundState = usStates[i];
	   			   if(foundState.equals("hampshire")||foundState.equals("jersey")||foundState.equals("mexico")||foundState.equals("york")) {
	   				   foundState = "new " + foundState;
	   				   
	   			   }
	   			   
	   			   System.out.println("Detected state: " + foundState);
	   			   return;
	   		   }
	   		   
	   	   }
	             
       }
       if(foundState.equals("")) {
		   System.out.println("No detection");
		   //return;
	   }
    } catch(AmazonRekognitionException e) {
       e.printStackTrace();
    }
 }
}
   
    /*DetectLabelsRequest request = new DetectLabelsRequest()
         .withImage(new Image()
         .withS3Object(new S3Object()
         .withName(photo).withBucket(bucket)))
         .withMaxLabels(10)
         .withMinConfidence(75F);

    try {
       DetectLabelsResult result = rekognitionClient.detectLabels(request);
       List <Label> labels = result.getLabels();

       System.out.println("Detected labels for " + photo);
       for (Label label: labels) {
          System.out.println(label.getName() + ": " + label.getConfidence().toString());
       }
    } catch(AmazonRekognitionException e) {
       e.printStackTrace();
    }
 }
}*/
