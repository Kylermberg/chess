package chess;

import java.util.Collection;
import java.util.ArrayList;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private TeamColor currentTurn;
    private ChessBoard board;

    public ChessGame() {
        this.currentTurn = TeamColor.WHITE;
        this.board = new ChessBoard();
        this.board.resetBoard();
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return this.currentTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.currentTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece piece = board.getPiece(startPosition);
        if (piece == null) {
            return null;
        }
        return piece.pieceMoves(board, startPosition);
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPiece piece = board.getPiece(move.getStartPosition());
        if (piece == null) {
            throw new InvalidMoveException("No piece at start position");
        }
        Collection<ChessMove> validMoves = piece.pieceMoves(board, move.getStartPosition());

        if (!validMoves.contains(move)) {
            throw new InvalidMoveException("Invalid move");
        }

        board.addPiece(move.getEndPosition(), piece);
        board.addPiece(move.getStartPosition(), null); // Clear start position

        setTeamTurn(currentTurn == TeamColor.WHITE ? TeamColor.BLACK : TeamColor.WHITE);
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition kingPosition = findKingPosition(teamColor);
        if (kingPosition == null) {
            return false;
        }
        TeamColor opponent = (teamColor == TeamColor.WHITE) ? TeamColor.BLACK : TeamColor.WHITE;
        for (ChessPosition pos : getAllOpponentPieces(opponent)) {
            ChessPiece piece = board.getPiece(pos);
            if (piece != null && piece.pieceMoves(board, pos).contains(new ChessMove(pos, kingPosition, null))) {
                return true; // King is in check
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        if (!isInCheck(teamColor)) {
            return false;
        }

        for (ChessPosition pos : getAllTeamPieces(teamColor)) {
            ChessPiece piece = board.getPiece(pos);
            Collection<ChessMove> moves = piece.pieceMoves(board, pos);
            for (ChessMove move : moves) {
                ChessBoard tempBoard = new ChessBoard();
                tempBoard.setBoard(this.board);
                tempBoard.addPiece(move.getEndPosition(), piece);
                tempBoard.addPiece(move.getStartPosition(), null);

                ChessGame tempGame = new ChessGame();
                tempGame.setBoard(tempBoard);

                if (!tempGame.isInCheck(teamColor)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if (isInCheck(teamColor)) {
            return false;
        }

        for (ChessPosition pos : getAllTeamPieces(teamColor)) {
            ChessPiece piece = board.getPiece(pos);
            if (!piece.pieceMoves(board, pos).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board.setBoard(board);
    }

    public ChessBoard getBoard() {
        return this.board;
    }

    private ChessPosition findKingPosition(TeamColor teamColor) {
        for (ChessPosition pos : getAllTeamPieces(teamColor)) {
            ChessPiece piece = board.getPiece(pos);
            if (piece != null && piece.getPieceType() == ChessPiece.PieceType.KING) {
                return pos;
            }
        }
        return null;
    }

    /**
     * Helper method to get all positions of a team's pieces
     */
    private Collection<ChessPosition> getAllTeamPieces(TeamColor teamColor) {
        Collection<ChessPosition> teamPieces = new ArrayList<>();

        for (ChessPosition position : board.getAllPositions()) {
            ChessPiece piece = board.getPiece(position);
            if (piece != null && piece.getTeamColor() == teamColor) {
                teamPieces.add(position);
            }
        }

        return teamPieces;
    }

    /**
     * Helper method to get all positions of the opponent's pieces
     */
    private Collection<ChessPosition> getAllOpponentPieces(TeamColor teamColor) {
        Collection<ChessPosition> opponentPieces = new ArrayList<>();
        TeamColor opponentColor = (teamColor == TeamColor.WHITE) ? TeamColor.BLACK : TeamColor.WHITE;

        for (ChessPosition position : board.getAllPositions()) {
            ChessPiece piece = board.getPiece(position);
            if (piece != null && piece.getTeamColor() == opponentColor) {
                opponentPieces.add(position);
            }
        }

        return opponentPieces;
    }
}
