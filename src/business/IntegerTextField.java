


package business;
        
import java.text.DecimalFormat;
import javafx.application.Platform;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextField;


class IntegerTextField extends TextField {
    @Override
    public void replaceText(int start, int end, String text) {
      // Really crude attempt at parsing. Probably better ways to do this.

      // Mock up the result of inserting the text "as is"
      StringBuilder mockupText = new StringBuilder(getText());
      mockupText.replace(start, end, text);

      // Strip the commas out, they will need to move anyway
      int commasRemovedBeforeInsert = 0;
      for (int commaIndex = mockupText.lastIndexOf(","); commaIndex >= 0; commaIndex = mockupText
          .lastIndexOf(",")) {
        mockupText.replace(commaIndex, commaIndex + 1, "");
        if (commaIndex < start) {
          commasRemovedBeforeInsert++;
        }
      }

      // Check if the inserted text is ok (still forms a number)
      boolean ok = true;
      int decimalPointCount = 0;
      for (int i = 0; i < mockupText.length() && ok; i++) {
        char c = mockupText.charAt(i);
        if (c == '-') {
          ok = i == 0;
        } else if (c == '.') {
          ok = decimalPointCount == 0;
        } else {
          ok = Character.isDigit(c);
        }
      }

      // if it's ok, insert the commas in the correct place, update the text,
      // and position the carat:
      if (ok) {
        int commasInsertedBeforeInsert = 0;
        int startNonFractional = 0;
        //  System.out.println(mockupText.toString());
        if (mockupText.length() > 0 && mockupText.charAt(0) == '-') {
          startNonFractional = 1;
        }
        int endNonFractional = mockupText.indexOf(".");
        if (endNonFractional == -1) {
          endNonFractional = mockupText.length();
        }

        
        int commaInsertIndex=endNonFractional - 3;
        if(commaInsertIndex > startNonFractional)          mockupText.insert(commaInsertIndex, ",");
        if (commaInsertIndex < start - commasRemovedBeforeInsert
              + text.length()) {
            commasInsertedBeforeInsert++;
          }
        
        commaInsertIndex=endNonFractional-5;
        if(commaInsertIndex > startNonFractional)          mockupText.insert(commaInsertIndex, ",");
        if (commaInsertIndex < start - commasRemovedBeforeInsert
              + text.length()) {
            commasInsertedBeforeInsert++;
          }
        
        commaInsertIndex=endNonFractional-7;
        if(commaInsertIndex > startNonFractional)          mockupText.insert(commaInsertIndex, ",");
        if (commaInsertIndex < start - commasRemovedBeforeInsert
              + text.length()) {
            commasInsertedBeforeInsert++;
          }
        
        //System.out.println(mockupText.toString());
        

        final int caratPos = start - commasRemovedBeforeInsert
            + commasInsertedBeforeInsert + text.length();

        // System.out.printf("Original text: %s. Replaced text: %s. start: %d. end: %d. commasInsertedBeforeInsert: %d. commasRemovedBeforeInsert: %d. caratPos: %d.%n",
        // getText(), mockupText, start, end, commasInsertedBeforeInsert,
        // commasRemovedBeforeInsert, caratPos);

        // update the text:
        this.setText(mockupText.toString());

        // move the carat:
        // Needs to be scheduled to the fx application thread after the current
        // event has finished processing to override
        // default behavior
        // This seems like a bit of a hack...
        Platform.runLater(() -> {
            positionCaret(caratPos);
        });
      }
    }

    @Override
    public void replaceText(IndexRange range, String text) {
      this.replaceText(range.getStart(), range.getEnd(), text);
    }

    @Override
    public void insertText(int index, String text) {
      this.replaceText(index, index, text);
    }

    @Override
    public void deleteText(int start, int end) {

      // special case where user deletes a comma:
      if (start >= 1 && end - start == 1 && getText().charAt(start) == ',') {
        // move cursor back
        this.selectRange(getAnchor() - 1, getAnchor() - 1);
      } else {
        this.replaceText(start, end, "");
      }
    }

    @Override
    public void deleteText(IndexRange range) {
      this.deleteText(range.getStart(), range.getEnd());
    }

    @Override
    public void replaceSelection(String replacement) {
      this.replaceText(getSelection(), replacement);
    }
    
    public static double doubleConvert(String n){
        
        if (n==null || "".equals(n) || "null".equals(n) ) {
            return 0.0 ; 
        }
        StringBuilder sb=new StringBuilder();
        int len=n.length();
        for(int i=0;i<len;i++){
            if(n.charAt(i)!=',')sb.append(n.charAt(i));
        }
        return Double.parseDouble(sb.toString());
        //return 0;
    }
    
    public static String numformat(double n){
        
        int m= (int) n;
        String mockText=String.valueOf(m);
        StringBuilder mockupText=new StringBuilder();
        mockupText.append(mockText);
        
        int startNonFractional = 0;
        //  System.out.println(mockupText.toString());
        if (mockupText.length() > 0 && mockupText.charAt(0) == '-') {
          startNonFractional = 1;
        }
        int endNonFractional = mockupText.indexOf(".");
        if (endNonFractional == -1) {
          endNonFractional = mockupText.length();
        }

        
        int commaInsertIndex=endNonFractional - 3;
        if(commaInsertIndex > startNonFractional)   mockupText.insert(commaInsertIndex, ",");
        
        
        commaInsertIndex=endNonFractional-5;
        if(commaInsertIndex > startNonFractional)          mockupText.insert(commaInsertIndex, ",");
        
        
        commaInsertIndex=endNonFractional-7;
        if(commaInsertIndex > startNonFractional)          mockupText.insert(commaInsertIndex, ",");
        
        return mockupText.toString();
        
    }
    public static String doubleformat(double n){
        
        double m= n;
        
        DecimalFormat df2 = new DecimalFormat(".##");
        String mockText=df2.format(m);
        StringBuilder mockupText=new StringBuilder();
        mockupText.append(mockText);
        
        int startNonFractional = 0;
        //  System.out.println(mockupText.toString());
        if (mockupText.length() > 0 && mockupText.charAt(0) == '-') {
          startNonFractional = 1;
        }
        int endNonFractional = mockupText.indexOf(".");
        if (endNonFractional == -1) {
          endNonFractional = mockupText.length();
        }

        
        int commaInsertIndex=endNonFractional - 3;
        if(commaInsertIndex > startNonFractional)   mockupText.insert(commaInsertIndex, ",");
        
        
        commaInsertIndex=endNonFractional-5;
        if(commaInsertIndex > startNonFractional)          mockupText.insert(commaInsertIndex, ",");
        
        
        commaInsertIndex=endNonFractional-7;
        if(commaInsertIndex > startNonFractional)          mockupText.insert(commaInsertIndex, ",");
        
        return mockupText.toString();
        
    }
  }

