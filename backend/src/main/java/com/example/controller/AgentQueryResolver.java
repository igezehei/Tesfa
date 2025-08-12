package com.example.controller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.adapter.LLMAdapterService;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class Agent {
    private String id;
    private String name;
    private String description;
    private String avatarUrl;
    private String context;
    private List<String> capabilities;

    public Agent(String id, String name, String description, String avatarUrl, String context, List<String> capabilities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avatarUrl = avatarUrl;
        this.context = context;
        this.capabilities = capabilities;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getAvatarUrl() { return avatarUrl; }
    public String getContext() { return context; }
    public List<String> getCapabilities() { return capabilities; }
}

@Component
class AgentQueryResolver implements GraphQLQueryResolver {
    public List<Agent> agents() {
        return Arrays.asList(
            new Agent("1", "ChatGPT", "OpenAI's conversational agent.", "https://avatars.githubusercontent.com/u/1?v=4", "General-purpose AI assistant.", Arrays.asList("code", "chat", "reasoning")),
            new Agent("2", "Claude", "Anthropic's helpful AI agent.", "https://avatars.githubusercontent.com/u/2?v=4", "Helpful, harmless, honest AI.", Arrays.asList("chat", "summarization")),
            new Agent("3", "Drafahan", "Drafahan AI assistant.", "https://avatars.githubusercontent.com/u/3?v=4", "Domain-specific expert.", Arrays.asList("domain-expert", "data"))
        );
    }
}
