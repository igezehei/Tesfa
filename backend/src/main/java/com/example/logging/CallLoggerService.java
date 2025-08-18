package com.example.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CallLoggerService {
    private final Path logPath = Paths.get(System.getProperty("user.home"), "tesfa-llm-calls.log");
    private final ObjectWriter writer = new ObjectMapper().writer();

    public CallLoggerService() {
        try {
            if (!Files.exists(logPath)) {
                Files.createFile(logPath);
            }
        } catch (IOException e) {
            // ignore for now
        }
    }

    public void log(CallRecord record) {
        try (FileWriter fw = new FileWriter(logPath.toFile(), true)) {
            String json = writer.writeValueAsString(record);
            fw.write(json + "\n");
        } catch (IOException e) {
            // ignore
        }
    }

    public List<CallRecord> recent(int limit) {
        try {
            List<String> lines = Files.readAllLines(logPath);
            List<CallRecord> out = new ArrayList<>();
            ObjectMapper m = new ObjectMapper();
            int start = Math.max(0, lines.size() - limit);
            for (int i = lines.size()-1; i >= start; i--) {
                String l = lines.get(i);
                CallRecord r = m.readValue(l, CallRecord.class);
                out.add(r);
            }
            return out;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
