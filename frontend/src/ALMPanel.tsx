import React, { useState } from 'react';
import { Box, Typography, List, ListItem, ListItemText, Button, Link, Chip } from '@mui/material';

export default function ALMPanel() {
  const [health, setHealth] = useState<string | null>(null);
  const [checking, setChecking] = useState(false);

  const checkHealth = async () => {
    setChecking(true);
    try {
      const res = await fetch('/actuator/health');
      if (!res.ok) throw new Error(`HTTP ${res.status}`);
      const body = await res.json();
      setHealth(body.status || JSON.stringify(body));
    } catch (e: any) {
      setHealth(`error: ${e.message}`);
    } finally {
      setChecking(false);
    }
  };

  return (
    <Box sx={{ mt: 3, p: 2, borderRadius: 2, bgcolor: '#0f1115', color: '#fff' }}>
      <Typography variant="h6" sx={{ color: '#9cdcfe', mb: 1 }}>ALM — Application Lifecycle</Typography>
      <Typography variant="body2" sx={{ color: '#cfcfcf', mb: 2 }}>
        High-level links and quick checks for the project's lifecycle: source, CI, deployments, coverage and runtime health.
      </Typography>

      <List>
        <ListItem>
          <ListItemText primary="Source" secondary={<Link href="https://github.com/igezehei/Tesfa" target="_blank" rel="noopener noreferrer">github.com/igezehei/Tesfa</Link>} />
        </ListItem>
        <ListItem>
          <ListItemText primary="Issues" secondary={<Link href="https://github.com/igezehei/Tesfa/issues" target="_blank" rel="noopener noreferrer">Open issues / backlog</Link>} />
        </ListItem>
        <ListItem>
          <ListItemText primary="CI / Actions" secondary={<Link href="https://github.com/igezehei/Tesfa/actions" target="_blank" rel="noopener noreferrer">GitHub Actions</Link>} />
        </ListItem>
        <ListItem>
          <ListItemText primary="Coverage" secondary={<Link href="/coverage/index.html" target="_blank" rel="noopener noreferrer">Coverage report (JaCoCo)</Link>} />
        </ListItem>
        <ListItem>
          <ListItemText primary="Deployments" secondary={<Link href="/deployments" target="_blank" rel="noopener noreferrer">Deployment dashboard / CD</Link>} />
        </ListItem>
      </List>

      <Box sx={{ display: 'flex', gap: 2, mt: 2, alignItems: 'center' }}>
        <Button variant="contained" color="primary" onClick={checkHealth} disabled={checking}>
          Check backend health
        </Button>
        {health && (
          <Chip label={health} color={health === 'UP' ? 'success' : 'warning'} />
        )}
      </Box>
    </Box>
  );
}
