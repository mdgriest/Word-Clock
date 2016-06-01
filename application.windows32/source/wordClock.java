import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class wordClock extends PApplet {

/*
   Mitchell Griest
 Word Clock
 Summer 2016
 
 mdgriest@crimson.ua.edu
 */

PFont font;
int fontSize;
int maxFontSize = 96; //Feel free to increase this for higher resolution monitors
int minFontSize = 10;
int fontStep = 1;
String fontName = "Helvetica-Light"; //Change this to use a different typeface

//Change these hex values to use a different color scheme of your choosing
int red = 0xffF27074;
int darkRed = 0xff8E2800;
int clay = 0xffB64926;
int yellow = 0xffFFA500;
int paleYellow = 0xffFFFF9D;
int orange = 0xffFF6138;
int green = 0xff468966;
int limeGreen = 0xffBDF271;
int lightGreen = 0xffBEEB9F;
int middleGreen = 0xff79BD8F;
int blueGreen = 0xff00A388;
int blue = 0xff1E90FF;
int dullBlue = 0xff348899;
int aqua = 0xff29D9C2;
int lightBlue = 0xff99CCFF;
int darkGrayBlue = 0xff6D7889;
int lightGray = 0xffD4D4D4;
int middleGray = 0xffAAAAAA;
int darkGray = 0xff4B4747;
int white = 0xffF3F4F2;

int bg;
int important;
int plain;
int secondary;

int minuteTensPlace;
int minuteOnesPlace;

public void setup() {
  
  surface.setResizable(true);
  surface.setTitle("Word Clock");
  bg = darkGray;
  important = aqua;
  plain = darkGrayBlue;
  secondary = limeGreen;
  background(bg);

  //Print names of all fonts on this machine to the console to help pick a new one
  //printArray(PFont.list());

  //Create the font
  fontSize = 25;
  font = createFont(fontName, fontSize);
  textFont(font);
}

public void draw() {
  background(bg);

  String[]allWords = {
    "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", 
    "o'clock", "oh", 
    "ten", "twenty", "thirty", "forty", "fifty", 
    "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", 
    "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", 
  };

  //Set initial x and y position for drawing the words
  int x = 0;
  int y = fontSize;

  //Text color is initially plain
  fill(plain);

  //For each word
  for (int i = 0; i < allWords.length; i++) {

    //Determine which word we are about to draw
    String word = allWords[i];

    //If the word won't fit on the current line
    if ( x + textWidth(word) > width - (fontSize * 1.5f)) {

      //Move x back to the left
      x = 0;

      //And move y down (start the next line)
      y += fontSize * 0.8f;
    }

    minuteTensPlace = minute() / 10;
    minuteOnesPlace = minute() % 10;

    switch(hour()) {
    case 0 :
    case 12:
      if (word.equals("Twelve")) fill(important);
      break;
    case 1 :
    case 13:
      if (word.equals("One")) fill(important);
      break;
    case 2 :
    case 14:
      if (word.equals("Two")) fill(important);
      break;
    case 3 :
    case 15:
      if (word.equals("Three")) fill(important);
      break;
    case 4 :
    case 16:
      if (word.equals("Four")) fill(important);
      break;
    case 5 :
    case 17:
      if (word.equals("Five")) fill(important);
      break;
    case 6 :
    case 18:
      if (word.equals("Six")) fill(important);
      break;
    case 7 :
    case 19:
      if (word.equals("Seven")) fill(important);
      break;
    case 8 :
    case 20:
      if (word.equals("Eight")) fill(important);
      break;
    case 9 :
    case 21:
      if (word.equals("Nine")) fill(important);
      break;
    case 10 :
    case 22:
      if (word.equals("Ten")) fill(important);
      break;
    case 11 :
    case 23:
      if (word.equals("Eleven")) fill(important);
      break;
    }

    if ( minute() == 0 && word.equals("o'clock")) fill(important);
    else {
      switch(minuteTensPlace) {
      case 0:
        if (word.equals("oh") && minute() != 0) fill(important);
        break;
      case 1:
        switch(minute()) {
        case 10:
          if (word.equals("ten")) fill(important);
          break;
        case 11:
          if (word.equals("eleven")) fill(important);
          break;
        case 12:
          if (word.equals("twelve")) fill(important);
          break;
        case 13:
          if (word.equals("thirteen")) fill(important);
          break;
        case 14:
          if (word.equals("fourteen")) fill(important);
          break;
        case 15:
          if (word.equals("fifteen")) fill(important);
          break;
        case 16:
          if (word.equals("sixteen")) fill(important);
          break;
        case 17:
          if (word.equals("seventeen")) fill(important);
          break;
        case 18:
          if (word.equals("eighteen")) fill(important);
          break;
        case 19:
          if (word.equals("nineteen")) fill(important);
          break;
        }
        break;
      case 2:
        if (word.equals("twenty")) fill(important);
        break;
      case 3:
        if (word.equals("thirty")) fill(important);
        break;
      case 4:
        if (word.equals("forty")) fill(important);
        break;
      case 5:
        if (word.equals("fifty")) fill(important);
        break;
      }

      if (minuteTensPlace != 1) {
        switch(minuteOnesPlace) {
        case 1:
          if (word.equals("one")) fill(important);
          break;
        case 2:
          if (word.equals("two")) fill(important);
          break;
        case 3:
          if (word.equals("three")) fill(important);
          break;
        case 4:
          if (word.equals("four")) fill(important);
          break;
        case 5:
          if (word.equals("five")) fill(important);
          break;
        case 6:
          if (word.equals("six")) fill(important);
          break;
        case 7:
          if (word.equals("seven")) fill(important);
          break;
        case 8:
          if (word.equals("eight")) fill(important);
          break;
        case 9:
          if (word.equals("nine")) fill(important);
          break;
        }
      }
    }

    //Draw the word
    text(word, x, y);

    //Set the text color back to plain
    fill(plain);

    //Move x to the right for the next word
    x += textWidth(word);
  }


  //A decidedly inelegant way of adding the "and XX seconds" information
  fill(secondary);
  //If the word won't fit on the current line
  if ( x + textWidth("and") > width - (fontSize * 1.5f)) {
    //Move x back to the left
    x = 0;
    //And move y down to start the next line
    y += fontSize * 0.8f;
  }
  text("and", x, y);
  x += textWidth("and");

  fill(important);
  int second = second();
  //If the word won't fit on the current line
  if ( x + textWidth(Integer.toString(second)) > width - (fontSize * 1.5f)) {
    //Move x back to the left
    x = 0;
    //And move y down to start the next line
    y += fontSize * 0.8f;
  }
  text(second, x, y);
  x += (textWidth(second+""));

  fill(secondary);
  //If the word won't fit on the current line
  if ( x + textWidth("seconds.") > width - (fontSize * 1.5f)) {
    //Move x back to the left
    x = 0;
    //And move y down to start the next line
    y += fontSize * 0.8f;
  }
  text("seconds.", x, y);
}

public void keyPressed() {
  // Increase font size
  if (keyCode == ']') {
    fontSize = min(fontSize + fontStep, maxFontSize);
    font = createFont(fontName, fontSize);
    textFont(font);
  }
  // Decrease font size)
  else if (key == '[' ) {
    fontSize = max(fontSize - fontStep, minFontSize);
    font = createFont(fontName, fontSize);
    textFont(font);
  }
  //Color modes
  else if ( key == '1' ) {
    bg = white;
    important = darkGray;
    plain = lightGray;
    secondary = middleGray;
  } else if ( key == '2' ) {
    bg = green;
    important = limeGreen;
    plain = blueGreen;
    secondary = paleYellow;
  } else if ( key == '3' ) {
    bg = darkRed;
    important = white;
    plain = red;
    secondary = lightGray;
  } else if ( key == '4' ) {
    bg = darkGray;
    important = red;
    plain = darkGrayBlue;
    secondary = white;
  } else if ( key == '5' ) {
    bg = blue;
    important = white;
    plain = middleGray;
    secondary = darkGray;
  } else if ( key == '0' ) {
    bg = darkGray;
    important = aqua;
    plain = darkGrayBlue;
    secondary = limeGreen;
  }
}

  public void settings() {  size(150, 550); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "wordClock" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
