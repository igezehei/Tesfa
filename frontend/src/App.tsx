import { ApolloClient, InMemoryCache, ApolloProvider, gql, useLazyQuery } from '@apollo/client';
import React, { useState, useEffect } from 'react';

const client = new ApolloClient({
  uri: '/graphql',
  cache: new InMemoryCache(),
});

function useLogger(name: string, value: any) {
  useEffect(() => {
    console.log(`[${name}]`, value);
  }, [name, value]);
}

function LLMQueryComponent() {
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

  return (
    <div>
      <h2>Query LLM via GraphQL</h2>
      <select value={provider} onChange={e => setProvider(e.target.value)}>
        <option value="openai">OpenAI</option>
        <option value="anthropic">Anthropic</option>
        <option value="drafahan">Drafahan</option>
        <option value="default">Default</option>
      </select>
      <input
        value={prompt}
        onChange={e => setPrompt(e.target.value)}
        placeholder="Enter prompt"
      />
      <button onClick={() => queryLLM({ variables: { prompt, provider } })} disabled={loading}>
        Query
      </button>
      {loading && <p>Loading...</p>}
      {error && <p>Error: {error.message}</p>}
      {data && <pre>{data.queryLLM}</pre>}
    </div>
  );
}

function App() {
  return (
    <ApolloProvider client={client}>
      <div className="App">
        <h1>Welcome to Project Tesfa</h1>
        <LLMQueryComponent />
      </div>
    </ApolloProvider>
  );
}

export default App;