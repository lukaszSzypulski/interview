package interview;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TestCompositeBlock implements CompositeBlock {
    private String color;
    private String material;
    private List<Block> blocks;
}
