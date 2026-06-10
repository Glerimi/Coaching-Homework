# Basketball Analytics - Spring Boot + Angular

## Implemented homework features

### Backend dummy-data homework
- `DummyBasketballController`
- `DummyBasketballService` + interface
- Models: `DummyPlayer`, `Team`, `DashboardStats`, `PlayerRequest`
- Example mappings:
  - `GET /api/stats`
  - `GET /api/players`
  - `GET /api/players/{id}`
  - `POST /api/players`
  - `GET /api/teams`
  - `GET /api/teams/{id}`

### Backend PostgreSQL homework
- Entity: `PlayerEntity`
- Repository: `PlayerRepository extends JpaRepository`
- Service: `PlayerService` + `PlayerServiceImpl`
- Controller: `PlayerController`
- Mappings:
  - `GET /api/db/players`
  - `GET /api/db/players/{id}`
  - `GET /api/db/players?status=Active`
  - `POST /api/db/players`
  - `PUT /api/db/players/{id}`
  - `DELETE /api/db/players/{id}`

### Frontend PostgreSQL feature
- Angular service: `PlayerService`
- Angular component: `PlayersComponent`
- The component calls the backend and visualizes players from PostgreSQL.

## PostgreSQL setup
Create a database named `basketball_db` with user/password `postgres/postgres`, or override the values:

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/basketball_db"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="postgres"
```

The app creates/updates the `players` table automatically and inserts dummy players when the table is empty.

## Start backend
```powershell
cd backend
mvn spring-boot:run
```

If you use mvnd:
```powershell
mvnd spring-boot:run
```

## Start frontend
```powershell
cd frontend
npm install --legacy-peer-deps
npx ng serve
```

Open `http://localhost:4200`.
