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
color bg = #4B4747; //Dark gray
color important = #F27074; //Red
color plain = #6D7889; //Dark gray-blue
//color secondary = #99CCFF; //Pale blue
color secondary = #F3F4F2; //White

int minuteTensPlace;
int minuteOnesPlace;

void setup() {
  size(150, 550);
  surface.setResizable(true);
  surface.setTitle("Word Clock");
  background(bg);

  //Print names of all fonts on this machine to the console to help pick a new one
  //printArray(PFont.list());

  //Create the font
  fontSize = 25;
  font = createFont(fontName, fontSize);
  textFont(font);
}

void draw() {
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
    if ( x + textWidth(word) > width - (fontSize * 1.5)) {

      //Move x back to the left
      x = 0;

      //And move y down (start the next line)
      y += fontSize * 0.8;
    }

    minuteTensPlace = minute() / 10;
    minuteOnesPlace = minute() % 10;

    switch(hour()) {
    case 0 :
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
        if (word.equals("oh")) fill(important);
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
  if ( x + textWidth("and") > width - (fontSize * 1.5)) {
    //Move x back to the left
    x = 0;
    //And move y down to start the next line
    y += fontSize * 0.8;
  }
  text("and", x, y);
  x += textWidth("and");

  fill(important);
  int second = second();
  //If the word won't fit on the current line
  if ( x + textWidth(Integer.toString(second)) > width - (fontSize * 1.5)) {
    //Move x back to the left
    x = 0;
    //And move y down to start the next line
    y += fontSize * 0.8;
  }
  text(second, x, y);
  x += (textWidth(second+""));

  fill(secondary);
  //If the word won't fit on the current line
  if ( x + textWidth("seconds.") > width - (fontSize * 1.5)) {
    //Move x back to the left
    x = 0;
    //And move y down to start the next line
    y += fontSize * 0.8;
  }
  text("seconds.", x, y);
}

void keyPressed() {
  // ] (Increase font size)
  if (keyCode == 93) {
    fontSize = min(fontSize + fontStep, maxFontSize);
    font = createFont(fontName, fontSize);
    textFont(font);
  }
  // [ (Decrease font size)
  else if (keyCode == 91) {
    fontSize = max(fontSize - fontStep, minFontSize);
    font = createFont(fontName, fontSize);
    textFont(font);
  }
  else print(width, height);
}
