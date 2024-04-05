# Calc backend API

## Description
This API provides functionality for working with data, connections to servers, clients, networks, registration, virtual machines (VMs), and user authentication and management.

## Base URL
http://localhost:8080/api/

## Authentication
Authentication is required to access protected resources. Use the `/api/security/auth` endpoint to obtain an authentication token.

## Endpoints
OpenAPI: `backend-openapi.yaml`

### Security
- POST http://localhost:8080/api/security/auth
- POST http://localhost:8080/api/security/check
- POST http://localhost:8080/api/security/logout

### Data
#### Client - Clients
- GET http://localhost:8080/api/data/client
- POST http://localhost:8080/api/data/client/insert
- POST http://localhost:8080/api/data/client/update
- POST http://localhost:8080/api/data/client/delete
- POST http://localhost:8080/api/data/client/balance
- POST http://localhost:8080/api/data/client/pay

#### FM - Physical Machines
- GET http://localhost:8080/api/data/fm
- GET http://localhost:8080/api/data/fm/vm
- POST http://localhost:8080/api/data/fm/insert
- POST http://localhost:8080/api/data/fm/update
- POST http://localhost:8080/api/data/fm/delete
- POST http://localhost:8080/api/data/fm/rent

#### Net - Networks, Subnets
- GET http://localhost:8080/api/data/net
- POST http://localhost:8080/api/data/net/insert
- POST http://localhost:8080/api/data/net/update
- POST http://localhost:8080/api/data/net/delete
- POST http://localhost:8080/api/data/net/rent

#### Reg - Registry
- GET http://localhost:8080/api/data/reg
- POST http://localhost:8080/api/data/reg/insert
- POST http://localhost:8080/api/data/reg/update
- POST http://localhost:8080/api/data/reg/delete

#### VM - Virtual Machines
- GET http://localhost:8080/api/data/vm
- POST http://localhost:8080/api/data/vm/ssh/update
- POST http://localhost:8080/api/data/vm/history
- POST http://localhost:8080/api/data/vm/insert
- POST http://localhost:8080/api/data/vm/update
- POST http://localhost:8080/api/data/vm/delete
- POST http://localhost:8080/api/data/vm/rent

#### VPN Server - VPN Servers
- GET http://localhost:8080/api/data/vpn/server
- POST http://localhost:8080/api/data/vpn/server/insert
- POST http://localhost:8080/api/data/vpn/server/update
- POST http://localhost:8080/api/data/vpn/server/delete

#### VPN Type - VPN Types
- GET http://localhost:8080/api/data/vpn/type
- POST http://localhost:8080/api/data/vpn/type/insert
- POST http://localhost:8080/api/data/vpn/type/update
- POST http://localhost:8080/api/data/vpn/type/delete

#### VPN User - VPN User Accounts
- GET http://localhost:8080/api/data/vpn/user
- POST http://localhost:8080/api/data/vpn/user/insert
- POST http://localhost:8080/api/data/vpn/user/update
- POST http://localhost:8080/api/data/vpn/user/delete