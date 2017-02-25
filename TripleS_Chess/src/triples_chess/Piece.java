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
public class Piece 
{

    public Piece()
    {

    }
    
    public Piece(pieceR type, pColor colr) 
    {

        this.type = type;
        this.colr = colr;
        

    }

    public pieceR type() 
    {
        return type;
    }

    public pColor colr() 
    {
        return colr;
    }

    public String getImgID() 
    {
        return pImg;
    }

    public String toString() 
    {
        return colr + "" + type;
    }

    public String pImg;
    pieceR type;
    pColor colr;
}

