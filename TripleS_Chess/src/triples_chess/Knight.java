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
public class Knight extends Piece
{
    public Knight(pC color)
    {
        colr = color;
        type = pT.KNIGHT;
        switch(colr)
        {
            case W :
                pImg = "W" + "Knt";
                break;
            case B :
                pImg = "B" + "Knt";
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
