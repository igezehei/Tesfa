import { ApolloClient, InMemoryCache, ApolloProvider, gql, useLazyQuery } from '@apollo/client';
import React, { useState } from 'react';

const client = new ApolloClient({
  uri: '/graphql',
  cache: new InMemoryCache(),
});

const QUERY_LLM = gql`
  query QueryLLM($prompt: String!) {
    queryLLM(prompt: $prompt)
  }
`;

function LLMQueryComponent() {
  const [prompt, setPrompt] = useState('');
  const [queryLLM, { data, loading, error }] = useLazyQuery(QUERY_LLM);

  return (
    <div>
      <h2>Query LLM via GraphQL</h2>
      <input
        value={prompt}
        onChange={e => setPrompt(e.target.value)}
        placeholder="Enter prompt"
      />
      <button onClick={() => queryLLM({ variables: { prompt } })} disabled={loading}>
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