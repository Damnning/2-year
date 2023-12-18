package com.damning.Chess.Game.ChessBoard;

public class ChessBoard {
    Cell head;

    Cell getCell(final int x, final int y) {
        Cell cell = head;
        while (cell.y != y) {
            if (cell.down == null) return null;
            cell = cell.down;
        }
        while (cell.x != x) {
            if (cell.right == null) return null;
            cell = cell.right;
        }
        return cell;
    }
}
