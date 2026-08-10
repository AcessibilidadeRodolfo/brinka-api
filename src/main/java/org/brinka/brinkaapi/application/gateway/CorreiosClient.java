package org.brinka.brinkaapi.application.gateway;

import org.brinka.brinkaapi.application.dto.input.FreteInput;
import org.brinka.brinkaapi.application.dto.output.FreteOutput;

public interface CorreiosClient {
    FreteOutput calculateFrete(FreteInput input);
}
