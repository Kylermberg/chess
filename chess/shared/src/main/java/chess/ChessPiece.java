package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType pieceType;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.pieceType = type;
    }
    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.pieceType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();

        switch (pieceType) {
            case KING:
                moves = kingMoves(board, myPosition);
                break;
            case QUEEN:
                moves = queenMoves(board, myPosition);
                break;
        }
        return moves;
    }
    private Collection<ChessMove> kingMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        int [][] direction = {
                {1,0},{-1,0},{0,1},{0,-1},
                {1,1},{-1,1},{1,-1},{-1,-1},
        };
        for (int[] row : direction) {
            ChessPosition newPos = myPosition.translate(direction[0], direction[1]);
            if (board.isValidPosition(newPos) && board.isMoveLegal(myPosition, newPos)) {
                moves.add(new ChessMove(myPosition, newPos, null));
            }
        }
        return moves;
    }

    private Collection<ChessMove> queenMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves = new ArrayList<>();
        int [][] direction = {
                {1,0},{-1,0},{0,1},{0,-1},
                {1,1},{-1,1},{1,-1},{-1,-1},
        };
        for (int[] direction : directions) {
            ChessPosition newPos = myPosition.translate(direction[0], direction[1]);  // Move one step in the direction
            while (board.isValidPosition(newPos) && board.isMoveLegal(myPosition, newPos)) {
                moves.add(new ChessMove(myPosition, newPos, null));  // Add valid move
                newPos = newPos.translate(direction[0], direction[1]);  // Move further in the same direction
            }
        }

        return moves;
    }
}