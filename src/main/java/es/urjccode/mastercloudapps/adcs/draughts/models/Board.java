package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Board {

    private static final int ONE_UNIT_DISTANCE = 1;

    private static final int TWO_UNITS_DISTANCE = 2;

    private static final int MAX_LIMIT = Coordinate.getDimension() - 2;

    private static final int MIN_LIMIT = 1;

    private Piece[][] pieces;

    Board() {
        this.pieces = new Piece[Coordinate.getDimension()][Coordinate.getDimension()];
        for (int i = 0; i < Coordinate.getDimension(); i++)
            for (int j = 0; j < Coordinate.getDimension(); j++)
                this.pieces[i][j] = null;
    }

    Piece getPiece(Coordinate coordinate) {
        assert coordinate != null;
        return this.pieces[coordinate.getRow()][coordinate.getColumn()];
    }

    void put(Coordinate coordinate, Piece piece) {
        this.pieces[coordinate.getRow()][coordinate.getColumn()] = piece;
    }

    Piece remove(Coordinate coordinate) {
        assert this.getPiece(coordinate) != null;
        Piece piece = this.getPiece(coordinate);
        this.put(coordinate, null);
        return piece;
    }

    void move(Coordinate origin, Coordinate target) {
        assert this.getPiece(origin) != null;
        this.put(target, this.remove(origin));
    }

    List<Piece> getBetweenDiagonalPieces(Coordinate origin, Coordinate target) {
        List<Piece> betweenDiagonalPieces = new ArrayList<>();
        if (origin.isOnDiagonal(target))
            for (Coordinate coordinate : origin.getBetweenDiagonalCoordinates(target)) {
                Piece piece = this.getPiece(coordinate);
                if (piece != null)
                    betweenDiagonalPieces.add(piece);
            }
        return betweenDiagonalPieces;
    }

    Color getColor(Coordinate coordinate) {
        final Piece piece = this.getPiece(coordinate);
        if (piece == null)
            return null;
        return piece.getColor();
    }

    boolean isEmpty(Coordinate coordinate) {
        return this.getPiece(coordinate) == null;
    }

    List<Coordinate> getRemovablePiecesWithEnemies(Color color, List<Coordinate> coordinates) {
        List<Coordinate> removablePiecesWithEnemies = new ArrayList<>();
        for (Coordinate coordinate : coordinates)
            this.addRemovablePiecesWithEnemies(removablePiecesWithEnemies, color, coordinate);
        return removablePiecesWithEnemies;
    }

    private void addRemovablePiecesWithEnemies(List<Coordinate> removablePiecesWithEnemies, Color color, Coordinate coordinate) {
        if (color.equals(Color.WHITE) && coordinate.getRow() > MIN_LIMIT) {
            if (coordinate.getColumn() < MAX_LIMIT && checkEnemyExistence(coordinate, Direction.SE))
                removablePiecesWithEnemies.add(coordinate);
            if (coordinate.getColumn() > MIN_LIMIT && checkEnemyExistence(coordinate, Direction.SW))
                removablePiecesWithEnemies.add(coordinate);
        }
        if (color.equals(Color.BLACK) && coordinate.getRow() < MAX_LIMIT) {
            if (coordinate.getColumn() > MIN_LIMIT && checkEnemyExistence(coordinate, Direction.NW))
                removablePiecesWithEnemies.add(coordinate);
            if (coordinate.getColumn() < MAX_LIMIT && checkEnemyExistence(coordinate, Direction.NE))
                removablePiecesWithEnemies.add(coordinate);
        }
    }

    private boolean checkEnemyExistence(Coordinate coordinate, Direction direction) {
        return this.getPiece(coordinate.getDiagonalCoordinate(direction, ONE_UNIT_DISTANCE)) != null
            && !this.getColor(coordinate.getDiagonalCoordinate(direction, ONE_UNIT_DISTANCE)).equals(this.getColor(coordinate))
            && this.getPiece(coordinate.getDiagonalCoordinate(direction, TWO_UNITS_DISTANCE)) == null;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(this.toStringHorizontalNumbers());
        for (int i = 0; i < Coordinate.getDimension(); i++)
            string.append(this.toStringHorizontalPiecesWithNumbers(i));
        string.append(this.toStringHorizontalNumbers());
        return string.toString();
    }

    private String toStringHorizontalNumbers() {
        StringBuilder string = new StringBuilder(" ");
        for (int j = 0; j < Coordinate.getDimension(); j++)
            string.append(j);
        return string + "\n";
    }

    private String toStringHorizontalPiecesWithNumbers(int row) {
        StringBuilder string = new StringBuilder(" " + row);
        for (int j = 0; j < Coordinate.getDimension(); j++) {
            Piece piece = this.getPiece(new Coordinate(row, j));
            if (piece == null)
                string.append(" ");
            else {
                string.append(piece);
            }
        }
        return string.toString() + row + "\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(pieces);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Board other = (Board) obj;
        return Arrays.deepEquals(pieces, other.pieces);
    }
}
