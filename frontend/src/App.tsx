import { ApolloClient, InMemoryCache, ApolloProvider, gql, useLazyQuery } from '@apollo/client';
import React, { useState, useEffect } from 'react';
import { Select, MenuItem, InputLabel, FormControl, Box, Typography, Button, TextField, CircularProgress } from '@mui/material';
import CallLogsPanel from './CallLogsPanel';
import SmartCompose from './SmartCompose';

const client = new ApolloClient({
  uri: '/graphql',
  cache: new InMemoryCache(),
});

function useLogger(name: string, value: any) {
  useEffect(() => {
    console.log(`[${name}]`, value);
  }, [name, value]);
}

function ChatMessage({ message, sender }: { message: string; sender: string }) {
  return (
    <Box sx={{ mb: 2, display: 'flex', flexDirection: sender === 'user' ? 'row-reverse' : 'row' }}>
      <Box sx={{ p: 2, bgcolor: sender === 'user' ? '#007acc' : '#252526', borderRadius: 2, maxWidth: '70%', color: '#fff' }}>
        <Typography variant="body1">{message}</Typography>
        <Typography variant="caption" sx={{ color: '#61dafb' }}>{sender === 'user' ? 'You' : 'Agent'}</Typography>
      </Box>
    </Box>
  );
}

function LLMChatComponent() {
  const [messages, setMessages] = useState<{ message: string; sender: string; provider: string }[]>([]);
  const [prompt, setPrompt] = useState('');
  const [provider, setProvider] = useState('openai');
  const [queryLLM, { data, loading, error }] = useLazyQuery(gql`
    query QueryLLM($prompt: String!, $provider: String!) {
      queryLLM(prompt: $prompt, provider: $provider)
    }
  `);

  useLogger('Prompt', prompt);
  useLogger('Provider', provider);
  useLogger('Data', data);
  useLogger('Error', error);
  useLogger('Loading', loading);

  useEffect(() => {
    if (data && data.queryLLM) {
      setMessages(prev => [...prev, { message: data.queryLLM, sender: 'agent', provider }]);
    }
  }, [data, provider]);

  const handleSend = () => {
    if (prompt.trim()) {
      setMessages(prev => [...prev, { message: prompt, sender: 'user', provider }]);
      queryLLM({ variables: { prompt, provider } });
      setPrompt('');
    }
  };

  return (
    <Box sx={{ maxWidth: 600, mx: 'auto', mt: 4, p: 3, boxShadow: 3, borderRadius: 2, bgcolor: '#1e1e1e', color: '#fff' }}>
      <Typography variant="h4" gutterBottom sx={{ fontWeight: 'bold', color: '#61dafb' }}>
        Chat with Agent / LLM
      </Typography>
      <FormControl fullWidth sx={{ mb: 2 }}>
        <InputLabel id="provider-label" sx={{ color: '#fff' }}>LLM Provider</InputLabel>
        <Select
          labelId="provider-label"
          value={provider}
          label="LLM Provider"
          onChange={e => setProvider(e.target.value)}
          sx={{ bgcolor: '#252526', color: '#fff' }}
        >
          <MenuItem value="openai">ChatGPT (OpenAI)</MenuItem>
          <MenuItem value="anthropic">Claude (Anthropic)</MenuItem>
          <MenuItem value="drafahan">Drafahan</MenuItem>
          <MenuItem value="default">Default</MenuItem>
        </Select>
      </FormControl>
      <Box sx={{ maxHeight: 300, overflowY: 'auto', mb: 2, bgcolor: '#181818', p: 2, borderRadius: 2 }}>
        {messages.map((msg, idx) => (
          <ChatMessage {...msg} key={idx} />
        ))}
        {loading && <CircularProgress sx={{ color: '#61dafb', mb: 2 }} />}
        {error && <Typography color="error">Error: {error.message}</Typography>}
      </Box>
      <Box sx={{ display: 'flex', gap: 2 }}>
        <Box sx={{ flex: 1 }}>
          <TextField
          fullWidth
          variant="filled"
          label="Type your message..."
          value={prompt}
          onChange={e => setPrompt(e.target.value)}
          sx={{ bgcolor: '#252526', input: { color: '#fff' }, label: { color: '#fff' } }}
          onKeyDown={e => { if (e.key === 'Enter') handleSend(); }}
        />
          <SmartCompose value={prompt} provider={provider} onPick={(s) => setPrompt(p => (p + ' ' + s).trim())} />
        </Box>
        <Button
          variant="contained"
          color="primary"
          onClick={handleSend}
          disabled={loading || !prompt.trim()}
        >
          Send
        </Button>
      </Box>
    </Box>
  );
}

function App() {
  return (
    <ApolloProvider client={client}>
      <div className="App">
  <LLMChatComponent />
  <CallLogsPanel />
      </div>
    </ApolloProvider>
  );
}

export default App;