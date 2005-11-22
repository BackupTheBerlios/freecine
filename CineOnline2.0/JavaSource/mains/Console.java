package mains;
/*
 *  File:  Console.java
 *
 *  Gary Cornell and Cay S. Horstmann, Core Java (Book/CD-ROM)
 *  Published By SunSoft Press/Prentice-Hall
 *  Copyright (C) 1996 Sun Microsystems Inc.
 *  All Rights Reserved. ISBN 0-13-565755-5
 *
 *  Permission to use, copy, modify, and distribute this 
 *  software and its documentation for NON-COMMERCIAL purposes
 *  and without fee is hereby granted provided that this 
 *  copyright notice appears in all copies. 
 *  
 *  THE AUTHORS AND PUBLISHER MAKE NO REPRESENTATIONS OR 
 *  WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER 
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
 *  IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 *  PARTICULAR PURPOSE, OR NON-INFRINGEMENT. THE AUTHORS
 *  AND PUBLISHER SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED 
 *  BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING 
 *  THIS SOFTWARE OR ITS DERIVATIVES.
 *
 *  Modifications by Tom Scavo <trscavo@npac.syr.edu> 10/6/97
 *                                                    11/2/97
 *
 */
 
/**
 *  Methods to read numbers and strings from standard input,
 *  and print prompts to standard output.
 *  @version 1.02, 2 Nov 1997
 *  @author Cay Horstmann, Tom Scavo
 */

public class Console {

   /*
    *  The Console class may not be instantiated.
    */
    
   private Console() { };

   /**
    *  Print a prompt to the console.  Do not print a newline.
    *  @param prompt the prompt string to display
    */
    
   public static void printPrompt( String prompt ) {
      System.out.print( prompt );
      System.out.flush();
   }
   
   /**
    *  Read a word from the console. A word is 
    *  any set of characters terminated by whitespace.
    *  @return the 'word' entered
    */
    
   public static String readWord() {
      int ch;
      String r = "";
      boolean done = false;
      while ( !done ) {
         try {
            ch = System.in.read();
            if ( ch < 0 || Character.isWhitespace( (char)ch ) )
               done = true;
            else
               r = r + (char)ch;
         } catch ( java.io.IOException e ) {
            done = true;
         }
      }
      return r;
   }

   /**
    *  Print a prompt and read a word from the console.
    *  A word is any set of characters terminated by whitespace.
    *  @param prompt the prompt string to display
    *  @return the 'word' entered
    */
    
   public static String readWord( String prompt ) {
      printPrompt( prompt );
      return readWord();
   }

   /**
    *  Read a string from the console. The string is 
    *  terminated by a newline.
    *  @return the input string (without the newline)
    */
    
   public static String readString() {
      int ch;
      String r = "";
      boolean done = false;
      while ( !done ) {
         try {
            ch = System.in.read();
            if ( ch < 0 || (char)ch == '\n' )
               done = true;
            else
               r = r + (char)ch;
         } catch ( java.io.IOException e ) {
            done = true;
         }
      }
      return r;
   }

   /**
    *  Print a prompt and read a string from the console.
    *  The string is terminated by a newline.
    *  @param prompt the prompt string to display
    *  @return the input string (without the newline)
    */
    
   public static String readString( String prompt ) {
      printPrompt( prompt );
      return readString();
   }

   /**
    *  Read an integer from the console.
    *  The input is terminated by a newline and validated.
    *  @return the input value as an int
    *  @exception NumberFormatException if non-integer input
    */
    
   public static int readInt() {
      String input;
      input = readString().trim();
      try {
         return Integer.valueOf(input).intValue();
      } catch ( NumberFormatException e ) {
         throw e;
      }
   }

   /**
    *  Print a prompt and read an integer from the console.
    *  The input is terminated by a newline and validated.
    *  If the input is invalid, an error message is printed
    *  on stdout and the user is prompted for a new value.
    *  @param prompt the prompt string to display
    *  @return the input value as an int
    */
    
   public static int readInt( String prompt ) {
      String input;
      while ( true ) {
         input = readString( prompt ).trim();
         try {
            return Integer.valueOf(input).intValue();
         } catch ( NumberFormatException e ) {
            System.out.println
               ("No és un enter: " + input + "\nIntrodueix de nou!");
         }
      }
   }

   /**
    *  Read a floating point number from the console.
    *  The input is terminated by a newline and validated.
    *  @return the input value as a double
    *  @exception NumberFormatException if non-float input
    */
    
   public static double readDouble() {
      String input;
      input = readString().trim();
      try {
         return Double.valueOf(input).doubleValue();
      } catch ( NumberFormatException e ) {
         throw e;
      }
   }

   /**
    *  Print a prompt and read a floating point number from
    *  the console.  The input is terminated by a newline
    *  and validated.
    *  If the input is invalid, an error message is printed
    *  on stdout and the user is prompted for a new value.
    *  @param prompt the prompt string to display
    *  @return the input value as a double
    */
    
   public static double readDouble( String prompt ) {
      String input;
      while ( true ) {
         input = readString( prompt ).trim();
         try {
            return Double.valueOf(input).doubleValue();
         } catch ( NumberFormatException e ) {
            System.out.println
               ("No és un número en punt flotant: " + input + "\nIntrodueix de nou!");
         }
      }
   }
   
} // end class Console
