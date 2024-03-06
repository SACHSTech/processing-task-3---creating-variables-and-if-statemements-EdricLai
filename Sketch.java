import processing.core.PApplet;

public class Sketch extends PApplet {
  /**
   * called once
   * global variables
  */
    // screen
    int intWidth = 700;
    int intHeight = 500;
    float fltWidth = intWidth;
    float fltHeight = intHeight;

    // real time
    int intSecond = second();
    int intMinute = minute();
    int intHour = hour();

    // timers
    float fltTimer = 0f;
    int intFps = 0;

    // rgb
    int intBgR = 225;
    int intBgG = 250;
    int intBgB = 255;

    // sun
    float fltSunDiameter = fltWidth / 5f;
    float fltSunPosX = 0f - fltSunDiameter * 999f;
    float fltSunPosY = fltHeight / 3f;

    // moon
    float fltMoonDiameter = fltWidth / 6f;
    float fltMoonPosX = 0f - fltMoonDiameter;
    float fltMoonPosY = fltHeight / 3f;

    // star
    float fltStarDiameter = 0f;
    float fltStarPosX = 0f;
    float fltStarPosY = 0f;

    // door
    float fltDoorWidth = fltWidth / 18f;
    float fltDoorHeight = fltHeight / 7f;
    float fltDoorXCoords = fltWidth / 2f - fltDoorWidth / 2f;
    float fltDoorYCoords = fltHeight - fltDoorHeight;

    // windows
    float fltWindowDiameter = fltWidth / 15f;
    float fltWindow1XCoords = fltWidth / 2f + fltWidth / 10f;
    float fltWindow2XCoords = fltWidth / 2f - fltWidth / 10f;
    float fltWindowYCoords = fltHeight / 1.2f;

  /**
   * called once
   * initial settings
   */
  public void settings() {
    // screen size
    size(intWidth, intHeight);
  }

  /**
   * called repeatedly
   * looped program
   */
  public void draw()
  {
    // settings
    clear();
    noStroke();
    frameRate(200);
    background(intBgR, intBgG, intBgB);

    // updates real time
    intSecond = second();
    intMinute = minute();
    intHour = hour();

    // timers
    fltTimer += 0.005f;
    intFps += 1;
    if (fltTimer >= 12f) {fltTimer = 0f;}
    if (intFps >= 10) {intFps = 0;}

    // 20 fps
    if (intFps == 0) {
      // daylight cycle
      if (fltTimer >= 0f && fltTimer <= 2f) {
        // resets moon position
        fltMoonPosX = 0 - fltMoonDiameter;
        fltMoonPosY = fltHeight / 3f;
        // turns dark
        intBgR -= 5;
        intBgG -= 6;
        intBgB -= 4;
      }
      else if (fltTimer >= 6f && fltTimer <= 8f) {
        // resets sun position
        fltSunPosX = 0 - fltSunDiameter;
        fltSunPosY = fltHeight / 3f;
        // turns bright
        intBgR += 5;
        intBgG += 6;
        intBgB += 4;
      }

      // sun and moon movement
      fltSunPosX += fltWidth / 70;
      fltMoonPosX += fltWidth / 70;
      fltSunPosY += Math.pow(fltSunPosX, 2) / 50000f;
      fltMoonPosY += Math.pow(fltMoonPosX, 2) / 50000f;

      // star movement
      if (fltTimer >= 2 && fltTimer <= 6) {
        // star random position
        fltStarDiameter = random(fltWidth / 200f, fltWidth / 100f);
        fltStarPosX = random(0, fltWidth);
        fltStarPosY = random(0, fltHeight);
      }
      else {
        // star rest position
        fltStarPosX = 0 - fltStarDiameter;
        fltStarPosY = 0 - fltStarDiameter;
      }
    }

    // draws star
    fill (50, 100, 150);
    ellipse(fltStarPosX, fltStarPosY, fltStarDiameter, fltStarDiameter);

    // draws sun
    fill (250, 180, 20);
    ellipse(fltSunPosX, fltSunPosY, fltSunDiameter, fltSunDiameter);

    // draws moon
    fill (220, 220, 220);
    ellipse(fltMoonPosX, fltMoonPosY, fltMoonDiameter, fltMoonDiameter);

    // draws landscape
    fill (intBgR - 200, intBgG - 100, intBgB - 200);
    ellipse(fltWidth / 2f, fltHeight / 0.75f, fltWidth * 1.5f, fltHeight);

    // draws wood
    fill(intBgR - 115, intBgG - 190, intBgB - 195);
    rect(fltWidth / 3.00f, fltHeight / 1.50f, fltWidth / 3.00f, fltHeight / 3.00f);
    rect(fltWidth / 1.75f, fltHeight / 2.25f, fltWidth / 15.00f, fltHeight / 5.00f);
    triangle(fltWidth / 3.00f, fltHeight / 1.50f, fltWidth / 2.00f, fltHeight / 2.50f, fltWidth / 1.50f, fltHeight / 1.50f);

    // draws wood details
    fill(intBgR - 45, intBgG - 140, intBgB - 175);
    rect(fltWidth / 3.00f, fltHeight / 1.05f, fltWidth / 3.00f, fltHeight / 21.00f);
    rect(fltWidth / 3.00f, fltHeight / 1.50f, fltWidth / 3.00f, fltHeight / 21.00f);

    // draws windows
    if (intSecond == 20 || intSecond == 40) {
      fill(200, 200, 0);
    }
    else if (intSecond != 20 || intSecond != 40) {
      fill(intBgR - 135, intBgG - 210, intBgB - 215);
    }
    ellipse(fltWindow1XCoords, fltWindowYCoords, fltWindowDiameter, fltWindowDiameter);
    ellipse(fltWindow2XCoords, fltWindowYCoords, fltWindowDiameter, fltWindowDiameter);

    // draws door
    fill(intBgR - 135, intBgG - 210, intBgB - 215);
    rect(fltDoorXCoords, fltDoorYCoords, fltDoorWidth, fltDoorHeight);

    // displays timers
    fill(-intBgR + 200, -intBgG + 200, -intBgB + 200);
    textSize(fltWidth * fltHeight / 10000f);
    text(fltTimer, fltWidth / 16f, fltHeight / 8f);
    text(intSecond, fltWidth / 1.1f, fltHeight / 1.1f);
    text(intMinute, fltWidth / 1.25f, fltHeight / 1.1f);
    text(intHour, fltWidth / 1.4f, fltHeight / 1.1f);
  }
}