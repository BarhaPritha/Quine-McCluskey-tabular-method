/**
 * 
 * @author Barha Meherun Pritha
 * ID - 18101232
 * 
 */

import java.io.*; 
import java.util.*; 

public class tabulation{
  public static void main (String [] args){
    
    System.out.println("How many implicants are there in maxterms of SOP form? (Must be less than 15)");
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int [] a = new int [n];
    String [] bin = new String [n];
    System.out.println("Enter the canomicals in SOP form in separate text boxes. (Must be less than 15)");
    Scanner sc1 = new Scanner(System.in);
    for(int i = 0; i<a.length; i++){
      a[i] = sc1.nextInt();
      bin[i] = Integer.toBinaryString(a[i]);
    }
    String prime = " ";
    String essential = " ";
    String [] p = new String [n];
    
    //Sorting
    int temp = 0;  
    for(int i=0; i < a.length; i++){  
      for(int j=1; j < (a.length -i); j++){  
        if(a[j-1] > a[j]){  
          temp = a[j-1];  
          a[j-1] = a[j];  
          a[j] = temp;  
        }  
      }  
    }  
    
    //Integer to Binary
    for(int i = 0; i<a.length; i++){
      bin[i] = Integer.toBinaryString(a[i]);
    }
    
    //Same bit length
    int stringLen = bin[bin.length-1].length();
    String zero = "0";
    for(int i = 0; i<a.length; i++){
      while(bin[i].length() < stringLen){   
        bin[i] = zero.concat(bin[i]); 
      }
    }
    
    //Printing the bit strings in ascending order
    System.out.println();
    for(int i = 0; i<a.length; i++){
      p[i] = bin[i];
      System.out.println(bin[i]);
    }
    
    //Printing the bit strings according to the number of 1s
    System.out.println();
    System.out.println();
    for(int i = 0; i<a.length; i++){
      if(bin[i].contains("1")==false){
        System.out.print(a[i] + " ");
        System.out.println(bin[i]);
      }
    }
    for(int j = 1; j<5; j++){
      System.out.println("--------------------");
      for(int i = 0; i<a.length; i++){
        if(bin[i].contains("1")==true){
          int count = countOccur(bin[i]);
          if(count == j){
            System.out.print(a[i] + " ");
            System.out.println(bin[i]);
          }
        }
      }
    }
    System.out.println();
    System.out.println();
    
    String [] e = new String [n];
    for(int i = 0; i<e.length; i++){
      e[i] = a[i] + "";
    }
  
   char v = '_';
   String [] bin1 = new String [n*2 -1];
   String [] a1 = new String [n*2 -1];
   String str1 = "";
   String str2 = "";
   
   for(int i=0; i < a.length-1; i++){  
     for(int j= i+1; j < a.length; j++){  
       int count1 = countOccur(bin[i]);
       int count2 = countOccur(bin[j]);
       if(count2 - count1 == 1){
         int counter = match(bin[i], bin[j]);
         if (counter == 1) {
           p[i] = null;
           p[j] = null;
           e[i] = null;
           e[j] = null;
           for (int k = 0; k < bin[i].length(); k++) {
             if (bin[i].charAt(k) != bin[j].charAt(k)) {
               str1 = bin[i].substring(0, k) + v + bin[i].substring(k + 1);
               str2 = bin[j].substring(0, k) + v + bin[j].substring(k + 1);
             }
           }
           for(int k = 0; k < bin1.length; k++){
             if(bin1[k]==null){
               bin1[k] = str1;
               a1[k] = " " + a[i] + ", " + a[j] + ",";
               break;
             }
           }
         }
       }
     }
   }
   
   //Printing the bit strings with 1 bit difference
   for(int i = 0; i<bin1.length; i++){
      if(bin1[i]!=null && bin1[i].contains("1")==false){
        System.out.print(a1[i]+ " ");
        System.out.println(bin1[i]);
      }
    }
   for(int j = 1; j<4; j++){
     System.out.println("-----------------------");
     for(int i = 0; i<bin1.length; i++){
       if(bin1[i]!=null && bin1[i].contains("1")==true){
         int count = countOccur(bin1[i]);
         if(count == j){
           System.out.print(a1[i] + " ");
           System.out.println(bin1[i]);
         }
       }
     }
   }
    System.out.println();
    System.out.println();
    
    String [] p1 = new String [n*2 -1];
    String [] e1 = new String [n*2 -1];
    for(int i = 0; i<p1.length; i++){
      p1[i] = bin1[i];
      e1[i] = a1[i];
    }
    
    String [] bin2 = new String [n];
    String [] a2 = new String [n];
    str1 = "";
    str2 = "";
    for(int i=0; i < bin1.length-1; i++){
      if(bin1[i]!=null){
        for(int j= i+1; j < bin1.length; j++){
          if(bin1[j]!=null){ 
            int counter = match(bin1[i], bin1[j]);
            if (counter == 1) {
              int count1 = countOccur(bin1[i]);
              int count2 = countOccur(bin1[j]);
              if(count2 - count1 == 1){
                String temp1 = "";
                String temp2 = "";
                p1[i] = null;
                p1[j] = null;
                e1[i] = null;
                e1[j] = null;
                for (int k = 0; k < bin1[i].length(); k++) {
                  if (bin1[i].charAt(k) != bin1[j].charAt(k)) {
                    str1 = bin1[i].substring(0, k) + v + bin1[i].substring(k + 1);
                    str2 = bin1[j].substring(0, k) + v + bin1[j].substring(k + 1);
                    temp1 = bin1[i];
                    temp2 = bin1[j];
                    bin1[i] = str1;
                    bin1[j] = str2;
                  }
                }
                bin1[i] = temp1;
                bin1[j] = temp2;
                for(int k = 0; k < bin2.length; k++){
                  if(bin2[k]==null){
                    a2[k] = a1[i] + a1[j];
                    bin2[k] = str1;
                    break;
                  }
                }
              }
            }
          }
        }
      }
    }
    
   //Printing the bit strings with 2 bits difference
   for(int i = 0; i<bin2.length; i++){
      if(bin2[i]!=null && bin2[i].contains("1")==false){
        System.out.print(a2[i] + " ");
        System.out.println(bin2[i]);
      }
    }
   for(int j = 1; j<3; j++){
    System.out.println("-----------------------");
    for(int i = 0; i<bin2.length; i++){
      if(bin2[i]!=null && bin2[i].contains("1")==true){
        int count = countOccur(bin2[i]);
        if(count == j){
          System.out.print(a2[i]+ " ");
          System.out.println(bin2[i]);
        }
      }
    }
   }
    System.out.println();
    System.out.println();
    
    //Prime Implicants
    int c = 65;
    for(int i = 0; i<p.length; i++){
      if(p[i] != null){
        if(prime != " "){
          prime = prime.concat(" + ");
        }
        int c2 = 0;
        for(int j=0; j<p[i].length(); j++){
          if(c + c2 ==91){
            break;
          }
          if(p[i].charAt(j) != '_'){
            prime = prime.concat(new Character((char) (c + c2)).toString());
            if(p[i].charAt(j)=='0'){
              prime = prime.concat("'");
            }
          }
        c2++;
        }
      }
    }
    
    for(int i = 0; i<p1.length; i++){
      if(p1[i] != null){
        if(prime != " "){
          prime = prime.concat(" + ");
        }
        int c2 = 0;
        for(int j=0; j<p1[i].length(); j++){
          if(c + c2 ==91){
            break;
          }
           if(p1[i].charAt(j) != '_'){
            prime = prime.concat(new Character((char) (c + c2)).toString());
            if(p1[i].charAt(j)=='0'){
              prime = prime.concat("'");
            }
          }
        c2++;
        }
      }
    }

    for(int i = 0; i<bin2.length; i++){
      if(i>0){
        for(int j =0; j<i; j++){
          if(bin2[i]!=null && bin2[j]!=null){
            if(bin2[i].equals(bin2[j])){
              if(i<bin2.length-1){
                i++;
              }
              break;
            }
          }
        }
      }
        if(bin2[i]!= null){
          if(prime != " "){
            prime = prime.concat(" + ");
          }
          int c2 = 0;
          for(int j=0; j<bin2[i].length(); j++){
            if(c + c2 ==91){
              break;
            }
            if(bin2[i].charAt(j) != '_'){
              String ascii = new Character((char) (c + c2)).toString();
              prime = prime.concat(ascii);
              if(bin2[i].charAt(j)=='0'){
                prime = prime.concat("'");
              }
            }
            c2++;
          }
        }
      }
    System.out.println("Prime Implicants:" + prime);
    
    //Finding the number of occurences of Associated Minterms
    int [] pi = new int [16];
    for(int i = 0; i<pi.length; i++){
      int occur = 0;
      int occur2 = 0;
      for(int j = 0; j<e.length; j++){
        if(e[j]!=null && e[j].contains("" + i + ",")==true){
          occur++;
        }
      }
      for(int j = 0; j<e1.length; j++){
        if(e1[j]!=null && e1[j].contains(" " + i + ",")==true){
          occur++;
        }
      }
      for(int j = 0; j<a2.length; j++){
        if(a2[j]!=null && a2[j].contains(" " + i + ",")==true){
          occur2++;
        }
      }
      pi[i] = occur + (occur2 / 2);
    }
    
    String [] minterm = new String[n];
    for(int i =0; i<pi.length; i++){
      if(pi[i]==1){
        for(int j = 0; j<n; j++){
          if(minterm[j] == null){
            minterm[j] = i+"";
            break;
          }
        }
      }
    }

    //Essential Prime Implicants
    String [] min = new String [n];
    for(int i = 0; i<minterm.length; i++){
      if(minterm[i] != null){
        for(int j = 0; j<p.length; j++){
          if(p[j]!=null && minterm[i].contains(a[j]+"")==true){
            for(int k = 0; k<min.length; k++){
              if(min[k] == null){
                min[k] = p[j];
                break;
              }
            }
          }
        }
        for(int j = 0; j<p1.length; j++){
          if(p1[j]!=null && a1[j].contains(minterm[i])==true){
            for(int k = 0; k<min.length; k++){
              if(min[k] == null){
                min[k] = p1[j];
                break;
              }
            }
          }
        }
        for(int j = 0; j<bin2.length; j++){
          if(bin2[j]!=null && a2[j].contains(minterm[i])==true){
            for(int k = 0; k<min.length; k++){
              if(min[k] == bin2[j]){
                break;
              }
              if(min[k] == null){
                min[k] = bin2[j];
                break;
              }
            }
          }
        }
      }
    }
               
    for(int i = 0; i<min.length; i++){
      if(i>0){
        for(int j =0; j<i; j++){
          if(min[i]!=null && min[j]!=null){
            if(min[i].equals(min[j])){
              if(i<min.length-1){
                i++;
              }
              break;
            }
          }
        }
      }
      if(min[i] != null){ 
        if(essential != " "){
          essential  = essential.concat(" + ");
        }
        int c2 = 0;
        if(c + c2 ==91){
          break;
        }
        for(int j=0; j<min[i].length(); j++){
          if(min[i].charAt(j) != '_'){
            String ascii = new Character((char) (c + c2)).toString();
            essential = essential.concat(ascii);
            if(min[i].charAt(j)=='0'){
              essential = essential.concat("'");
            }
          }
         c2++;
        }
      }
    }
    
    System.out.println("Essential Prime Implicants:" + essential);
    
    
  }
  
   public static int match(String str1, String str2){
     int counter=0;
     for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                counter++;
            }
        }
     return counter;
    }
   
   private static int countOccur(String s){
     int count = 0;
     for(int i = 0; i<s.length(); i++){
       if(s.charAt(i) == '1'){
         count++;
       }
     }
     return count;
   }
   
}
    
