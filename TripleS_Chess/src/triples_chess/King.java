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
public class King 
{
    public King(pColor color)
    {
        colr = color;
        type = pieceR.KING;
        switch(colr)
        {
            case W :
                pImg = "W" + "Ace";
                break;
            case B :
                pImg = "B" + "Ace";
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
    
    public pColor getColor()
    {
        return colr;
    }
    
    //Initialize varibales
    pieceR type;
    pColor colr;
    private String pImg;
    
}
