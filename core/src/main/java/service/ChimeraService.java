package service;

import constants.Types;
import core.ChimeraFilter;
import core.ChimeraReader;
import core.InputData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by gleb on 2/13/16.
 */
@Service
@Scope(value = "prototype")
public class ChimeraService {

    private ChimeraReader reader;
    private ChimeraFilter filter;

    public void init(InputData inputData) {
        this.reader = new ChimeraReader(inputData);
        this.filter = new ChimeraFilter(Types.getEnum(inputData.getType()));
    }

    public boolean hasNext() {
        return reader.hasNext();
    }

    public String next() {
        return filter.process(reader.next());
    }
}
