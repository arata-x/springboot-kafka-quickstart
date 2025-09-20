#!/usr/bin/env bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "status": "IN_PROGRESS",
    "totalAmount": 1299.50,
    "createdBy": "quickstart"
  }'
