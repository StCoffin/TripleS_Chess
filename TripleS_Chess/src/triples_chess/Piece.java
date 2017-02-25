/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triples_chess;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Stcof
 */
public class Piece 
{

    public Piece(pieceR type, pColor colr) 
    {

        this.type = type;
        this.colr = colr;
        
        if (colr == pColor.W) 
        {
            pImg = "W";
        } 
        else if (colr == pColor.B) 
        {
            pImg = "B";
        }
        if (type == pieceR.PAWN) 
        {
            pImg = pImg + "pwn";
        } 
        else if (type == pieceR.ROOK) 
        {
            pImg = pImg + "Rok";
        } 
        else if (type == pieceR.KNIGHT) 
        {
            pImg = pImg + "Knt";
        } 
        else if (type == pieceR.BISHOP) 
        {
            pImg = pImg + "Bis";
        } 
        else if (type == pieceR.QUEEN) 
        {
            pImg = pImg + "Que";
        } 
        else if (type == pieceR.KING) 
        {
            pImg = pImg + "Ace";
        }

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

