import React from 'react';
import { Box, Typography, Link } from '@mui/material';

export default function ContactFooter() {
  return (
    <Box sx={{ mt: 4, py: 3, textAlign: 'center', bgcolor: 'transparent' }}>
      <Typography variant="h6" sx={{ color: '#61dafb', mb: 1 }}>Get in touch</Typography>
      <Typography variant="body2" sx={{ color: '#ccc' }}>
        Found a bug or want to contribute? Visit the project on{' '}
        <Link href="https://github.com/igezehei/Tesfa" target="_blank" rel="noopener noreferrer" sx={{ color: '#9cdcfe' }}>
          GitHub
        </Link>
        .
      </Typography>
    </Box>
  );
}
