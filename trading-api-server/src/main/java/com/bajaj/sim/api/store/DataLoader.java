package com.bajaj.sim.api.store;

import com.bajaj.sim.api.model.Instrument;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader {
    private final InMemoryStore store;
    private final ObjectMapper mapper;

    public DataLoader(InMemoryStore store) {
        this.store = store;
        this.mapper = new ObjectMapper();
    }

    @PostConstruct
    public void loadInstruments() throws IOException {
        InputStream is = getClass().getResourceAsStream("/data/instruments.json");
        List<Instrument> instruments = mapper.readValue(is, new TypeReference<List<Instrument>>(){});
        for (Instrument inst : instruments) {
            store.addInstrument(inst);
        }
    }
}