package edu.curtin.comp2003.rover;
public class SoilAnalyser
{
    /**
     * Begins a soil analysis. The soil analysis will complete some time later, 
     * and its result can be retrieved by calling pollAnalysis().
     *
     * If startAnalysis() is called while analysis is already underway, it will 
     * throw an exception.
     */
    public void startAnalysis() 
    {
        System.out.println("Analysing soil");
    }

    /**
     * Retrieves the results of a soil analysis, if they're ready yet. If no new 
     * results have been produced, this method returns null.
     */
    public byte[] pollAnalysis() 
    {
        byte[] bArray = {1,2,3,4,5,6,7,8,9,10};
        return bArray;
    }
}
