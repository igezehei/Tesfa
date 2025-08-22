import React, { useState, useEffect } from 'react';
import debounce from 'lodash.debounce';
import { gql, useLazyQuery } from '@apollo/client';
import { Box, List, ListItemText, ListItemButton } from '@mui/material';

const SUGGEST_QUERY = gql`
  query Suggest($prompt: String!, $provider: String!) {
    queryLLM(prompt: $prompt, provider: $provider)
  }
`;

export default function SmartCompose({ value, provider, onPick }: { value: string; provider: string; onPick: (s: string) => void }) {
  const [suggest, setSuggest] = useState('');
  const [fetchSuggest, { data }] = useLazyQuery(SUGGEST_QUERY);

  // debounce network calls
  const debounced = React.useMemo(() => debounce((v: string) => {
    if (v && v.trim().length > 4) {
      fetchSuggest({ variables: { prompt: v + '\nSuggest a short completion', provider } });
    }
  }, 400), [provider]);

  useEffect(() => {
    debounced(value);
    return () => { debounced.cancel(); };
  }, [value, debounced]);

  useEffect(() => {
    if (data && data.queryLLM) {
      setSuggest(data.queryLLM);
    }
  }, [data]);

  if (!suggest) return null;

  return (
    <Box sx={{ position: 'relative', mt: 1, bgcolor: '#0b0b0b', p: 1, borderRadius: 1 }}>
      <List dense>
        <ListItemButton onClick={() => onPick(suggest)}>
          <ListItemText primary={suggest} />
        </ListItemButton>
      </List>
    </Box>
  );
}
