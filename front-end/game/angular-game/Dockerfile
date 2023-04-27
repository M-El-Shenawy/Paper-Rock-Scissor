# Stage 1: Compile and Build angular codebase

# Use official node image as the base image
FROM node:latest as build

# Set the working directory
WORKDIR /usr/local/app

# Add the source code to app
COPY ./ /usr/local/app/

# Install all the dependencies
RUN npm install

# Build the application
RUN npm run build --prod

# Stage 2: Serve app with nginx server
FROM nginx:latest

COPY --from=build /usr/local/app/dist/angular-game /usr/share/nginx/html
