# Name the node stage "builder"
FROM node:15-alpine AS builder

# Set working directory
WORKDIR /app

# Copy our node module specification
COPY package.json package.json
COPY package-lock.json package-lock.json


# install node modules and build assets
RUN npm install --production

# Copy all files from current directory to working dir in image
# Except the one defined in '.dockerignore'
COPY . .

# Create production build of React App
RUN npm run build

# Choose NGINX as our base Docker image
FROM nginx:alpine

# Default port exposure
EXPOSE 80

# Set working directory to nginx asset directory
WORKDIR /usr/share/nginx/html

# Remove default nginx static assets
RUN rm -rf *

# Copy static assets from builder stage
COPY --from=builder /app/build .

#copy env variable setting script
COPY ./env.sh .
COPY .env .

# Make our shell script executable
RUN chmod +x env.sh




# Entry point when Docker container has started, wich will wet the env variables on startup
CMD ["/bin/sh", "-c", "/usr/share/nginx/html/env.sh && nginx -g \"daemon off;\""]