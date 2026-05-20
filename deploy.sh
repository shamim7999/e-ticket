#!/bin/bash

set -e

while true
do
  echo "🚀 Deploy cycle started at $(date)"

  echo "🛑 Stopping old containers..."
  docker compose -f docker-compose-prod.yml down --remove-orphans

  echo "📥 Pulling latest images..."
  docker compose -f docker-compose-prod.yml pull

  echo "▶️ Starting new containers..."
  docker compose -f docker-compose-prod.yml up -d

  echo "🧹 Cleaning unused images..."
  docker image prune -f

  echo "✅ Deploy cycle done. Sleeping 60 seconds..."
  sleep 60
done