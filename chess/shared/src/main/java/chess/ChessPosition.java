package chess;

import java.util.Objects;
/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {
    private int row;    // 0-based internally
    private int column; // 0-based internally

    public ChessPosition(int row, int col) {
        this.row = row - 1;    // Convert from 1-based input to 0-based internally
        this.column = col - 1; // Convert from 1-based input to 0-based internally
    }

    public int getRow() {
        return row + 1; // Convert back to 1-based indexing
    }

    public int getColumn() {
        return column + 1; // Convert back to 1-based indexing
    }

    // Add the translate method to allow movement of the chess pieces
    public ChessPosition translate(int rowOffset, int colOffset) {
        return new ChessPosition(this.getRow() + rowOffset, this.getColumn() + colOffset);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChessPosition that = (ChessPosition) obj;
        return row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return 31 * row + column;
    }
}

