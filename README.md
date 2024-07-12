# personnel-system

## Technologies
Spring(Boot, JPA), Swagger, Docker, PostgreSQL, Liquibase, JS, bootstrap, lombok, thymeleaf

## Features
  * Backend:
     * Employees may be hired and fire.
     * Employees may transfer from unit to unit
     * Get department status by date
  * UI
      * Add a new employee to any unit
      * Display a list of all subdivisions as of a specific date
      * Output employees of the selected subdivision for the period

  ## Environments

  To run this application you need to fill `.env` file in root directory with next environments:

- `POSTGRES_HOST` - host of Postgresql database
- `POSTGRES_USER` - username for Postgresql database
- `POSTGRES_PASSWORD` - password for Postgresql database
- `POSTGRES_DB` - name of Postgresql database
- `POSTGRES_PORT` - port of Postgresql database

## Quick start
1. Clone this repo into folder.

```Bash
git clone https://github.com/qReolq/personnel-system.git
cd personnel-system
```

2. Start docker compose

```Bash
docker compose up
```

3 you can go:
- UI: http://localhost:8080/
- Swagger: http://localhost:8080/swagger-ui/index.html#/
