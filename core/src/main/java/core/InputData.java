package core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by gleb on 2/13/16.
 */
@Data
@AllArgsConstructor
public class InputData {
    private String fileName;
    private String type;
    private Integer from;
    private Integer to;
}

