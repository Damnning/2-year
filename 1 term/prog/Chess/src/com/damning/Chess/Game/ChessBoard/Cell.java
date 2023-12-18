package com.damning.Chess.Game.ChessBoard;

import com.damning.Chess.Game.Figure.Figure;

public class Cell {
    int x;

    int y;
    Figure figure;
    Cell up;
    Cell down;
    Cell left;
    Cell right;

    public Cell(int x, int y) {
        this(x, y, null);

    }

    public Cell(int x, int y, Figure figure) {
        this.x = x;
        this.y = y;
        this.figure = figure;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}
