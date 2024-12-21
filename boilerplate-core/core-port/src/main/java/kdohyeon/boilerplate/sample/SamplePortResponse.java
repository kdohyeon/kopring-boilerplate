package kdohyeon.boilerplate.sample;

import lombok.Getter;

@Getter
public class SamplePortResponse {
    private final String name;

    public SamplePortResponse(String name) {
        this.name = name;
    }
}
