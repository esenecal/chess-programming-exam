package chess;

import java.util.Arrays;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    // Contains chess pieces in a board.
    private final ChessPiece[][] board = new ChessPiece[8][8];

    public ChessBoard() {
        
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        board[position.getRow()-1][position.getColumn()-1] = piece;     // offset by 1, as Row and Column are 1-8
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return board[position.getRow()-1][position.getColumn()-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        for (int y = 1; y < 9; y++) {       // clear the board.
            for (int x = 1; x < 9; x++) {
                addPiece(new ChessPosition(y, x), null);
            }
        }

        // white pieces
        int homeRow = 1;
        ChessGame.TeamColor teamColor = ChessGame.TeamColor.WHITE;

        // pawns
        for (int y = 2, x = 1; x < 9; x++) {
            addPiece(new ChessPosition(y, x), new ChessPiece(teamColor, ChessPiece.PieceType.PAWN));
        }

        // rook
        addPiece(new ChessPosition(homeRow, 1), new ChessPiece(teamColor, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(homeRow, 8), new ChessPiece(teamColor, ChessPiece.PieceType.ROOK));

        // knight
        addPiece(new ChessPosition(homeRow, 2), new ChessPiece(teamColor, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(homeRow, 7), new ChessPiece(teamColor, ChessPiece.PieceType.KNIGHT));

        // bishop
        addPiece(new ChessPosition(homeRow, 3), new ChessPiece(teamColor, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(homeRow, 6), new ChessPiece(teamColor, ChessPiece.PieceType.BISHOP));

        // queen
        addPiece(new ChessPosition(homeRow, 4), new ChessPiece(teamColor, ChessPiece.PieceType.QUEEN));

        // king
        addPiece(new ChessPosition(homeRow, 5), new ChessPiece(teamColor, ChessPiece.PieceType.KING));

        // black pieces
        homeRow = 8;
        teamColor = ChessGame.TeamColor.BLACK;

        // pawns
        for (int y = 7, x = 1; x < 9; x++) {
            addPiece(new ChessPosition(y, x), new ChessPiece(teamColor, ChessPiece.PieceType.PAWN));
        }

        // rook
        addPiece(new ChessPosition(homeRow, 1), new ChessPiece(teamColor, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(homeRow, 8), new ChessPiece(teamColor, ChessPiece.PieceType.ROOK));

        // knight
        addPiece(new ChessPosition(homeRow, 2), new ChessPiece(teamColor, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(homeRow, 7), new ChessPiece(teamColor, ChessPiece.PieceType.KNIGHT));

        // bishop
        addPiece(new ChessPosition(homeRow, 3), new ChessPiece(teamColor, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(homeRow, 6), new ChessPiece(teamColor, ChessPiece.PieceType.BISHOP));

        // queen
        addPiece(new ChessPosition(homeRow, 4), new ChessPiece(teamColor, ChessPiece.PieceType.QUEEN));

        // king
        addPiece(new ChessPosition(homeRow, 5), new ChessPiece(teamColor, ChessPiece.PieceType.KING));
    }

    // Created with IntelliJ
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ChessBoard that)) {
            return false;
        }
        return Objects.deepEquals(board, that.board);
    }

    // Created with IntelliJ
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
}
