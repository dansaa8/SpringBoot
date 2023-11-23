USE geo_POI;

INSERT INTO category (id, name, symbol, description)
VALUES (1, 'Playground', 'PG_Symbol', 'A place for the kids to play');

INSERT INTO category (id, name, symbol, description)
VALUES (2, 'GroceryStore', 'GS_Symbol', 'A place to buy groceries');

INSERT INTO category (id, name, symbol, description)
VALUES (3, 'BusStop', 'Bus_Symbol', 'A point to board a bus');

INSERT INTO location(id, name, status, coordinate, description, created_at, last_modified, fk_category)
VALUES (
           1,
           'Borås Resecentrum',
           'public',
           ST_GeomFromText('POINT(57.72007071572413 12.932595441618504)', 4326),
           'Travel Center',
           '2021-02-03',
           '2021-02-04',
           3);

INSERT INTO location(id, name, status, coordinate, description, created_at, last_modified, fk_category)
VALUES (
           2,
           'Lekplats Lindormsgatan',
           'public',
           ST_GeomFromText('POINT(57.72007071572413 12.932595441618504)', 4326),
           'Small playground',
           '2022-04-03',
           '2023-02-26',
           1);

INSERT INTO location(id, name, status, coordinate, description, created_at, last_modified, fk_category)
VALUES (
           3,
           'Öresjös matbutik',
           'private',
           #andra param format på det vi matar in. Är det annat format än det vi har i DB, så konverteras det automatiskt.
           ST_GeomFromText('POINT(57.76426277348248 12.946753643258686)', 4326),
           'Secret grocery store',
           '1977-06-17',
           '1999-12-24',
           1);