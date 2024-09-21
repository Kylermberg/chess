package chess;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private Map<ChessPosition, ChessPiece> board;

    public ChessBoard() {
        this.board = new HashMap<>();
    }


    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */

    /**
     * Returns all positions on the board where there are pieces.
     * @return Collection of all positions containing pieces.
     */
    public Collection<ChessPosition> getAllPositions() {
        return board.keySet();
    }

    public void addPiece(ChessPosition position, ChessPiece piece) {
        board.put(position, piece);
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return board.getOrDefault(position, null);
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void setBoard(ChessBoard newBoard) {
        this.board.clear();
        for (Map.Entry<ChessPosition, ChessPiece> entry : newBoard.board.entrySet()) {
            ChessPosition copiedPosition = new ChessPosition(entry.getKey().getRow(), entry.getKey().getColumn());
            ChessPiece copiedPiece = new ChessPiece(entry.getValue().getTeamColor(), entry.getValue().getPieceType());
            this.board.put(copiedPosition, copiedPiece);
        }
    }

    @Override
    public String toString() {
        return "ChessBoard{" +
                "board=" + board +
                '}';
    }

    public void resetBoard() {
        board.clear();

        // Add pawns
        for (int i = 1; i <= 8; i++) {
            addPiece(new ChessPosition(2, i), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
            addPiece(new ChessPosition(7, i), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        }

        // Add black major pieces
        addPiece(new ChessPosition(1, 1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(1, 2), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(1, 3), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(1, 4), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPosition(1, 5), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
        addPiece(new ChessPosition(1, 6), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(1, 7), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(1, 8), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));

        // Add white major pieces
        addPiece(new ChessPosition(8, 1), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(8, 2), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(8, 3), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(8, 4), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPosition(8, 5), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
        addPiece(new ChessPosition(8, 6), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(8, 7), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(8, 8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
    }



    public boolean isValidPosition(ChessPosition position) {
        return position.getRow() >= 0 && position.getRow() < 8 &&
                position.getColumn() >= 0 && position.getColumn() < 8;
    }
    public boolean isMoveLegal(ChessPosition start, ChessPosition end) {
        ChessPiece startPiece = getPiece(start);
        ChessPiece endPiece = getPiece(end);

        if (endPiece != null && endPiece.getTeamColor() == startPiece.getTeamColor()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ChessBoard otherBoard = (ChessBoard) obj;

        if (this.board.size() != otherBoard.board.size()) {
            return false;
        }

        for (Map.Entry<ChessPosition, ChessPiece> entry : this.board.entrySet()) {
            ChessPiece thisPiece = entry.getValue();
            ChessPiece otherPiece = otherBoard.getPiece(entry.getKey());

            if (!thisPiece.equals(otherPiece)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
