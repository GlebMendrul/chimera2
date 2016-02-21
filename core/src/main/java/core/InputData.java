package core;

import constants.Compress;
import lombok.AllArgsConstructor;
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
    private Compress compress;
}

