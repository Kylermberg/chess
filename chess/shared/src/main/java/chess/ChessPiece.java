package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor teamColor;
    private ChessPiece.PieceType pieceType;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.teamColor = pieceColor;
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
        return teamColor;
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
        int[][] directions = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {-1, 1}, {1, -1}, {-1, -1},
        };
        for (int[] direction : directions) {
            ChessPosition newPos = myPosition.translate(direction[0], direction[1]);
            if (board.isValidPosition(newPos) && board.isMoveLegal(myPosition, newPos)) {
                moves.add(new ChessMove(myPosition, newPos, null));
            }
        }
        return moves;
    }

    private Collection<ChessMove> queenMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        int[][] directions = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {-1, 1}, {1, -1}, {-1, -1},
        };
        for (int[] direction : directions) {
            ChessPosition newPos = myPosition.translate(direction[0], direction[1]);
            while (board.isValidPosition(newPos) && board.isMoveLegal(myPosition, newPos)) {
                moves.add(new ChessMove(myPosition, newPos, null));
                newPos = newPos.translate(direction[0], direction[1]);  // Move further in the same direction
            }
        }
        return moves;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChessPiece other = (ChessPiece) obj;
        return teamColor == other.teamColor && pieceType == other.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor, pieceType);
    }
}