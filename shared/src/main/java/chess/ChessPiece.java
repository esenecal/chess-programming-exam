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

        int y = startPosition.getRow();
        int x = startPosition.getColumn();

        ChessPosition[] possiblePositions = new ChessPosition[8];       // array of all possible end positions
        possiblePositions[0] = new ChessPosition(y+1, x);     // up
        possiblePositions[1] = new ChessPosition(y+1, x+1);     // up right
        possiblePositions[2] = new ChessPosition(y, x+1);     // right
        possiblePositions[3] = new ChessPosition(y-1, x+1);     // down right
        possiblePositions[4] = new ChessPosition(y-1, x);     // down
        possiblePositions[5] = new ChessPosition(y-1, x-1);     // down left
        possiblePositions[6] = new ChessPosition(y, x-1);     // left
        possiblePositions[7] = new ChessPosition(y+1, x-1);     // up left

        for (ChessPosition endPosition : possiblePositions) {

            // check if in bounds
            if (endPosition.getRow() < 1 || endPosition.getRow() > 8 || endPosition.getColumn() < 1 || endPosition.getColumn() > 8) {
                continue;
            }

            if (board.getPiece(endPosition) == null) {
                validMoves.add(new ChessMove(startPosition, endPosition, null));
            } else if (board.getPiece(endPosition).getTeamColor() != piece.getTeamColor()) {
                // if there is a piece and they are different colors, valid and break (capture)
                validMoves.add(new ChessMove(startPosition, endPosition, null));
                // continue
            }
            // if neither of these are true, go on.
        }

        return validMoves;
    }

    private static ArrayList<ChessMove> knightMoves(ChessBoard board, ChessPosition startPosition, ChessPiece piece) {
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        // logic is the same as kingMoves, with different possible positions.
        int y = startPosition.getRow();
        int x = startPosition.getColumn();

        ChessPosition[] possiblePositions = new ChessPosition[8];       // array of all possible end positions
        possiblePositions[0] = new ChessPosition(y+2, x-1);     // 2up 1left
        possiblePositions[1] = new ChessPosition(y+2, x+1);     // 2up 1right
        possiblePositions[2] = new ChessPosition(y+1, x+2);     // 1up 2right
        possiblePositions[3] = new ChessPosition(y-1, x+2);     // 1down 2right
        possiblePositions[4] = new ChessPosition(y-2, x-1);     // 2down 1left
        possiblePositions[5] = new ChessPosition(y-2, x+1);     // 2down 1right
        possiblePositions[6] = new ChessPosition(y+1, x-2);     // 1up 2left
        possiblePositions[7] = new ChessPosition(y-1, x-2);     // 1down 2left

        for (ChessPosition endPosition : possiblePositions) {

            // check if in bounds
            if (endPosition.getRow() < 1 || endPosition.getRow() > 8 || endPosition.getColumn() < 1 || endPosition.getColumn() > 8) {
                continue;
            }

            if (board.getPiece(endPosition) == null) {
                validMoves.add(new ChessMove(startPosition, endPosition, null));
            } else if (board.getPiece(endPosition).getTeamColor() != piece.getTeamColor()) {
                // if there is a piece and they are different colors, valid and break (capture)
                validMoves.add(new ChessMove(startPosition, endPosition, null));
                // continue
            }
            // if neither of these are true, go on.
        }

        return validMoves;
    }

    private static ArrayList<ChessMove> pawnMoves(ChessBoard board, ChessPosition startPosition, ChessPiece piece) {
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        int y = startPosition.getRow();
        int x = startPosition.getColumn();

        ChessPiece.PieceType[] promotionPieces = {
                PieceType.ROOK,
                PieceType.BISHOP,
                PieceType.KNIGHT,
                PieceType.QUEEN
        };

        // for white pieces
        if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
            ChessPosition forwardOne = new ChessPosition(y+1, x);   // move forward one
            ChessPosition forwardTwo = new ChessPosition(y+2, x);   // move forward two
            ChessPosition[] captureMoves = new ChessPosition[2];        // possible capture moves.
            captureMoves[0] = new ChessPosition(y+1, x+1);        // capture right
            captureMoves[1] = new ChessPosition(y+1, x-1);        // capture left

            // if on home row, check to move forward to IF forwardOne AND forwardTwo are clear
            if (y == 2 && board.getPiece(forwardOne) == null && board.getPiece(forwardTwo) == null) {
                validMoves.add(new ChessMove(startPosition, forwardTwo, null));
            }

            // forwardOne. if at opposite end, promote.
            if (board.getPiece(forwardOne) == null && forwardOne.getRow() < 9) {       // space empty and within bounds
                if (forwardOne.getRow() == 8) {
                    for (ChessPiece.PieceType promotion : promotionPieces) {
                        validMoves.add(new ChessMove(startPosition, forwardOne, promotion));
                    }
                } else {
                    validMoves.add(new ChessMove(startPosition, forwardOne, null));
                }
            }

            // captureMoves
            for (ChessPosition endPosition : captureMoves) {
                // check bounds
                if (endPosition.getRow() > 8 || endPosition.getColumn() < 1 || endPosition.getColumn() > 8) {
                    continue;
                }
                // if this spot is NOT empty and has a piece of the opposite color, valid.
                if (board.getPiece(endPosition) != null && board.getPiece(endPosition).getTeamColor() != piece.getTeamColor()) {
                    if (endPosition.getRow() == 8) {
                        for (ChessPiece.PieceType promotion : promotionPieces) {
                            validMoves.add(new ChessMove(startPosition, endPosition, promotion));
                        }
                    } else {
                        validMoves.add(new ChessMove(startPosition, endPosition, null));
                    }
                }
            }

        } else {        // for black pieces

            ChessPosition forwardOne = new ChessPosition(y-1, x);   // move forward one
            ChessPosition forwardTwo = new ChessPosition(y-2, x);   // move forward two
            ChessPosition[] captureMoves = new ChessPosition[2];        // possible capture moves.
            captureMoves[0] = new ChessPosition(y-1, x+1);        // capture right
            captureMoves[1] = new ChessPosition(y-1, x-1);        // capture left

            // if on home row, check to move forward to IF forwardOne AND forwardTwo are clear
            if (y == 7 && board.getPiece(forwardOne) == null && board.getPiece(forwardTwo) == null) {
                validMoves.add(new ChessMove(startPosition, forwardTwo, null));
            }

            // forwardOne. if at opposite end, promote.
            if (board.getPiece(forwardOne) == null && forwardOne.getRow() > 0) {       // space empty and within bounds
                if (forwardOne.getRow() == 1) {
                    for (ChessPiece.PieceType promotion : promotionPieces) {
                        validMoves.add(new ChessMove(startPosition, forwardOne, promotion));
                    }
                } else {
                    validMoves.add(new ChessMove(startPosition, forwardOne, null));
                }
            }

            // captureMoves
            for (ChessPosition endPosition : captureMoves) {
                // check bounds
                if (endPosition.getRow() < 1 || endPosition.getColumn() < 1 || endPosition.getColumn() > 8) {
                    continue;
                }
                // if this spot is NOT empty and has a piece of the opposite color, valid.
                if (board.getPiece(endPosition) != null && board.getPiece(endPosition).getTeamColor() != piece.getTeamColor()) {
                    if (endPosition.getRow() == 1) {
                        for (ChessPiece.PieceType promotion : promotionPieces) {
                            validMoves.add(new ChessMove(startPosition, endPosition, promotion));
                        }
                    } else {
                        validMoves.add(new ChessMove(startPosition, endPosition, null));
                    }
                }
            }
        }

        return validMoves;
    }

    private static ArrayList<ChessMove> queenMoves(ChessBoard board, ChessPosition startPosition, ChessPiece piece) {
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        ArrayList<ChessMove> bishopMoves = bishopMoves(board, startPosition, piece);
        ArrayList<ChessMove> rookMoves = rookMoves(board, startPosition, piece);

        validMoves.addAll(bishopMoves);
        validMoves.addAll(rookMoves);

        return validMoves;
    }

    private static ArrayList<ChessMove> rookMoves(ChessBoard board, ChessPosition startPosition, ChessPiece piece) {
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        // up
        for (int y = startPosition.getRow()+1, x = startPosition.getColumn(); y < 9; y++) {
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

        // right
        for (int y = startPosition.getRow(), x = startPosition.getColumn()+1; x < 9; x++) {
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

        // down
        for (int y = startPosition.getRow()-1, x = startPosition.getColumn(); y > 0; y--) {
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

        // left
        for (int y = startPosition.getRow(), x = startPosition.getColumn()-1; x > 0; x--) {
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
