package edu.curtin.comp2003.rover;
public class Sensors
{
    /** Performs a temperature reading and returns the result in °C. */
    public double readTemperature() 
    {
        return 34.5;
    }

    /** Performs a visibility reading and returns the result in km. */
    public double readVisibility() 
    {
        return 3.5;
    }

    /** Performs a light-level reading, and returns the result in lux (units). */
    public double readLightLevel() 
    {
        return 34.5;
    }

    /** Takes a photo and returns the binary data making up the image. */
    public byte[] takePhoto() 
    {
        byte[] bArray = {1,2,3,4,5,6,7,8,9,10};
        return bArray;
    }
}
