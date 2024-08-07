import interview.*;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.testng.AssertJUnit.*;


public class TestWall {

    @Test
    public void testFindBlockByColor() {
        Block block1 = new TestBlock("black", "carbon");
        Block block2 = new TestBlock("gray", "metal");
        CompositeBlock compositeBlock = new TestCompositeBlock("blue", "water", Arrays.asList(block1, block2));

        Wall wall = new Wall(List.of(compositeBlock));

        Optional<Block> result = wall.findBlockByColor("black");
        assertTrue(result.isPresent());
        assertEquals("black", result.get().getColor());

        result = wall.findBlockByColor("blue");
        assertTrue(result.isPresent());
        assertEquals("blue", result.get().getColor());

        result = wall.findBlockByColor("gray");
        assertTrue(result.isPresent());
        assertEquals("gray", result.get().getColor());

        result = wall.findBlockByColor("green");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindBlocksByMaterial() {
        Block block1 = new TestBlock("black", "carbon");
        Block block2 = new TestBlock("gray", "metal");
        CompositeBlock compositeBlock = new TestCompositeBlock("blue", "water", Arrays.asList(block1, block2));

        Wall wall = new Wall(List.of(compositeBlock));

        List<Block> result = wall.findBlocksByMaterial("carbon");
        assertEquals(1, result.size());
        assertEquals("carbon", result.get(0).getMaterial());

        result = wall.findBlocksByMaterial("carbon");
        assertEquals(1, result.size());
        assertEquals("carbon", result.get(0).getMaterial());

        result = wall.findBlocksByMaterial("water");
        assertEquals(1, result.size());
        assertEquals("water", result.get(0).getMaterial());

        result = wall.findBlocksByMaterial("grass");
        assertTrue(result.isEmpty());
    }

    @Test
    public void countTest() {
        Block block1 = new TestBlock("black", "carbon");
        Block block2 = new TestBlock("gray", "metal");
        CompositeBlock compositeBlock = new TestCompositeBlock("blue", "water", Arrays.asList(block1, block2));

        Wall wall = new Wall(List.of(compositeBlock));
        assertEquals(3, wall.count());

        Wall emptyWall = new Wall(Collections.emptyList());
        assertEquals(0, emptyWall.count());
    }
}