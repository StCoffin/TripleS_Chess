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
public class Pawn extends Piece
{
    public Pawn(pColor color)
    {
        colr = color;
        type = pieceR.PAWN;
        switch(colr)
        {
            case W :
                pImg = "W" + "Pwn";
                System.out.println("Initialized new pawn: White!");
                break;
            case B :
                pImg = "B" + "Pwn";
                System.out.println("Initialized new pawn: Black!");
                break;
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
