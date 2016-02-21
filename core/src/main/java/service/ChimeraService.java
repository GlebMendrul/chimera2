package service;

import constants.Types;
import core.ChimeraParser;
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
    private ChimeraParser parser;

    public void init(InputData inputData) {
        this.reader = new ChimeraReader(inputData);
        this.parser = new ChimeraParser(Types.getEnum(inputData.getType()), inputData.getCompress());
    }

    public boolean hasNext() {
        return reader.hasNext();
    }

    public String next() {
        return parser.process(reader.next());
    }
}
