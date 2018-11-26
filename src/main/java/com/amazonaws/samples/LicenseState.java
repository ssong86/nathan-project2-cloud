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

    String photo = "plate-3.jpg"; // images in s3
    String bucket = "nathan-rekog-demo"; // my bucket name
    String[] usStates = {"California", "Alabama", "Arkansas", "Arizona", "Alaska", "Colorado", "Connecticut", 
    		"Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", 
    		"Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
    		"Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", 
    		"North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", 
    		"South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", 
    		"West Virginia", "Wisconsin", "Wyoming" }; // 50 us states

    AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion("us-west-2").build();

    DetectTextRequest request = new DetectTextRequest()
            .withImage(new Image()
            .withS3Object(new S3Object()
            .withName(photo)
            .withBucket(bucket)));
  

    try {
       DetectTextResult result = rekognitionClient.detectText(request);
       List<TextDetection> textDetections = result.getTextDetections();

       
       System.out.println("Test " + textDetections);
       

       System.out.println("Detected lines and words for " + photo);
       
       
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