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

    private final ChessGame.TeamColor pieceColor;
    private final ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
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
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece piece = board.getPiece(myPosition);
        switch(piece.getPieceType()) {
            case PieceType.BISHOP:
                return bishopMoves(board, myPosition, piece);
            case PieceType.KING:
                return kingMoves(board, myPosition, piece);
            case PieceType.KNIGHT:
                return knightMoves(board, myPosition, piece);
            case PieceType.PAWN:
                return pawnMoves(board, myPosition, piece);
            case PieceType.QUEEN:
                return queenMoves(board, myPosition, piece);
            case PieceType.ROOK:
                return rookMoves(board, myPosition, piece);
            default:
                return null;
        }
    }

    private static ArrayList<ChessMove> bishopMoves(ChessBoard board, ChessPosition startPosition, ChessPiece piece) {
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        // upper right
        for (int y = startPosition.getRow()+1, x = startPosition.getColumn()+1; y < 9 && x < 9; y++, x++) {
            ChessPosition endPosition = new ChessPosition(y, x);
            // if there is no piece at endPosition, valid.
            if (board.getPiece(endPosition) == null) {
                validMoves.add(new ChessMove(startPosition, endPosition, null));
            } else if (board.getPiece(endPosition).getTeamColor() != piece.getTeamColor()) {
                // if there is a piece and they are different colors, valid and break (capture)
                validMoves.add(new ChessMove(startPosition, endPosition, null));
                break;
            } else {
                // if there is a piece and they are the same colors, break.
                break;
            }
        }

        // lower right
        for (int y = startPosition.getRow()-1, x = startPosition.getColumn()+1; y > 0 && x < 9; y--, x++) {
            ChessPosition endPosition = new ChessPosition(y, x);
            // if there is no piece at endPosition, valid.
            if (board.getPiece(endPosition) == null) {
                validMoves.add(new ChessMove(startPosition, endPosition, null));
            } else if (board.getPiece(endPosition).getTeamColor() != piece.getTeamColor()) {
                // if there is a piece and they are different colors, valid and break (capture)
                validMoves.add(new ChessMove(startPosition, endPosition, null));
                break;
            } else {
                // if there is a piece and they are the same colors, break.
                break;
            }
        }

        // lower left
        for (int y = startPosition.getRow()-1, x = startPosition.getColumn()-1; y > 0 && x > 0; y--, x--) {
            ChessPosition endPosition = new ChessPosition(y, x);
            // if there is no piece at endPosition, valid.
            if (board.getPiece(endPosition) == null) {
                validMoves.add(new ChessMove(startPosition, endPosition, null));
            } else if (board.getPiece(endPosition).getTeamColor() != piece.getTeamColor()) {
                // if there is a piece and they are different colors, valid and break (capture)
                validMoves.add(new ChessMove(startPosition, endPosition, null));
                break;
            } else {
                // if there is a piece and they are the same colors, break.
                break;
            }
        }

        // upper left
        for (int y = startPosition.getRow()+1, x = startPosition.getColumn()-1; y < 9 && x > 0; y++, x--) {
            ChessPosition endPosition = new ChessPosition(y, x);
            // if there is no piece at endPosition, valid.
            if (board.getPiece(endPosition) == null) {
                validMoves.add(new ChessMove(startPosition, endPosition, null));
            } else if (board.getPiece(endPosition).getTeamColor() != piece.getTeamColor()) {
                // if there is a piece and they are different colors, valid and break (capture)
                validMoves.add(new ChessMove(startPosition, endPosition, null));
                break;
            } else {
                // if there is a piece and they are the same colors, break.
                break;
            }
        }

        return validMoves;
    }

    private static ArrayList<ChessMove> kingMoves(ChessBoard board, ChessPosition startPosition, ChessPiece piece) {
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        return validMoves;
    }

    private static ArrayList<ChessMove> knightMoves(ChessBoard board, ChessPosition startPosition, ChessPiece piece) {
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        return validMoves;
    }

    private static ArrayList<ChessMove> pawnMoves(ChessBoard board, ChessPosition startPosition, ChessPiece piece) {
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        return validMoves;
    }

    private static ArrayList<ChessMove> queenMoves(ChessBoard board, ChessPosition startPosition, ChessPiece piece) {
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        return validMoves;
    }

    private static ArrayList<ChessMove> rookMoves(ChessBoard board, ChessPosition startPosition, ChessPiece piece) {
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        // upper right
        for (int y = startPosition.getRow()+1, x = startPosition.getColumn()+1; y < 9 && x < 9; y++, x++) {
            ChessPosition endPosition = new ChessPosition(y, x);
            // if there is no piece at endPosition, valid.
            if (board.getPiece(endPosition) == null) {
                validMoves.add(new ChessMove(startPosition, endPosition, null));
            } else if (board.getPiece(endPosition).getTeamColor() != piece.getTeamColor()) {
                // if there is a piece and they are different colors, valid and break (capture)
                validMoves.add(new ChessMove(startPosition, endPosition, null));
                break;
            } else {
                // if there is a piece and they are the same colors, break.
                break;
            }
        }

        // lower right
        for (int y = startPosition.getRow()-1, x = startPosition.getColumn()+1; y > 0 && x < 9; y--, x++) {
            ChessPosition endPosition = new ChessPosition(y, x);
            // if there is no piece at endPosition, valid.
            if (board.getPiece(endPosition) == null) {
                validMoves.add(new ChessMove(startPosition, endPosition, null));
            } else if (board.getPiece(endPosition).getTeamColor() != piece.getTeamColor()) {
                // if there is a piece and they are different colors, valid and break (capture)
                validMoves.add(new ChessMove(startPosition, endPosition, null));
                break;
            } else {
                // if there is a piece and they are the same colors, break.
                break;
            }
        }

        // lower left
        for (int y = startPosition.getRow()-1, x = startPosition.getColumn()-1; y > 0 && x > 0; y--, x--) {
            ChessPosition endPosition = new ChessPosition(y, x);
            // if there is no piece at endPosition, valid.
            if (board.getPiece(endPosition) == null) {
                validMoves.add(new ChessMove(startPosition, endPosition, null));
            } else if (board.getPiece(endPosition).getTeamColor() != piece.getTeamColor()) {
                // if there is a piece and they are different colors, valid and break (capture)
                validMoves.add(new ChessMove(startPosition, endPosition, null));
                break;
            } else {
                // if there is a piece and they are the same colors, break.
                break;
            }
        }

        // upper left
        for (int y = startPosition.getRow()+1, x = startPosition.getColumn()-1; y < 9 && x > 0; y++, x--) {
            ChessPosition endPosition = new ChessPosition(y, x);
            // if there is no piece at endPosition, valid.
            if (board.getPiece(endPosition) == null) {
                validMoves.add(new ChessMove(startPosition, endPosition, null));
            } else if (board.getPiece(endPosition).getTeamColor() != piece.getTeamColor()) {
                // if there is a piece and they are different colors, valid and break (capture)
                validMoves.add(new ChessMove(startPosition, endPosition, null));
                break;
            } else {
                // if there is a piece and they are the same colors, break.
                break;
            }
        }

        return validMoves;
    }


    // Created by IntelliJ
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ChessPiece piece)) {
            return false;
        }
        return pieceColor == piece.pieceColor && type == piece.type;
    }

    // Created by IntelliJ
    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

    // Structure generated by IntelliJ
    @Override
    public String toString() {
        return "{" + pieceColor + "," + type + "}";
    }
}
