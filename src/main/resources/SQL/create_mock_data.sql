USE geo_POI;

INSERT INTO category (id, name, symbol, description)
VALUES (1, 'GroceryStore', 'Boutique', 'En plats att köpa livsmedel');

INSERT INTO category (id, name, symbol, description)
VALUES (2, 'BlueberrySpot', 'Blueberry', 'A place to pick blueberries');

INSERT INTO category(id, name, symbol, description)
VALUES(3, 'BadPlats', 'Wave', 'En plats att svalka sig');

INSERT INTO category(id, name, symbol, description)
VALUES(4, 'DjurPark', 'Lion', 'En plats att åskåda exotiska djur');

INSERT INTO location(id, name, user_id, is_private, coordinate, description, fk_category)
VALUES (
           1,
           'Borås Djurpark',
           'ingrid',
           false,
           ST_GeomFromText('POINT(57.74148690409042 12.941680727659872)', 4326),
           'Fin djurpark',
           4);

INSERT INTO location(id, name, user_id, is_private, coordinate, description, fk_category)
VALUES (
           2,
           'Coop Sjöbo Torg',
           'ingrid',
           false,
           ST_GeomFromText('POINT(57.749657569208054 12.944105444901174)', 4326),
           'Litet ställe, inte jättestort utbud',
           1);

INSERT INTO location(id, name, user_id, is_private, coordinate, description, fk_category)
VALUES (
           3,
           'Mitt superhemliga blåbärs-ställe',
           'ingrid',
           true,
           ST_GeomFromText('POINT(57.754134333358294 12.924385843691052)', 4326),
           'SUPERHEMLIGT, DETTA STÄLLE FÅR ABSOLUT INTE KOMMA UT PUBLIKT',
           2);


INSERT INTO location(id, name, user_id, is_private, coordinate, description, fk_category)
VALUES (
           4,
           'Ica supermarket knalleland',
           'bertil',
           false,
           ST_GeomFromText('POINT(57.732616875599746 12.938208605777922)', 4326),
           'Detta ställe bör alla känna till!',
           1);

INSERT INTO location(id, name, user_id, is_private, coordinate, description, fk_category)
VALUES (
           5,
           'Coop Sjöbo Torg',
           'bertil',
           true,
           ST_GeomFromText('POINT(57.749657569208054 12.944105444901174)', 4326),
           'Detta ställe vill jag hålla privat',
           1);

INSERT INTO location(id, name, user_id, is_private, coordinate, description, fk_category)
VALUES (
           6,
           'Blåbäääääär',
           'bertil',
           false,
           ST_GeomFromText('POINT(57.7453661769841 12.965835357100056)', 4326),
           'Det finns så mycket blåbär här, att alla bör känna till detta stället',
           2);