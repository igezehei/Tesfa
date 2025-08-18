import React from 'react';
import { gql, useQuery } from '@apollo/client';
import { Box, Typography, List, ListItem, ListItemText, Divider } from '@mui/material';

const RECENT_CALLS = gql`
  query RecentCalls($limit: Int) {
    recentCalls(limit: $limit) {
      id
      timestamp
      provider
      promptHash
      responseSnippet
      durationMs
      status
      error
    }
  }
`;

export default function CallLogsPanel() {
  const { data, loading, error } = useQuery(RECENT_CALLS, { variables: { limit: 50 } });

  if (loading) return <Typography>Loading call logs...</Typography>;
  if (error) return <Typography color="error">Error loading logs</Typography>;

  return (
    <Box sx={{ mt: 2, p: 2, bgcolor: '#111', color: '#fff', borderRadius: 2 }}>
      <Typography variant="h6" sx={{ color: '#61dafb' }}>Recent LLM Calls</Typography>
      <List>
        {(data.recentCalls || []).map((c: any) => (
          <React.Fragment key={c.id || c.promptHash}>
            <ListItem>
              <ListItemText primary={`${c.provider} • ${new Date(c.timestamp).toLocaleString()}`} secondary={`hash=${c.promptHash} duration=${c.durationMs}ms status=${c.status}`} />
            </ListItem>
            <ListItem>
              <ListItemText secondary={c.responseSnippet} />
            </ListItem>
            <Divider />
          </React.Fragment>
        ))}
      </List>
    </Box>
  );
}
