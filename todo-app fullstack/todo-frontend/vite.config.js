import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path' 

// Remove root or set to __dirname
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      '~bootstrap': path.resolve(__dirname, 'node_modules/bootstrap'),
    }
  },
  server: {
    port: 5173,
    hot: true,
    open: true,
  }
});

