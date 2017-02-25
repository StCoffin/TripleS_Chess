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
public class Queen 
{
    public Queen(pColor color)
    {
        colr = color;
        type = pieceR.QUEEN;
        switch(colr)
        {
            case W :
                pImg = "W" + "Que";
                break;
            case B :
                pImg = "B" + "Que";
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
