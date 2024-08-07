package interview;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class Wall implements Structure {
    private List<Block> blocks;

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findBlockByColorPrivate(blocks, color);
    }

    private Optional<Block> findBlockByColorPrivate(List<Block> blocks, String color) {
        for (Block block : blocks) {
            if (block.getColor() != null && block.getColor().equals(color)) {
                return Optional.of(block);
            }
            if (block instanceof CompositeBlock compositeBlock) {
                Optional<Block> result = findBlockByColorPrivate(compositeBlock.getBlocks(), color);
                if (result.isPresent()) {
                    return result;
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> result = new ArrayList<>();
        findBlocksByMaterialPrivate(blocks, material, result);
        return result;
    }

    private void findBlocksByMaterialPrivate(List<Block> blocks, String material, List<Block> result) {
        for (Block block : blocks) {
            if (block.getMaterial() != null && block.getMaterial().equals(material)) {
                result.add(block);
            }
            if (block instanceof CompositeBlock compositeBlock) {
                findBlocksByMaterialPrivate(compositeBlock.getBlocks(), material, result);
            }
        }
    }

    @Override
    public int count() {
        return counter(blocks);
    }

    private int counter(List<Block> blocks) {
        int count = 0;
        for (Block block : blocks) {
            count++;
            if (block instanceof CompositeBlock compositeBlock) {
                count += counter(compositeBlock.getBlocks());
            }
        }
        return count;
    }
}
