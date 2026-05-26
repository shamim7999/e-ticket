#!/bin/bash
source .env

# ─── Config ───────────────────────────────────────────────────────────────────
GIT_USER="${GITHUB_USER}"
GIT_PASSWORD="${GITHUB_PASSWORD}"
# ──────────────────────────────────────────────────────────────────────────────

# Ask for commit message
echo -n "Commit message: "
read COMMIT_MSG

# Validate commit message
if [ -z "${COMMIT_MSG// }" ]; then
    echo "Error: Commit message cannot be empty."
    exit 1
fi

# Stage all changes
git add .

# Commit
git commit -m "$COMMIT_MSG"

# Push with credentials
git push "$(git remote get-url origin | sed "s|://|://${GIT_USER}:${GIT_PASSWORD}@|")"
