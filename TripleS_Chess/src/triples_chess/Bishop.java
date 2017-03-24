/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triples_chess;

/**
 *
 * @author Stcof
 */
public class Bishop extends Piece
{
    public Bishop(pC color)
    {
        colr = color;
        type = pT.BISHOP;
        switch(colr)
        {
            case W :
                pImg = "W" + "Bis";
                break;
            case B :
                pImg = "B" + "Bis";
        }

    }
    
    
    
    /**
    public  String canMove()
    {
        // Do something here...
    }
    **/
    
    public String getImgID() 
    {
        return pImg;
    }
    
    public pC getColor()
    {
        return colr;
    }
    
    //Initialize varibales
    pT type;
    pC colr;
    private String pImg;
    
}
