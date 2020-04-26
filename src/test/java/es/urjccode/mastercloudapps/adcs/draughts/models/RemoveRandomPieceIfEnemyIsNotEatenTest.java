package es.urjccode.mastercloudapps.adcs.draughts.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RemoveRandomPieceIfEnemyIsNotEatenTest extends GameTest {

    private void assertMove(Coordinate... coordinates) {
        assertNull(this.game.move(coordinates));
        assertEquals(this.game, this.expectedGame);
    }

    @Test
    public void testRemoveWhitePieceWhenMovesAndEnemyIsNotEaten() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  n     ",
            " b      ",
            "        ");
        this.setExpectedGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  n     ",
            "        ",
            "        ");
        this.assertMove(
            new Coordinate(6, 1),
            new Coordinate(5, 0)
        );
    }

    @Test
    public void testRemoveBlackPieceWhenMovesAndEnemyIsNotEaten() {
        this.setGame(Color.BLACK,
            "        ",
            "        ",
            " n      ",
            "  b     ",
            "        ",
            "        ",
            "        ",
            "        ");
        this.setExpectedGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ",
            "        ",
            "        ");
        this.assertMove(
            new Coordinate(2, 1),
            new Coordinate(3, 0)
        );
    }

    @Test
    public void testRemoveWhitePieceWhenMovesOnceAndEnemyIsNotEaten() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "     n  ",
            "        ",
            "   n    ",
            "  b     ",
            "        ",
            "        ");
        this.setExpectedGame(Color.BLACK,
            "        ",
            "        ",
            "     n  ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        this.assertMove(
            new Coordinate(5, 2),
            new Coordinate(3, 4)
        );
    }

    @Test
    public void testRemoveBlackPieceWhenMovesOnceAndEnemyIsNotEaten() {
        this.setGame(Color.BLACK,
            "        ",
            "    n   ",
            "   b    ",
            "        ",
            " b      ",
            "        ",
            "        ",
            "        ");
        this.setExpectedGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            " b      ",
            "        ",
            "        ",
            "        ");
        this.assertMove(
            new Coordinate(1, 4),
            new Coordinate(3, 2)
        );
    }

    @Test
    public void testRemoveWhitePieceWhenMovesTwiceAndEnemyIsNotEaten() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "     n  ",
            "        ",
            "   n    ",
            "        ",
            " n      ",
            "b       ");
        this.setExpectedGame(Color.BLACK,
            "        ",
            "        ",
            "     n  ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        this.assertMove(
            new Coordinate(7, 0),
            new Coordinate(5, 2),
            new Coordinate(3, 4)
        );
    }

    @Test
    public void testRemoveBlackPieceWhenMovesTwiceAndEnemyIsNotEaten() {
        this.setGame(Color.BLACK,
            "        ",
            "n       ",
            " b      ",
            "        ",
            "   b    ",
            "        ",
            "     b  ",
            "        ");
        this.setExpectedGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "     b  ",
            "        ");
        this.assertMove(
            new Coordinate(1, 0),
            new Coordinate(3, 2),
            new Coordinate(5, 4)
        );
    }
}
