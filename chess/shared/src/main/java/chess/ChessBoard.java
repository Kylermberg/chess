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
        this.board.putAll(newBoard.board);
    }

    public void resetBoard() {
        board.clear();
        for (int i = 0; i < 8; i++) {
            addPiece(new ChessPosition(1,i), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
            addPiece(new ChessPosition(6,i), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        }
        addPiece(new ChessPosition(0,0), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(0,1), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(0,2), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(0,3), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPosition(0,4), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
        addPiece(new ChessPosition(0,5), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(0,6), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(0,7), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));

        addPiece(new ChessPosition(7,0), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(7,1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(7,2), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(7,3), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPosition(7,4), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
        addPiece(new ChessPosition(7,5), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(7,6), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(7,7), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
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

        return board.equals(otherBoard.board);
    }


    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
