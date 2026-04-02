#!/bin/bash

echo "Building Docker image..."
docker build -t reverse-platform .

echo ""
echo "Running Docker container..."
docker run -p 8080:8080 --name reverse-platform-app reverse-platform