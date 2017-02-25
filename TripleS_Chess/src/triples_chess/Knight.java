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
    public Knight(pColor color)
    {
        colr = color;
        type = pieceR.KNIGHT;
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
    
    public pColor getColor()
    {
        return colr;
    }
    
    //Initialize varibales
    pieceR type;
    pColor colr;
    private String pImg;
    
}
